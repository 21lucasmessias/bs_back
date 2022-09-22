package io.sanctuary.bs.api.application.user.retrieve.getById;

import io.sanctuary.bs.api.domain.user.Role;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserId;

public record UserOutput(
        UserId id,
        String uniqueIdentifier,
        String name,
        String phoneNumber,
        String email,
        Role role
) {

    public static UserOutput from(final User aUser) {
        return new UserOutput(
                aUser.getId(),
                aUser.getUniqueIdentifier(),
                aUser.getName(),
                aUser.getPhoneNumber(),
                aUser.getEmail(),
                aUser.getRole()
        );
    }
}
