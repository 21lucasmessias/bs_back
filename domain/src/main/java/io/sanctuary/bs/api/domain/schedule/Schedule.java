package io.sanctuary.bs.api.domain.schedule;

import io.sanctuary.bs.api.domain.AggregateRoot;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.validation.ValidationHandler;

import java.time.Instant;

public class Schedule extends AggregateRoot<ScheduleId> implements Cloneable {

    private final User barber;
    private User client;
    private Instant start;
    private Instant end;
    private ScheduleStatus status;
    private String description;
    private ScheduleType scheduleType;

    private Schedule(
            final ScheduleId anId,
            final User aBarber,
            final User aClient,
            final Instant aStart,
            final Instant aEnd,
            final ScheduleStatus aStatus,
            final String aDescription,
            final ScheduleType aScheduleType
    ) {
        super(anId);

        this.barber = aBarber;
        this.client = aClient;
        this.start = aStart;
        this.end = aEnd;
        this.status = aStatus;
        this.description = aDescription;
        this.scheduleType = aScheduleType;
    }

    public static Schedule newSchedule(
            final User aBarber,
            final User aClient,
            final Instant aStart,
            final Instant aEnd,
            final ScheduleStatus aStatus,
            final String aDescription,
            final ScheduleType aScheduleType
    ) {
        final var id = ScheduleId.unique();

        return new Schedule(
                id,
                aBarber,
                aClient,
                aStart,
                aEnd,
                aStatus,
                aDescription,
                aScheduleType
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ScheduleValidator(this, handler).validate();
    }

    public Schedule update(
            final User aClient,
            final Instant aStart,
            final Instant aEnd,
            final ScheduleStatus aStatus,
            final String aDescription,
            final ScheduleType aScheduleType
    ) {
        this.client = aClient;
        this.start = aStart;
        this.end = aEnd;
        this.status = aStatus;
        this.description = aDescription;
        this.scheduleType = aScheduleType;

        return this;
    }

    @Override
    public Schedule clone() {
        try {
            return (Schedule) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getDescription() {
        return description;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public User getBarber() {
        return barber;
    }

    public User getClient() {
        return client;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public ScheduleStatus getStatus() {
        return status;
    }
}
