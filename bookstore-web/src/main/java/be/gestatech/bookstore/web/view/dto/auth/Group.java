package be.gestatech.bookstore.web.view.dto.auth;


import java.util.Arrays;
import java.util.List;

import static be.gestatech.bookstore.web.view.dto.auth.Role.*;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public enum Group {

    USER(VIEW_USER_PAGES, EDIT_OWN_PROFILE, ACCESS_API),
    ADMIN(VIEW_ADMIN_PAGES, EDIT_PROFILES);

    private final List<Role> roles;

    private Group(Role... roles) {
        this.roles = unmodifiableList(asList(roles));
    }

    public List<Role> getRoles() {
        return roles;
    }

    public static List<Group> getByRole(Role role) {
        return Arrays.stream(values())
                .filter(group -> group.getRoles().contains(role))
                .collect(toList());
    }
}
