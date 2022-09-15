package com.view.plantview.sensor.api.application.downtime.update;

import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

import java.time.Instant;
import java.time.LocalDate;

public record UpdateDowntimeCommand(
        String id,
        SensorId sensorId,
        Equipment equipment,
        LocalDate date,
        Instant start,
        Instant end
) {

    public static UpdateDowntimeCommand with(
            final String anId,
            final SensorId aSensorId,
            final Equipment anEquipment,
            final LocalDate aDate,
            final Instant aStart,
            final Instant anEnd
    ) {
        return new UpdateDowntimeCommand(
                anId,
                aSensorId,
                anEquipment,
                aDate,
                aStart,
                anEnd
        );
    }
}
