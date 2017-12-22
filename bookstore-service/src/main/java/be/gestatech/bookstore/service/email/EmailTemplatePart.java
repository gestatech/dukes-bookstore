package be.gestatech.bookstore.service.email;

public enum EmailTemplatePart {

    SUBJECT_CONTENT("subject_content", false),

    SALUTATION_CONTENT("salutation_content", false),

    BODY_TITLE("body_title", false),

    BODY_CONTENT("body_content", true),

    OBJECT_CONTENT("object_content", true),

    CALL_TO_ACTION_LABEL("call_to_action_label", false);

    private final String key;

    private final boolean html;

    EmailTemplatePart(String key, boolean html) {
        this.key = key;
        this.html = html;
    }

    public String getKey() {
        return key;
    }

    public boolean isHtml() {
        return html;
    }
}