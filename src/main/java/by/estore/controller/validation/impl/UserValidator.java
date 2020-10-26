package by.estore.controller.validation.impl;

import by.estore.bean.User;
import by.estore.controller.validation.Validator;

public class UserValidator implements Validator<User> {

    public static final String INVALID_EMAIL_MESSAGE = "poor email";
    public static final String INVALID_PASSWORD_MESSAGE = "poor password";
    public static final String INVALID_PHONE_MESSAGE = "poor phone";

    @Override
    public boolean isValid(User var) {
        return true;
    }
}
