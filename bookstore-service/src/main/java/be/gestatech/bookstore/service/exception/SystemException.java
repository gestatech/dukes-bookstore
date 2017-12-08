package be.gestatech.bookstore.service.exception;

public class SystemException extends BusinessException {

    private static final long serialVersionUID = -6901393911501075300L;

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
