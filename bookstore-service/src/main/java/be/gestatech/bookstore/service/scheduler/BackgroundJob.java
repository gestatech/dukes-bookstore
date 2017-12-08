package be.gestatech.bookstore.service.scheduler;

import be.gestatech.bookstore.service.api.LoginTokenService;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class BackgroundJob {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private LoginTokenService loginTokenService;

    @Schedule(dayOfWeek = "*", hour = "*", minute = "0", second = "0", persistent = false)
    public void hourly() {
        loginTokenService.removeExpired();
    }
}
