package by.estore.service.exception;

public class AuthorizationException extends RuntimeException {

    private String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
