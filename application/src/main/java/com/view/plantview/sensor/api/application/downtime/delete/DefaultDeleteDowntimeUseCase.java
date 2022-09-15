package com.view.plantview.sensor.api.application.downtime.delete;

import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;

import java.util.Objects;

public class DefaultDeleteDowntimeUseCase extends DeleteDowntimeUseCase {

    private final UserGateway gateway;

    public DefaultDeleteDowntimeUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public void execute(String anId) {
        this.gateway.deleteById(UserId.from(anId));
    }
}
