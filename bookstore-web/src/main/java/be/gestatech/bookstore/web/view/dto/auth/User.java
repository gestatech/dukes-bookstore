package be.gestatech.bookstore.web.view.dto.auth;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class User {

    private Long id;

    @NotNull //@Email
    private String email;

    private String firstName;

    private String lastName;

    private String fullName;

    private boolean emailVerified;// For now.

    private Credentials credentials;

    private List<LoginToken> loginTokens = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();

    private Instant lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<LoginToken> getLoginTokens() {
        return loginTokens;
    }

    public void setLoginTokens(List<LoginToken> loginTokens) {
        this.loginTokens = loginTokens;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Role> getRoles() {
        return groups.stream().flatMap(group -> group.getRoles().stream()).collect(toSet());
    }

    public Set<String> getRolesAsStrings() {
        return getRoles().stream().map(Role::name).collect(toSet());
    }
}
