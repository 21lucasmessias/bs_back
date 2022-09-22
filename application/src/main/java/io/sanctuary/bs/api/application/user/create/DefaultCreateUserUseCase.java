package io.sanctuary.bs.api.application.user.create;

import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final UserGateway gateway;

    public DefaultCreateUserUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public Either<Notification, CreateUserOutput> execute(final CreateUserCommand aCommand) {
        final var anUniqueIdentifier = aCommand.uniqueIdentifier();
        final var aName = aCommand.name();
        final var aPhoneNumber = aCommand.phoneNumber();
        final var anEmail = aCommand.email();
        final var aRole = aCommand.role();
        final var aPassword = aCommand.password();

        final var aUser = User.newUser(
                anUniqueIdentifier,
                aName,
                aPhoneNumber,
                anEmail,
                aRole,
                aPassword
        );

        final var notification = Notification.create();

        aUser.validate(notification);

        return notification.hasErrors() ? Left(notification) : create(aUser);
    }

    private Either<Notification, CreateUserOutput> create(User aUser) {
        return Try(() -> gateway.create(aUser))
                .toEither()
                .bimap(Notification::create, CreateUserOutput::from);
    }
}
