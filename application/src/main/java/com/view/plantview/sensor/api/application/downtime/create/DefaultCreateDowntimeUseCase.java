package com.view.plantview.sensor.api.application.downtime.create;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateDowntimeUseCase extends CreateDowntimeUseCase {

    private final UserGateway gateway;

    public DefaultCreateDowntimeUseCase(final UserGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, CreateDowntimeOutput> execute(final CreateDowntimeCommand aCommand) {
        final var aSensorId = aCommand.sensorId();
        final var anEquipment = aCommand.equipment();
        final var aDate = aCommand.date();
        final var aStart = aCommand.start();
        final var anEnd = aCommand.end();

        final var notification = Notification.create();

        final var aDowntime = User.newDowntime(aSensorId, anEquipment, aDate, aStart, anEnd);

        aDowntime.validate(notification);

        return notification.hasErrors() ? Left(notification) : create(aDowntime);
    }

    private Either<Notification, CreateDowntimeOutput> create(final User aUser) {
        return Try(() -> this.gateway.create(aUser))
                .toEither()
                .bimap(Notification::create, CreateDowntimeOutput::from);
    }
}
