package by.estore.controller.validation;

import by.estore.controller.dto.UserAuth;

public class UserAuthValidator implements Validator<UserAuth> {

    @Override
    public boolean isValid(UserAuth var) {
        return true;
    }
}
