package by.estore.web.validation;

import by.estore.dto.UserAuth;
import by.estore.web.validation.impl.EmailValidator;
import by.estore.web.validation.impl.PasswordValidator;
import by.estore.web.validation.impl.UserAuthValidator;

public final class ValidatorFactory {
    private static final ValidatorFactory instance = new ValidatorFactory();

    private final Validator<UserAuth> userAuthValidator = new UserAuthValidator();

    private final Validator<String> emailValidator = new EmailValidator();
    private final Validator<String> passwordValidator = new PasswordValidator();

    private ValidatorFactory() {}

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public Validator<UserAuth> getUserAuthValidator() {
        return userAuthValidator;
    }

    public Validator<String> getEmailValidator() {
        return emailValidator;
    }

    public Validator<String> getPasswordValidator() {
        return passwordValidator;
    }
}
