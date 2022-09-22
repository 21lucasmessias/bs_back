package io.sanctuary.bs.api.application.user.retrieve.list;

import io.sanctuary.bs.api.domain.user.Role;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserId;

public record UserListOuput(
        UserId id,
        String uniqueIdentifier,
        String name,
        String phoneNumber,
        String email,
        Role role
) {

    public static UserListOuput from(final User aUser) {
        return new UserListOuput(
                aUser.getId(),
                aUser.getUniqueIdentifier(),
                aUser.getName(),
                aUser.getPhoneNumber(),
                aUser.getEmail(),
                aUser.getRole()
        );
    }
}
