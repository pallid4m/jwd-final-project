package by.estore.service.exception;

public class AuthorizationException extends RuntimeException {

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
