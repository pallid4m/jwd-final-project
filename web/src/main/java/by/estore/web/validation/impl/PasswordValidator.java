package by.estore.web.validation.impl;

import by.estore.web.validation.Validator;

public class PasswordValidator implements Validator<String> {

    private static final String REGEX = "";

    @Override
    public boolean isValid(String password) {
        return true;
    }
}
