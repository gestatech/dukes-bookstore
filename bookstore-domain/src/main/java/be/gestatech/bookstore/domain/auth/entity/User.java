package be.gestatech.bookstore.domain.auth.entity;


import be.gestatech.bookstore.domain.auth.validator.Email;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.omnifaces.persistence.model.TimestampedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.TRANSACTIONAL;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query = "SELECT user FROM User user WHERE user.email = :email"),
    @NamedQuery(name = "User.getByLoginToken", query = "SELECT user FROM User user JOIN user.loginTokens loginToken JOIN FETCH user.loginTokens WHERE loginToken.tokenHash = :tokenHash AND loginToken.type = :tokenType AND loginToken.expiration > CURRENT_TIMESTAMP")
})
public class User extends TimestampedEntity<Long> {

    private static final long serialVersionUID = 6016261369255337317L;

    @Column(length = 254, nullable = false, unique = true)
    @NotNull
    @Email
    private String email;

    @Column(length = 32, nullable = false)
    @NotNull
    @Size(max = 32)
    private String firstName;

    @Column(length = 32, nullable = false)
    @NotNull
    @Size(max = 32)
    private String lastName;

    @Formula("CONCAT(firstName, ' ', lastName)")
    private String fullName;

    /*
     * TODO: implement.
     */
    @Column(nullable = false)
    private boolean emailVerified = true; // For now.

    @OneToOne(fetch = LAZY, cascade = ALL)
    private Credentials credentials;

    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @Cache(usage = TRANSACTIONAL)
    private List<LoginToken> loginTokens = new ArrayList<>();

    @ElementCollection(fetch = EAGER)
    @Enumerated(STRING)
    private List<Group> groups = new ArrayList<>();

    @Column
    private Instant lastLogin;

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

    @Transient
    public Set<Role> getRoles() {
        return groups.stream().flatMap(g -> g.getRoles().stream()).collect(toSet());
    }

    @Transient
    public Set<String> getRolesAsStrings() {
        return getRoles().stream().map(Role::name).collect(toSet());
    }
}
