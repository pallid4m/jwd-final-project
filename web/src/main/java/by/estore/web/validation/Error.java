package by.estore.web.validation;

import java.util.Objects;

public class Error {

    private String type;
    private String message;

    public Error(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(type, error.type) &&
                Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, message);
    }

    @Override
    public String toString() {
        return "Error{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
