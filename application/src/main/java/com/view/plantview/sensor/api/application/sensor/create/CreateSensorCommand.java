package com.view.plantview.sensor.api.application.sensor.create;

import com.view.plantview.sensor.api.domain.equipment.Equipment;

public record CreateSensorCommand(
        String externalId,
        Equipment equipment
) {

    public static CreateSensorCommand with(final String externalId) {
        return new CreateSensorCommand(externalId, null);
    }

    public static CreateSensorCommand with(final String externalId, final Equipment equipment) {
        return new CreateSensorCommand(externalId, equipment);
    }
}
