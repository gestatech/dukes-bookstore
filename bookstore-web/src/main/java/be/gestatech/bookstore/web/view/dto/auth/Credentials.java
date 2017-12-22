package be.gestatech.bookstore.web.view.dto.auth;

import javax.validation.constraints.NotNull;

public class Credentials {

    @NotNull
    private Long id;

    private User user;

    @NotNull
    private byte[] passwordHash;

    @NotNull
    private byte[] salt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

}
