package be.gestatech.bookstore.web.view.dto.auth;

import javax.validation.constraints.NotNull;

public class Credentials {

    private User user;

    private @NotNull byte[] passwordHash;

    private @NotNull
    byte[] salt;

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
