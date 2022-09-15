package com.view.plantview.sensor.api.application.sensor.update;

import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

public record UpdateSensorOutput(
        SensorId id
) {

    public static UpdateSensorOutput from(final Sensor sensor) {
        return new UpdateSensorOutput(sensor.getId());
    }
}
