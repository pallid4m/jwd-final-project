package by.estore.controller.validation;

public interface Validator<T> {

    boolean isValid(T var);
}
