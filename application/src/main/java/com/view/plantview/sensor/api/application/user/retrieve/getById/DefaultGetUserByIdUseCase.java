package com.view.plantview.sensor.api.application.user.retrieve.getById;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway gateway;

    public DefaultGetUserByIdUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public UserOutput execute(String anId) {
        final var aUserId = UserId.from(anId);
        return this.gateway.findById(aUserId).map(UserOutput::from).orElseThrow(notFound(aUserId));
    }

    private Supplier<DomainException> notFound(final UserId anId) {
        return () -> DomainException.with(new Error("User with ID %s was not found.".formatted(anId.getValue())));
    }
}
