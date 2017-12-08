package be.gestatech.bookstore.service.impl;

import be.gestatech.bookstore.domain.auth.entity.LoginToken;
import be.gestatech.bookstore.domain.auth.entity.TokenType;
import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.LoginTokenService;
import be.gestatech.bookstore.service.api.UserService;
import be.gestatech.bookstore.service.exception.InvalidUsernameException;
import org.omnifaces.persistence.service.BaseEntityService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import static org.omnifaces.utils.security.MessageDigests.digest;

@Stateless
public class LoginTokenServiceBean extends BaseEntityService<Long, LoginToken> implements LoginTokenService {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserService userService;


    @Override
    public String generate(String email, String remoteAddress, String description, TokenType tokenType) {
        Instant expiration = now().plus(14, DAYS);
        return generate(email, remoteAddress, description, tokenType, expiration);
    }

    @Override
    public String generate(String email, String ipAddress, String description, TokenType tokenType, Instant expiration) {
        String rawToken = randomUUID().toString();
        User user = userService.getByEmail(email).orElseThrow(InvalidUsernameException::new);

        LoginToken loginToken = new LoginToken();
        loginToken.setTokenHash(digest(rawToken, MESSAGE_DIGEST_ALGORITHM));
        loginToken.setExpiration(expiration);
        loginToken.setDescription(description);
        loginToken.setType(tokenType);
        loginToken.setIpAddress(ipAddress);
        loginToken.setUser(user);
        user.getLoginTokens().add(loginToken);
        return rawToken;
    }

    @Override
    public void remove(String loginToken) {
        entityManager.createNamedQuery("LoginToken.remove").setParameter("tokenHash", digest(loginToken, MESSAGE_DIGEST_ALGORITHM)).executeUpdate();
    }

    @Override
    public void removeExpired() {
        entityManager.createNamedQuery("LoginToken.removeExpired").executeUpdate();
    }
}
