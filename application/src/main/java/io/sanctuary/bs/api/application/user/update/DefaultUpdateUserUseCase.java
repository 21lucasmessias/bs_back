package io.sanctuary.bs.api.application.user.update;

import io.sanctuary.bs.api.domain.exceptions.DomainException;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.user.UserId;
import io.sanctuary.bs.api.domain.validation.Error;
import io.sanctuary.bs.api.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateUserUseCase extends UpdateUserUseCase {
    private final UserGateway gateway;

    public DefaultUpdateUserUseCase(UserGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, UpdateUserOutput> execute(final UpdateUserCommand aCommand) {
        final var anId = UserId.from(aCommand.id());
        final var aName = aCommand.name();
        final var aPhoneNumber = aCommand.phoneNumber();
        final var anEmail = aCommand.email();
        final var aPassword = aCommand.password();

        final var aUser = this.gateway.findById(anId).orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aUser.update(aName, aPhoneNumber, anEmail, aPassword);
        aUser.validate(notification);

        return notification.hasErrors() ? API.Left(notification) : update(aUser);
    }

    private Either<Notification, UpdateUserOutput> update(final User aUser) {
        return API.Try(() -> gateway.update(aUser))
                .toEither()
                .bimap(Notification::create, UpdateUserOutput::from);
    }

    private Supplier<DomainException> notFound(final UserId anId) {
        return () -> DomainException.with(new Error("User with ID %s was not found.".formatted(anId.getValue())));
    }
}
