package be.gestatech.bookstore.service.api;

import be.gestatech.bookstore.domain.auth.entity.TokenType;
import be.gestatech.bookstore.domain.auth.entity.User;

import javax.ejb.Local;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Local
public interface UserService {

    int DEFAULT_SALT_LENGTH = 40;

    long DEFAULT_PASSWORD_RESET_EXPIRATION_TIME_IN_MINUTES = TimeUnit.HOURS.toMinutes(1);

    String MESSAGE_DIGEST_ALGORITHM = "SHA-256";

    void registerUser(User user, String password);

    User update(User user);

    void updatePassword(User user, String password);

    void updatePassword(String loginToken, String password);

    void requestResetPassword(String email, String ipAddress, String callbackUrlFormat);

    Optional<User> findByEmail(String email);

    Optional<User> findByLoginToken(String loginToken, TokenType type);

    User findByEmailAndPassword(String email, String password);

    User getActiveUser();
}
