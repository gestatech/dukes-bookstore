package be.gestatech.bookstore.service.api;

import javax.ejb.Local;

@Local
public interface UserService {

    public String sayHello();
}
