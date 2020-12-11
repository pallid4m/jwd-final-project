package by.estore.web.validation.impl;

import by.estore.web.validation.Error;
import by.estore.web.validation.Validator;

public class PasswordValidator implements Validator<String> {

    public static final String MESSAGE = "Password less than 6 characters";

    public static final String ERROR_TYPE = "password";

    private static final int PASS_LENGTH = 6;

    private Error error;

    @Override
    public boolean isValid(String password) {
        if (password.length() < PASS_LENGTH) {
            error = new Error(ERROR_TYPE, MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public Error getError() {
        return error;
    }
}
