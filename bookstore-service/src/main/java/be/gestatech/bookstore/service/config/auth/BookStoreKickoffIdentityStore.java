package be.gestatech.bookstore.service.config.auth;

import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.UserService;
import be.gestatech.bookstore.service.exception.EmailNotVerifiedException;
import be.gestatech.bookstore.service.exception.InvalidCredentialsException;

import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

public class BookStoreKickoffIdentityStore implements IdentityStore {

    @Inject
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        try {
            if (credential instanceof UsernamePasswordCredential) {
                String email = ((UsernamePasswordCredential) credential).getCaller();
                String password = ((UsernamePasswordCredential) credential).getPasswordAsString();
                return validate(userService.findByEmailAndPassword(email, password));
            }
            if (credential instanceof CallerOnlyCredential) {
                String email = ((CallerOnlyCredential) credential).getCaller();
                return validate(userService.findByEmail(email).orElseThrow(InvalidCredentialsException::new));
            }
        } catch (InvalidCredentialsException e) {
            return INVALID_RESULT;
        }
        return NOT_VALIDATED_RESULT;
    }

    private static CredentialValidationResult validate(User user) {
        if (!user.isEmailVerified()) {
            throw new EmailNotVerifiedException();
        }
        return new CredentialValidationResult(user.getEmail(), user.getRolesAsStrings());
    }
}
