package com.view.plantview.sensor.api.application.sensor.delete;

import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

import java.util.Objects;

public class DefaultDeleteSensorUseCase extends DeleteSensorUseCase {
    private final SensorGateway gateway;

    public DefaultDeleteSensorUseCase(final SensorGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public void execute(String anId) {
        gateway.deleteById(SensorId.from(anId));
    }
}
