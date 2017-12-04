package be.gestatech.bookstore.service.impl;

import be.gestatech.bookstore.domain.auth.entity.User;
import be.gestatech.bookstore.service.api.UserService;
import org.omnifaces.persistence.service.BaseEntityService;
import javax.ejb.Stateless;

@Stateless
public class UserServiceBean extends BaseEntityService<Long, User> implements UserService{
    @Override
    public String sayHello() {
        return "hello";
    }
}
