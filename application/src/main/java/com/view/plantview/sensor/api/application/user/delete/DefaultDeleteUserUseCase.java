package com.view.plantview.sensor.api.application.user.delete;

import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;

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
