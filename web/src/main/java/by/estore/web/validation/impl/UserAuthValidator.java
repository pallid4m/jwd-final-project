package by.estore.web.validation.impl;

import by.estore.dto.UserAuth;
import by.estore.web.validation.Error;
import by.estore.web.validation.Errors;
import by.estore.web.validation.Validator;
import by.estore.web.validation.ValidatorFactory;

public class UserAuthValidator implements Validator<UserAuth> {

    private static final String message = "User auth is not valid";

    private final Errors errors = new Errors();

    @Override
    public boolean isValid(UserAuth user) {
        Validator<String> emailValidator = ValidatorFactory.getInstance().getEmailValidator();
        Validator<String> passwordValidator = ValidatorFactory.getInstance().getPasswordValidator();
        boolean ret = true;

        if (!emailValidator.isValid(user.getEmail())) {
            Error error = ((EmailValidator) emailValidator).getError();
            errors.addError(error);
            ret = false;
        }

        if (!passwordValidator.isValid(user.getPassword())) {
            Error error = ((PasswordValidator) passwordValidator).getError();
            errors.addError(error);
            ret = false;
        }

        return ret;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Errors getErrors() {
        return errors;
    }
}
