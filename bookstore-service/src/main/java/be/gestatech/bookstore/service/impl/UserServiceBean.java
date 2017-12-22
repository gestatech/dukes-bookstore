package be.gestatech.bookstore.service.impl;

import be.gestatech.bookstore.domain.auth.entity.Credentials;
import be.gestatech.bookstore.domain.auth.entity.TokenType;
import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.LoginTokenService;
import be.gestatech.bookstore.service.api.UserService;
import be.gestatech.bookstore.service.dto.EmailUser;
import be.gestatech.bookstore.service.email.EmailTemplate;
import be.gestatech.bookstore.service.exception.DuplicateEntityException;
import be.gestatech.bookstore.service.exception.InvalidPasswordException;
import be.gestatech.bookstore.service.exception.InvalidUsernameException;
import org.omnifaces.persistence.service.BaseEntityService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static be.gestatech.bookstore.domain.auth.entity.Group.USER;
import static org.omnifaces.persistence.JPA.getOptionalSingleResult;
import static org.omnifaces.utils.security.MessageDigests.digest;

@Stateless
public class UserServiceBean extends BaseEntityService<Long, User> implements UserService, Serializable {

    private static final long serialVersionUID = 906826172793752421L;

    @Resource
    private SessionContext sessionContext;

    @Inject
    private LoginTokenService loginTokenService;

    @Inject
    private AbstractEmailServiceBean emailService;

    @Override
    public void registerUser(User user, String password) {
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEntityException();
        }
        setCredentials(user, password);
        if (!user.getGroups().contains(USER)) {
            user.getGroups().add(USER);
        }
        super.save(user);
    }

    @Override
    public User update(User user) {
        User existingUser = manage(user);
        if (!user.getEmail().equals(existingUser.getEmail())) { // Email changed.
            Optional<User> otherUser = findByEmail(user.getEmail());
            if (otherUser.isPresent()) {
                if (!user.equals(otherUser.get())) {
                    throw new DuplicateEntityException();
                } else {
                    // Since email verification status can be updated asynchronous, the DB status is leading.
                    // Set the current user to whatever is already in the DB.
                    user.setEmailVerified(otherUser.get().isEmailVerified());
                }
            } else {
                user.setEmailVerified(false);
            }
        }
        return super.update(user);
    }

    @Override
    public void updatePassword(User user, String password) {
        User existingUser = manage(user);
        setCredentials(existingUser, password);
        super.update(existingUser);
    }

    @Override
    public void updatePassword(String loginToken, String password) {
        Optional<User> user = findByLoginToken(loginToken, TokenType.RESET_PASSWORD);
        if (user.isPresent()) {
            updatePassword(user.get(), password);
            loginTokenService.remove(loginToken);
        }
    }

    @Override
    public void requestResetPassword(String email, String ipAddress, String callbackUrlFormat) {
        User user = findByEmail(email).orElseThrow(InvalidUsernameException::new);
        ZonedDateTime expiration = ZonedDateTime.now().plusMinutes(DEFAULT_PASSWORD_RESET_EXPIRATION_TIME_IN_MINUTES);
        String token = loginTokenService.generate(email, ipAddress, "Reset Password", TokenType.RESET_PASSWORD, expiration.toInstant());
        EmailTemplate emailTemplate = new EmailTemplate("resetPassword")
                .setToUser(new EmailUser(user))
                .setCallToActionURL(String.format(callbackUrlFormat, token));
        Map<String, Object> messageParameters = new HashMap<>();
        messageParameters.put("expiration", expiration);
        messageParameters.put("ip", ipAddress);
        emailService.sendTemplate(emailTemplate, messageParameters);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return getOptionalSingleResult(createNamedTypedQuery("User.findByEmail")
                .setParameter("email", email));
    }

    @Override
    public Optional<User> findByLoginToken(String loginToken, TokenType type) {
        return getOptionalSingleResult(createNamedTypedQuery("User.getByLoginToken")
                .setParameter("tokenHash", digest(loginToken, MESSAGE_DIGEST_ALGORITHM))
                .setParameter("tokenType", type));
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email).orElseThrow(InvalidUsernameException::new);
        Credentials credentials = user.getCredentials();
        if (credentials == null) {
            throw new InvalidUsernameException();
        }
        byte[] passwordHash = digest(password, credentials.getSalt(), MESSAGE_DIGEST_ALGORITHM);
        if (!Arrays.equals(passwordHash, credentials.getPasswordHash())) {
            throw new InvalidPasswordException();
        }
        return user;
    }

    @Override
    public User getActiveUser() {
        return findByEmail(sessionContext.getCallerPrincipal().getName()).orElse(null);
    }

    private void setCredentials(User user, String password) {
        byte[] salt = generateSalt(DEFAULT_SALT_LENGTH);
        byte[] passwordHash = digest(password, salt, MESSAGE_DIGEST_ALGORITHM);
        Credentials credentials = user.getCredentials();
        if (credentials == null) {
            credentials = new Credentials();
            credentials.setUser(user);
            user.setCredentials(credentials);
        }
        credentials.setPasswordHash(passwordHash);
        credentials.setSalt(salt);
    }

    private byte[] generateSalt(int saltLength) {
        byte[] salt = new byte[saltLength];
        ThreadLocalRandom.current().nextBytes(salt);
        return salt;
    }
}
