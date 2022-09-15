package com.view.plantview.sensor.api.application.sensor.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.equipment.Equipment;

public record UpdateSensorCommand(
        String id,
        String idExternal,
        Equipment equipment,
        User user
) {

    public static UpdateSensorCommand with(
            final String anId,
            final String idExternal,
            final Equipment anEquipment,
            final User anUser
    ) {
        return new UpdateSensorCommand(
                anId,
                idExternal,
                anEquipment,
                anUser
        );
    }
}
