package by.estore.service.exception;

public class UserAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = -6244986964817710202L;

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
