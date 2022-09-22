package io.sanctuary.bs.api.application.user.delete;

import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.user.UserId;

import java.util.Objects;

public class DefaultDeleteUserUseCase extends DeleteUserUseCase {
    private final UserGateway gateway;

    public DefaultDeleteUserUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public void execute(String anId) {
        gateway.deleteById(UserId.from(anId));
    }
}
