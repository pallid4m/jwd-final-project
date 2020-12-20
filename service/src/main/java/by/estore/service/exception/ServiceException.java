package by.estore.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 6824198657773254956L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
