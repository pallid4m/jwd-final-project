package by.estore.web.validation.impl;

import by.estore.web.validation.Validator;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    private Validator<String> emailValidator = new EmailValidator();

    private Map<String, Boolean> map = Map.of(
            "sometest@gmail.com", Boolean.TRUE,
            "some+test@gmail.com", Boolean.TRUE,
            "stuart.sillitoe@prodirectsport.net", Boolean.TRUE,
            "_valid@mail.com", Boolean.TRUE,
            "also+valid@domain.com", Boolean.TRUE,
            "invalíd@mail.com", Boolean.FALSE,
            "invalid%$£\"@domain.com", Boolean.FALSE,
            "invalid£@domain.com", Boolean.FALSE,
            "valid%$@domain.com", Boolean.TRUE,
            "invali\"d@domain.com", Boolean.FALSE
    );

    @Test
    public void isEmailValidTest() {
        map.forEach((email, expected) -> assertEquals(expected, emailValidator.isValid(email)));
    }
}