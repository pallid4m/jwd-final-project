package by.estore.web.validation.impl;

import by.estore.web.validation.Error;
import by.estore.web.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {

    /**
     * General Email Regex (RFC 5322 Official Standard)
     * @see "https://tools.ietf.org/html/rfc5322"
     */

    public static final String MESSAGE = "Email is not valid";

    public static final String ERROR_TYPE = "email";

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private Error error;

    @Override
    public boolean isValid(String email) {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            error = new Error(ERROR_TYPE, MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public Error getError() {
        return error;
    }
}
