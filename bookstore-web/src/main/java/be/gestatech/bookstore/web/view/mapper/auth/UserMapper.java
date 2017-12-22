package be.gestatech.bookstore.web.view.mapper.auth;

import be.gestatech.bookstore.web.view.dto.auth.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class UserMapper {

    @Inject
    private LoginTokenMapper loginTokenMapper;

    @Inject
    private CredentialsMapper credentialsMapper;

    public User mapToWeb(be.gestatech.bookstore.domain.auth.entity.User source) {
        return mapToWeb(source, new User());
    }

    public User mapToWeb(be.gestatech.bookstore.domain.auth.entity.User source, User target) {
        User webUser = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                webUser = new User();
            }
            webUser.setId(source.getId());
            webUser.setFirstName(source.getFirstName());
            webUser.setLastName(source.getLastName());
            webUser.setEmail(source.getEmail());
            webUser.setLastLogin(source.getLastLogin());
            //webUser.setEmailVerified(source.getEmailVerified);
            //webUser.setGroups(source.getGroups());
            webUser.setCredentials(credentialsMapper.mapToWeb(source.getCredentials()));
            webUser.setLoginTokens(loginTokenMapper.mapToWeb(source.getLoginTokens()));
        }
        return webUser;
    }

    public be.gestatech.bookstore.domain.auth.entity.User mapToBusiness(User source) {
        return mapToBusiness(source, new be.gestatech.bookstore.domain.auth.entity.User());
    }

    public be.gestatech.bookstore.domain.auth.entity.User mapToBusiness(User source, be.gestatech.bookstore.domain.auth.entity.User target) {
        be.gestatech.bookstore.domain.auth.entity.User businessUser = null;
        if (Objects.nonNull(source)) {
            if (Objects.isNull(target)) {
                businessUser = new be.gestatech.bookstore.domain.auth.entity.User();
            }
            businessUser.setId(source.getId());
            businessUser.setFirstName(source.getFirstName());
            businessUser.setLastName(source.getLastName());
            businessUser.setEmail(source.getEmail());
            businessUser.setLastLogin(source.getLastLogin());
            //businessUser.setEmailVerified(source.getEmailVerified);
            //businessUser.setGroups(source.getGroups());
            businessUser.setCredentials(credentialsMapper.mapToBusiness(source.getCredentials()));
            businessUser.setLoginTokens(loginTokenMapper.mapToBusiness(source.getLoginTokens()));
        }
        return businessUser;
    }
}
