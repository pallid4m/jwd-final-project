package by.estore.controller.validation.impl;

import by.estore.controller.dto.UserAuth;
import by.estore.controller.validation.Validator;

public class UserAuthValidator implements Validator<UserAuth> {

    @Override
    public boolean isValid(UserAuth var) {
        return true;
    }
}
