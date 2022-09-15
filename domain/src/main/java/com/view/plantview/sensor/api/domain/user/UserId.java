package com.view.plantview.sensor.api.domain.user;

import com.view.plantview.sensor.api.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class UserId extends Identifier {
    private final String value;

    private UserId(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static UserId unique() {
        return UserId.from(UUID.randomUUID());
    }

    public static UserId from(final String anId) {
        return new UserId(anId);
    }

    public static UserId from(final UUID uuid) {
        return new UserId(uuid.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserId sensorId = (UserId) o;
        return getValue().equals(sensorId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
