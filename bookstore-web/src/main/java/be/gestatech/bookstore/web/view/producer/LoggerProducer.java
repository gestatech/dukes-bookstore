package be.gestatech.bookstore.web.view.producer;

import java.util.logging.Logger;



import javax.enterprise.context.Dependent;

import javax.enterprise.inject.Produces;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

@Named
@Dependent
public class LoggerProducer {

    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
