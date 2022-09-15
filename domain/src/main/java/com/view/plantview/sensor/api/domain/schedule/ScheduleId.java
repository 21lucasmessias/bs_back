package com.view.plantview.sensor.api.domain.schedule;

import com.view.plantview.sensor.api.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class ScheduleId extends Identifier {
    private final String value;

    private ScheduleId(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ScheduleId unique() {
        return ScheduleId.from(UUID.randomUUID());
    }

    public static ScheduleId from(final String anId) {
        return new ScheduleId(anId);
    }

    public static ScheduleId from(final UUID uuid) {
        return new ScheduleId(uuid.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ScheduleId sensorId = (ScheduleId) o;
        return getValue().equals(sensorId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
