package com.view.plantview.sensor.api.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler aHandler);

    ValidationHandler validate(Validation aValidation);

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    List<Error> getErrors();

    interface Validation {
        void validate();
    }

}
