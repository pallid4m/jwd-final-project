package by.estore.web.validation;

public interface Validator<T> {
    boolean isValid(T var);
    String getMessage();
}
