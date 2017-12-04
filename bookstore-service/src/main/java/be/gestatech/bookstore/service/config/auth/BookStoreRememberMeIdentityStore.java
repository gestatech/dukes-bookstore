package be.gestatech.bookstore.service.config.auth;

import be.gestatech.bookstore.domain.auth.entity.TokenType;
import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.UserService;
import org.omnifaces.util.Servlets;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

@Named
@ApplicationScoped
public class BookStoreRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private HttpServletRequest request;

    @Inject
    private UserService userService;

    @Inject
    private LoginTokenService loginTokenService;

    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        Optional<User> user = userService.findByLoginToken(credential.getToken(), TokenType.REMEMBER_ME);
        if (user.isPresent()) {
            return new CredentialValidationResult(new CallerPrincipal(user.get().getEmail()), user.get().getRolesAsStrings());
        } else {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        return loginTokenService.generate(callerPrincipal.getName(), Servlets.getRemoteAddr(request), getDescription(), TokenType.REMEMBER_ME);
    }

    @Override
    public void removeLoginToken(String loginToken) {
        loginTokenService.remove(loginToken);
    }

    private String getDescription() {
        return "Remember me session: " + request.getHeader("User-Agent");
    }
}
