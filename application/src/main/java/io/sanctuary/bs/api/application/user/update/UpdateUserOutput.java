package io.sanctuary.bs.api.application.user.update;

import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserId;

public record UpdateUserOutput(
        UserId id
) {

    public static UpdateUserOutput from(final User aUser) {
        return new UpdateUserOutput(aUser.getId());
    }
}
