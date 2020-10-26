package by.estore.controller.validation.impl;

import by.estore.controller.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements Validator<String> {

    private static final String PHONE_REGEX = "";

    private Pattern pattern = Pattern.compile(PHONE_REGEX);

    @Override
    public boolean isValid(String phone) {
        Matcher matcher = pattern.matcher(phone);
        return true;
    }
}
