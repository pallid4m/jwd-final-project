package by.estore.service.exception;

public class AuthorizationException extends RuntimeException {
    private static final long serialVersionUID = 4799788377463416012L;

    public final String NAME = "auth";

    private String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
