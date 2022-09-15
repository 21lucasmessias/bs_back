package com.view.plantview.sensor.api.application.sensor.retrieve.getById;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorId;
import com.view.plantview.sensor.api.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetSensorByIdUseCase extends GetSensorByIdUseCase {

    private final SensorGateway gateway;

    public DefaultGetSensorByIdUseCase(final SensorGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public SensorOutput execute(String anId) {
        final var aSensorId = SensorId.from(anId);
        return this.gateway.findById(aSensorId).map(SensorOutput::from).orElseThrow(notFound(aSensorId));
    }

    private Supplier<DomainException> notFound(final SensorId anId) {
        return () -> DomainException.with(new Error("Sensor with ID %s was not found.".formatted(anId.getValue())));
    }
}
