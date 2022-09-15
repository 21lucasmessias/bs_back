package com.view.plantview.sensor.api.application.downtime.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;

public record UpdateDowntimeOutput(
        UserId id
) {

    public static UpdateDowntimeOutput from(final User user) {
        return new UpdateDowntimeOutput(user.getId());
    }
}
