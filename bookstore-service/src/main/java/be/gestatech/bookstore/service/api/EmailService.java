package be.gestatech.bookstore.service.api;

import be.gestatech.bookstore.service.dto.EmailUser;
import be.gestatech.bookstore.service.email.EmailTemplate;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface EmailService {

    void sendTemplateMessage(EmailTemplate templateEmail, Map<String, Object> messageParameters, Map<String, String> templateContent);

    void sendPlainTextMessage(EmailUser to, EmailUser from, String subject, String body, String replyTo);
}
