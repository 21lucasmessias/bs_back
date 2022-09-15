package com.view.plantview.sensor.api.application.downtime.retrieve.list;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.SensorId;

import java.time.Instant;
import java.time.LocalDate;

public record DowntimeListOutput(
        UserId id,
        SensorId sensorId,
        Equipment equipment,
        LocalDate date,
        Instant start,
        Instant end
) {

    public static DowntimeListOutput from(User aUser) {
        return new DowntimeListOutput(
                aUser.getId(),
                aUser.getSensorId(),
                aUser.getEquipment(),
                aUser.getDate(),
                aUser.getStart(),
                aUser.getEnd()
        );
    }
}
