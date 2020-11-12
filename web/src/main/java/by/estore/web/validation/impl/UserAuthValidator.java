package by.estore.web.validation.impl;

import by.estore.dto.UserAuth;
import by.estore.web.validation.Validator;

public class UserAuthValidator implements Validator<UserAuth> {

    @Override
    public boolean isValid(UserAuth var) {
        return true;
    }
}
