package com.view.plantview.sensor.api.application.downtime.create;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;

public record CreateDowntimeOutput(
        UserId id
) {
    
    public static CreateDowntimeOutput from(User user) {
        return new CreateDowntimeOutput(user.getId());
    }
}
