package be.gestatech.bookstore.service.config;

import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.UserService;
import org.omnifaces.cdi.Startup;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ResourceBundle;

import static be.gestatech.bookstore.domain.auth.entity.Group.ADMIN;
import static be.gestatech.bookstore.domain.auth.entity.Group.USER;
import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static java.util.ResourceBundle.getBundle;
import static org.omnifaces.util.Faces.getLocale;
import static org.omnifaces.utils.Lang.isEmpty;

/**
 * For explanation about @Startup annotation see: http://omnifaces.org/docs/javadoc/2.2/org/omnifaces/cdi/Startup.html
 */
@Startup
public class StartupBean {

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        setupTestUsers();
        configureMessageResolver();
    }

    private void setupTestUsers() {
        if (!userService.findByEmail("admin@kickoff.example.org").isPresent()) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Admin");
            user.setEmail("admin@kickoff.example.org");
            user.setGroups(asList(ADMIN, USER));
            userService.registerUser(user, "passw0rd");
        }

        if (!userService.findByEmail("user@kickoff.example.org").isPresent()) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("User");
            user.setEmail("user@kickoff.example.org");
            user.setGroups(asList(USER));
            userService.registerUser(user, "passw0rd");
        }
    }

    private void configureMessageResolver() {

        Messages.setResolver(new Messages.Resolver() {
            private static final String BASE_NAME = "org.example.kickoff.i18n.ApplicationBundle";

            @Override
            public String getMessage(String message, Object... params) {
                ResourceBundle bundle = getBundle(BASE_NAME, getLocale());
                if (bundle.containsKey(message)) {
                    message = bundle.getString(message);
                }
                return isEmpty(params) ? message : format(message, params);
            }
        });
    }

}
