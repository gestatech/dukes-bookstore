package be.gestatech.bookstore.service.api;

import be.gestatech.bookstore.service.dto.EmailUser;
import be.gestatech.bookstore.service.email.EmailTemplate;
import be.gestatech.bookstore.service.email.EmailTemplatePart;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface EmailTemplateService {

    String build(EmailTemplate templateEmail, EmailTemplatePart templatePart, Map<String, Object> messageParameters);

    Map<String, Object> addUserParameters(String prefix, EmailUser user, Map<String, Object> messageParameters);
}
