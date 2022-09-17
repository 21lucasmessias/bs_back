package com.view.plantview.sensor.api.application.user.retrieve.list;

import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserId;

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
