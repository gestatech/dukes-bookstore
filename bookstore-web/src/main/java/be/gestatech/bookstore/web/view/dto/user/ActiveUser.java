package be.gestatech.bookstore.web.view.dto.user;

import be.gestatech.bookstore.web.view.dto.auth.Group;
import be.gestatech.bookstore.web.view.dto.auth.Role;
import be.gestatech.bookstore.web.view.dto.auth.User;
import org.omnifaces.config.WebXml;

import javax.enterprise.inject.Typed;
import javax.inject.Named;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is produced by {@link be.gestatech.bookstore.web.view.producer.ActiveUserProducer}.
 * This is available in EL by #{activeUser}.
 * This is available in backing beans by {@code @Inject}.
 */
@Named
@Typed
public class ActiveUser {

    private Map<String, Boolean> is = new ConcurrentHashMap<>();

    private Map<String, Boolean> can = new ConcurrentHashMap<>();

    private Map<String, Boolean> canView = new ConcurrentHashMap<>();

    private User activeUser;

    public ActiveUser() {
        // Constructor for proxy.
    }

    public ActiveUser(User user) { // Used by ActiveUserProducer.
        activeUser = user;
    }

    public boolean isPresent() { // For use in both backing beans and EL.
        return Objects.nonNull(activeUser);
    }

    public Long getId() { // For use in backing beans in order to get concrete User.
        return isPresent() ? activeUser.getId() : null;
    }

    public boolean hasGroup(Group group) { // For use in backing beans.
        return isPresent() && activeUser.getGroups().contains(group);
    }

    public boolean hasRole(Role role) { // For use in backing beans.
        return isPresent() && activeUser.getRoles().contains(role);
    }

    public boolean is(String group) { // For use in EL #{activeUser.is('ADMIN')}
        return isPresent() && is.computeIfAbsent(group, g -> activeUser.getGroups().stream().map(Group::name).anyMatch(g::equalsIgnoreCase));
    }

    public boolean can(String role) { // For use in EL #{activeUser.can('VIEW_ADMIN_PAGES')}
        return isPresent() && can.computeIfAbsent(role, r -> activeUser.getRolesAsStrings().stream().anyMatch(r::equalsIgnoreCase));
    }

    public boolean canView(String path) { // For use in EL #{activeUser.canView('admin/users')}
        return canView.computeIfAbsent(path, this::isAccessAllowed);
    }

    private boolean isAccessAllowed(String path) {
        String uri = path.startsWith("/") ? path : ("/" + path);
        if (WebXml.INSTANCE.isAccessAllowed(uri, null)) {
            return true;
        }
        return isPresent() && activeUser.getRolesAsStrings().stream().anyMatch(r -> WebXml.INSTANCE.isAccessAllowed(uri, r));
    }

    @Override
    public String toString() { // Must print friendly name in EL #{activeUser}.
        return isPresent() ? activeUser.getFullName() : null;
    }

}
