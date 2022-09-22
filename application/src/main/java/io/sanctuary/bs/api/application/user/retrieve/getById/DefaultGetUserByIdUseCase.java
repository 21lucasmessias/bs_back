package io.sanctuary.bs.api.application.user.retrieve.getById;

import io.sanctuary.bs.api.domain.exceptions.DomainException;
import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.user.UserId;
import io.sanctuary.bs.api.domain.validation.Error;

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
