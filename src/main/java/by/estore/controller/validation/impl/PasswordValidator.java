package by.estore.controller.validation.impl;

import by.estore.controller.validation.Validator;

public class PasswordValidator implements Validator<String> {

    private static final String REGEX = "";

    @Override
    public boolean isValid(String password) {
        return true;
    }
}
