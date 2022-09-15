package com.view.plantview.sensor.api.application.downtime.retrieve.getById;

import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetDowntimeByIdUseCase extends GetDowntimeByIdUseCase {

    private final UserGateway gateway;

    public DefaultGetDowntimeByIdUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public DowntimeOutput execute(String anId) {
        final var aDowntimeId = UserId.from(anId);
        return this.gateway.findById(aDowntimeId).map(DowntimeOutput::from).orElseThrow(notFound(aDowntimeId));
    }

    private Supplier<DomainException> notFound(final UserId anId) {
        return () -> DomainException.with(new Error("Downtime with ID %s was not found.".formatted(anId.getValue())));
    }
}
