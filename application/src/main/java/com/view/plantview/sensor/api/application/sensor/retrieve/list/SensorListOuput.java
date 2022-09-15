package com.view.plantview.sensor.api.application.sensor.retrieve.list;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

public record SensorListOuput(
        SensorId id,
        String idExternal,
        Equipment equipment,
        User user
) {

    public static SensorListOuput from(final Sensor aSensor) {
        return new SensorListOuput(
                aSensor.getId(),
                aSensor.getIdExternal(),
                aSensor.getEquipment(),
                aSensor.getDowntime()
        );
    }
}
