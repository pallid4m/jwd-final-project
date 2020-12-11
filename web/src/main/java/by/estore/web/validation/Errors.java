package by.estore.web.validation;

import java.util.HashSet;
import java.util.Set;

public class Errors {

    private Set<Error> errors = new HashSet<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public Set<Error> getErrors() {
        return errors;
    }
}
