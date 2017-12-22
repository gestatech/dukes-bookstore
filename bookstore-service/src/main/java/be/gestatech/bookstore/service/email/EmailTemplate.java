package be.gestatech.bookstore.service.email;

import be.gestatech.bookstore.service.dto.EmailUser;

import java.util.HashMap;
import java.util.Map;

public class EmailTemplate {

    private String templateId;

    // TODO convert to enum
    private String type;

    private EmailUser toUser;

    private EmailUser fromUser;

    private String replyTo;

    private boolean sendBccToFromUser = false;

    private Map<EmailTemplatePart, String> templateParts = new HashMap<>();

    private String callToActionURL;

    public EmailTemplate(String type) {
        this.type = type;
    }

    public EmailTemplate(String templateId, String type) {
        this.templateId = templateId;
        this.type = type;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getType() {
        return type;
    }


    public EmailUser getToUser() {
        return toUser;
    }

    public EmailTemplate setToUser(EmailUser toUser) {
        this.toUser = toUser;
        return this;
    }

    public EmailUser getFromUser() {
        return fromUser;
    }

    public EmailTemplate setFromUser(EmailUser fromUser) {
        this.fromUser = fromUser;
        return this;
    }


    public String getReplyTo() {
        return replyTo;
    }

    public EmailTemplate setReplyTo(String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    public boolean isSendBccToFromUser() {
        return sendBccToFromUser;
    }

    public EmailTemplate setSendBccToFromUser(boolean sendBccToFromUser) {
        this.sendBccToFromUser = sendBccToFromUser;
        return this;
    }

    public Map<EmailTemplatePart, String> getTemplateParts() {
        return templateParts;
    }

    public EmailTemplate setTemplateParts(Map<EmailTemplatePart, String> templateParts) {
        this.templateParts = templateParts;
        return this;
    }

    public EmailTemplate setTemplatePart(EmailTemplatePart key, String value) {
        templateParts.put(key, value);
        return this;
    }

    public String getCallToActionURL() {
        return callToActionURL;
    }

    public EmailTemplate setCallToActionURL(String url) {
        callToActionURL = url;
        return this;
    }
}