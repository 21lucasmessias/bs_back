package com.view.plantview.sensor.api.application.downtime.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.validation.Error;
import com.view.plantview.sensor.api.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateDowntimeUseCase extends UpdateDowntimeUseCase {
    private final UserGateway gateway;

    public DefaultUpdateDowntimeUseCase(UserGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, UpdateDowntimeOutput> execute(final UpdateDowntimeCommand aCommand) {
        final var anId = UserId.from(aCommand.id());
        final var aSensorId = aCommand.sensorId();
        final var anEquipment = aCommand.equipment();
        final var aDate = aCommand.date();
        final var aStart = aCommand.start();
        final var anEnd = aCommand.end();

        final var aDowntime = this.gateway.findById(anId).orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aDowntime.update(aSensorId, anEquipment, aDate, aStart, anEnd);
        aDowntime.validate(notification);

        return notification.hasErrors() ? API.Left(notification) : update(aDowntime);
    }

    private Either<Notification, UpdateDowntimeOutput> update(final User aUser) {
        return API.Try(() -> gateway.update(aUser))
                .toEither()
                .bimap(Notification::create, UpdateDowntimeOutput::from);
    }

    private Supplier<DomainException> notFound(final UserId anId) {
        return () -> DomainException.with(new Error("Downtime with ID %s was not found.".formatted(anId.getValue())));
    }
}
