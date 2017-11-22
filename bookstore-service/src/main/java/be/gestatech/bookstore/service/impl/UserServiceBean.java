package be.gestatech.bookstore.service.impl;

import be.gestatech.bookstore.service.api.UserService;

import javax.ejb.Stateless;

@Stateless
public class UserServiceBean implements UserService{
    @Override
    public String sayHello() {
        return "hello";
    }
}
