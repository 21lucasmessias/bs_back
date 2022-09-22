package io.sanctuary.bs.api.domain.user;

import io.sanctuary.bs.api.domain.validation.Error;
import io.sanctuary.bs.api.domain.validation.ValidationHandler;
import io.sanctuary.bs.api.domain.validation.Validator;

public class UserValidator extends Validator {

    private final User user;

    protected UserValidator(final User aUser, final ValidationHandler aHandler) {
        super(aHandler);

        this.user = aUser;
    }

    @Override
    public void validate() {
        if (user.getUniqueIdentifier() == null) {
            this.validationHandler().append(new Error("'uniqueIdentifier' should not be null"));
        } else if (user.getUniqueIdentifier().isEmpty()) {
            this.validationHandler().append(new Error("'uniqueIdentifier' should not be empty"));
        }

        if (user.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        } else if (user.getName().isEmpty()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
        }

        if (user.getPhoneNumber() == null) {
            this.validationHandler().append(new Error("'phoneNumber' should not be null"));
        } else if (user.getPhoneNumber().isEmpty()) {
            this.validationHandler().append(new Error("'phoneNumber' should not be empty"));
        }

        if (user.getEmail() == null) {
            this.validationHandler().append(new Error("'email' should not be null"));
        } else if (user.getEmail().isEmpty()) {
            this.validationHandler().append(new Error("'email' should not be empty"));
        }

        if(user.getRole() == null) {
            this.validationHandler().append(new Error("'role' should not be null"));
        }

        if (user.getPassword() == null) {
            this.validationHandler().append(new Error("'password' should not be null"));
        } else if (user.getPassword().length() < 5) {
            this.validationHandler().append(new Error("'password' length should be greater than 5"));
        }
    }
}
