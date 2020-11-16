package by.estore.web.validation.impl;

import by.estore.dto.UserAuth;
import by.estore.web.validation.Validator;

public class UserAuthValidator implements Validator<UserAuth> {

    private static final String message = "User auth is not valid";

    private final Validator<String> emailValidator = new EmailValidator();
    private final Validator<String> passwordValidator = new PasswordValidator();

    @Override
    public boolean isValid(UserAuth user) {
        return emailValidator.isValid(user.getEmail()) && passwordValidator.isValid(user.getPassword());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
