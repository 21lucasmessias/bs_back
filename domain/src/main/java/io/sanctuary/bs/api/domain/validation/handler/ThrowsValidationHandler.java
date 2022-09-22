package io.sanctuary.bs.api.domain.validation.handler;

import io.sanctuary.bs.api.domain.exceptions.DomainException;
import io.sanctuary.bs.api.domain.validation.Error;
import io.sanctuary.bs.api.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(List.of(anError));
    }

    @Override
    public ValidationHandler append(final ValidationHandler aHandler) {
        throw DomainException.with(aHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception exception) {
            throw DomainException.with(new Error(exception.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
