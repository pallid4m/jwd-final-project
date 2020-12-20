package by.estore.service.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1478376845963869992L;

    public final String NAME = "userNotFound";

    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
