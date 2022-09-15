package com.view.plantview.sensor.api.application.sensor.update;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorId;
import com.view.plantview.sensor.api.domain.validation.Error;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateSensorUseCase extends UpdateSensorUseCase {
    private final SensorGateway gateway;

    public DefaultUpdateSensorUseCase(SensorGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, UpdateSensorOutput> execute(final UpdateSensorCommand aCommand) {
        final var anId = SensorId.from(aCommand.id());
        final var anIdExternal = aCommand.idExternal();
        final var anEquipment = aCommand.equipment();
        final var aDowntime = aCommand.user();

        final var aSensor = this.gateway.findById(anId).orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aSensor.update(anIdExternal, anEquipment, aDowntime);
        aSensor.validate(notification);

        return notification.hasErrors() ? API.Left(notification) : update(aSensor);
    }

    private Either<Notification, UpdateSensorOutput> update(final Sensor aSensor) {
        return API.Try(() -> gateway.update(aSensor))
                .toEither()
                .bimap(Notification::create, UpdateSensorOutput::from);
    }

    private Supplier<DomainException> notFound(final SensorId anId) {
        return () -> DomainException.with(new Error("Sensor with ID %s was not found.".formatted(anId.getValue())));
    }
}
