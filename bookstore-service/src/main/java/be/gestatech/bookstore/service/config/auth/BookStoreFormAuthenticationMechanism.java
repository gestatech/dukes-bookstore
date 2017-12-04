package be.gestatech.bookstore.service.config.auth;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.*;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Authentication mechanism that authenticates according to the Servlet spec defined FORM authentication mechanism.
 * See Servlet spec for further details.
 */
@AutoApplySession // For "Is user already logged-in?"
@RememberMe(
        cookieSecureOnly = false, // Remove this when login is served over HTTPS.
        cookieMaxAgeSeconds = 60 * 60 * 24 * 14 // 14 days.
)
@LoginToContinue(loginPage = "/login?continue=true", errorPage = "", useForwardToLogin = false)
@ApplicationScoped
public class BookStoreFormAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {
        Credential credential = context.getAuthParameters().getCredential();
        if (Objects.nonNull(credential)) {
            return context.notifyContainerAboutLogin(identityStore.validate(credential));
        } else {
            return context.doNothing();
        }
    }

    // Workaround for CDI bug; default methods are not intercepted. Fixed in Weld 2.4.0 and 3.0.0, which we'll
    // eventually target. See https://issues.jboss.org/browse/WELD-2093
    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
        HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext);
    }
}
