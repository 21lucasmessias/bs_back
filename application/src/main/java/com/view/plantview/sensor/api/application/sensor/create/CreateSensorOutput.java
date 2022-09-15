package com.view.plantview.sensor.api.application.sensor.create;

import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

public record CreateSensorOutput(
        SensorId id
) {

    public static CreateSensorOutput from(final Sensor aSensor) {
        return new CreateSensorOutput(aSensor.getId());
    }
}
