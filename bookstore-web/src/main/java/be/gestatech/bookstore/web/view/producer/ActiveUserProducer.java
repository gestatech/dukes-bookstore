package be.gestatech.bookstore.web.view.producer;

import be.gestatech.bookstore.service.api.UserService;
import be.gestatech.bookstore.web.view.dto.auth.User;
import be.gestatech.bookstore.web.view.dto.user.ActiveUser;
import be.gestatech.bookstore.web.view.mapper.auth.UserMapper;
import org.omnifaces.security.AuthenticatedEvent;
import org.omnifaces.security.LoggedOutEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

import static java.time.Instant.now;
import static org.omnifaces.util.Beans.getManager;
import static org.omnifaces.util.BeansLocal.destroy;

@Named
@SessionScoped
public class ActiveUserProducer implements Serializable {

    private static final long serialVersionUID = -5314245770532055673L;

    private User activeUser;

    @Inject
    private UserMapper userMapper;

    @Inject
    private UserService userService;

    @Produces
    @Named
    @RequestScoped
    public ActiveUser getActiveUser() {
        return new ActiveUser(activeUser);
    }

    public void onAuthenticated(@Observes AuthenticatedEvent event) {
        if (Objects.isNull(activeUser) || !event.getUserPrincipal().getName().equals(activeUser.getEmail())) {
            activeUser = userMapper.mapToWeb(userService.getActiveUser());
            if (Objects.nonNull(activeUser)) {
                activeUser.setLastLogin(now());
                userService.update(userMapper.mapToBusiness(activeUser));
            }
            refreshInjectedActiveUsers();
        }
    }

    public void onLoggedOut(@Observes LoggedOutEvent event) {
        activeUser = null;
        refreshInjectedActiveUsers();
    }

    private void refreshInjectedActiveUsers() {
        destroy(getManager(), ActiveUser.class);
    }

}
