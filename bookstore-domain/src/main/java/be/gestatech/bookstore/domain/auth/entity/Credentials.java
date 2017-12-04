package be.gestatech.bookstore.domain.auth.entity;

import org.hibernate.annotations.Cache;
import org.omnifaces.persistence.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import static org.hibernate.annotations.CacheConcurrencyStrategy.TRANSACTIONAL;


@Entity
public class Credentials extends BaseEntity<Long> {

    private static final long serialVersionUID = -539137323768185859L;

    @ManyToOne
    @Cache(usage = TRANSACTIONAL)
    private User user;

    @Column(length = 32)
    @NotNull
    private byte[] passwordHash;

    @Column(length = 40)
    @NotNull
    private byte[] salt;

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
