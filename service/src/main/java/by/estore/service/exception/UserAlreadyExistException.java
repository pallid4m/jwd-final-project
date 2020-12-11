package by.estore.service.exception;

public class UserAlreadyExistException extends RuntimeException {

    public final String NAME = "userAlreadyExist";

    private String message;

    public UserAlreadyExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
