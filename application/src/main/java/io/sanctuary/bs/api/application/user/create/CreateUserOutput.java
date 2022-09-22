package io.sanctuary.bs.api.application.user.create;

import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserId;

public record CreateUserOutput(
        UserId id
) {

    public static CreateUserOutput from(final User aUser) {
        return new CreateUserOutput(aUser.getId());
    }
}
