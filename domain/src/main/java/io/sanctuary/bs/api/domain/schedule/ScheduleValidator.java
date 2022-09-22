package io.sanctuary.bs.api.domain.schedule;

import io.sanctuary.bs.api.domain.validation.Error;
import io.sanctuary.bs.api.domain.validation.ValidationHandler;
import io.sanctuary.bs.api.domain.validation.Validator;

public class ScheduleValidator extends Validator {

    private final Schedule schedule;

    protected ScheduleValidator(final Schedule aSchedule, final ValidationHandler aHandler) {
        super(aHandler);

        this.schedule = aSchedule;
    }

    @Override
    public void validate() {
        if (schedule.getBarber() == null) {
            this.validationHandler().append(new Error("'barber' should not be null"));
        }

        if (schedule.getClient() == null) {
            this.validationHandler().append(new Error("'client' should not be null"));
        }

        if (schedule.getStart() == null) {
            this.validationHandler().append(new Error("'start' should not be null"));
        }

        if (schedule.getEnd() == null) {
            this.validationHandler().append(new Error("'end' should not be null"));
        }

        if (schedule.getStatus() == null) {
            this.validationHandler().append(new Error("'status' should not be null"));
        }

        if (schedule.getScheduleType() == null) {
            this.validationHandler().append(new Error("'scheduleType' should not be null"));
        }

        if(schedule.getStart() != null && schedule.getEnd() != null) {
            if(schedule.getStart().isAfter(schedule.getEnd())) {
                this.validationHandler().append(new Error("'start' should be after 'end'"));
            }
        }
    }
}
