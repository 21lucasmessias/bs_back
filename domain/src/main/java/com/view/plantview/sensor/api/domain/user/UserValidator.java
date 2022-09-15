package com.view.plantview.sensor.api.domain.user;

import com.view.plantview.sensor.api.domain.validation.Error;
import com.view.plantview.sensor.api.domain.validation.ValidationHandler;
import com.view.plantview.sensor.api.domain.validation.Validator;

public class UserValidator extends Validator {

    private final User user;

    protected UserValidator(final User aUser, final ValidationHandler aHandler) {
        super(aHandler);

        this.user = aUser;
    }

    @Override
    public void validate() {
        if (user.getUniqueIdentifier() == null) {
            this.validationHandler().append(new Error("'unique identifier' should not be null"));
        }

        if (user.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }

        if (user.getPhoneNumber() == null) {
            this.validationHandler().append(new Error("'phone number' should not be null"));
        }

        if (user.getEmail() == null) {
            this.validationHandler().append(new Error("'email' should not be null"));
        }

        if (user.getPassword() == null) {
            this.validationHandler().append(new Error("'password' should not be null"));
        }

        if (user.getPassword().length() < 5) {
            this.validationHandler().append(new Error("'password' length should be greater than 5"));
        }
    }
}
