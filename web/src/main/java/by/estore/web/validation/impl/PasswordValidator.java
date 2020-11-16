package by.estore.web.validation.impl;

import by.estore.web.validation.Validator;

public class PasswordValidator implements Validator<String> {

    public static final String MESSAGE = "Password is not valid";

    private static final int PASS_LENGTH = 6;

    @Override
    public boolean isValid(String password) {
        return password.length() >= PASS_LENGTH;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
