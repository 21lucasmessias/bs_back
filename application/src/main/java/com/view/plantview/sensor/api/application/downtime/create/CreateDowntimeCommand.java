package com.view.plantview.sensor.api.application.downtime.create;

import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

import java.time.Instant;
import java.time.LocalDate;

public record CreateDowntimeCommand(
        SensorId sensorId,
        Equipment equipment,
        LocalDate date,
        Instant start,
        Instant end
) {

    public static CreateDowntimeCommand with(
            SensorId aSensorId,
            Equipment anEquipment,
            LocalDate aDate,
            Instant aStart,
            Instant anEnd
    ) {
        return new CreateDowntimeCommand(aSensorId, anEquipment, aDate, aStart, anEnd);
    }
}
