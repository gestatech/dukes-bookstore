package be.gestatech.bookstore.service.api;

import be.gestatech.bookstore.domain.auth.entity.TokenType;

import javax.ejb.Local;
import java.time.Instant;

@Local
public interface LoginTokenService {

    String MESSAGE_DIGEST_ALGORITHM = "SHA-256";

    String generate(String email, String remoteAddress, String description, TokenType tokenType);

    String generate(String email, String ipAddress, String description, TokenType tokenType, Instant expiration);

    void remove(String loginToken);

    void removeExpired();
}
