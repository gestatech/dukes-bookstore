package be.gestatech.bookstore.web.view.mapper.auth;

import be.gestatech.bookstore.web.view.dto.auth.Credentials;

import javax.inject.Named;
import java.util.Objects;

@Named
public class CredentialsMapper {

    public Credentials mapToWeb(be.gestatech.bookstore.domain.auth.entity.Credentials source) {
        return mapToWeb(source, new Credentials());
    }

    public Credentials mapToWeb(be.gestatech.bookstore.domain.auth.entity.Credentials source, Credentials target) {
        Credentials webCredentials = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                webCredentials = new Credentials();
            }
            webCredentials.setId(source.getId());
            webCredentials.setPasswordHash(source.getPasswordHash());
            webCredentials.setSalt(source.getSalt());
            //webCredentials.setUser(source.getUser());
        }
        return webCredentials;
    }

    public be.gestatech.bookstore.domain.auth.entity.Credentials mapToBusiness(Credentials source) {
        return mapToBusiness(source, new be.gestatech.bookstore.domain.auth.entity.Credentials());
    }

    public be.gestatech.bookstore.domain.auth.entity.Credentials mapToBusiness(Credentials source, be.gestatech.bookstore.domain.auth.entity.Credentials target) {
        be.gestatech.bookstore.domain.auth.entity.Credentials businessCredentials = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                businessCredentials = new be.gestatech.bookstore.domain.auth.entity.Credentials();
            }
            businessCredentials.setId(source.getId());
            businessCredentials.setPasswordHash(source.getPasswordHash());
            businessCredentials.setSalt(source.getSalt());
            //businessCredentials.setUser(source.getUser());
        }
        return businessCredentials;
    }
}
