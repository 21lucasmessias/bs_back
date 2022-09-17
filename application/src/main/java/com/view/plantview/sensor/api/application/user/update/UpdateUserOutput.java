package com.view.plantview.sensor.api.application.user.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;

public record UpdateUserOutput(
        UserId id
) {

    public static UpdateUserOutput from(final User aUser) {
        return new UpdateUserOutput(aUser.getId());
    }
}
