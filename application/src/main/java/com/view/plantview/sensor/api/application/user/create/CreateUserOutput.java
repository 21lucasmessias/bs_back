package com.view.plantview.sensor.api.application.user.create;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;

public record CreateUserOutput(
        UserId id
) {

    public static CreateUserOutput from(final User aUser) {
        return new CreateUserOutput(aUser.getId());
    }
}
