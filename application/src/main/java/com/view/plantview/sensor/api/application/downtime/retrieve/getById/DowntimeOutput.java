package com.view.plantview.sensor.api.application.downtime.retrieve.getById;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

import java.time.Instant;
import java.time.LocalDate;

public record DowntimeOutput(
        UserId id,
        SensorId sensorId,
        Equipment equipment,
        LocalDate date,
        Instant start,
        Instant end
) {

    public static DowntimeOutput from(final User aUser) {
        return new DowntimeOutput(
                aUser.getId(),
                aUser.getSensorId(),
                aUser.getEquipment(),
                aUser.getDate(),
                aUser.getStart(),
                aUser.getEnd()
        );
    }
}
