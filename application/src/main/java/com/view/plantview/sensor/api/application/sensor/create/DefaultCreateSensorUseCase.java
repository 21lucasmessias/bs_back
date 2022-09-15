package com.view.plantview.sensor.api.application.sensor.create;

import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateSensorUseCase extends CreateSensorUseCase {

    private final SensorGateway gateway;

    public DefaultCreateSensorUseCase(final SensorGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public Either<Notification, CreateSensorOutput> execute(final CreateSensorCommand aCommand) {
        final var anExternalId = aCommand.externalId();
        final var anEquipment = aCommand.equipment();

        final var aSensor = Sensor.newSensor(anExternalId, anEquipment);

        final var notification = Notification.create();

        aSensor.validate(notification);

        return notification.hasErrors() ? Left(notification) : create(aSensor);
    }

    private Either<Notification, CreateSensorOutput> create(Sensor aSensor) {
        return Try(() -> gateway.create(aSensor))
                .toEither()
                .bimap(Notification::create, CreateSensorOutput::from);
    }
}
