package com.view.plantview.sensor.api.application.user.create;


import com.view.plantview.sensor.api.domain.user.Role;

public record CreateUserCommand(
        String uniqueIdentifier,
        String name,
        String phoneNumber,
        String email,
        Role role,
        String password
) {

    public static CreateUserCommand with(
            final String aName,
            final String aPhoneNumber,
            final String aEmail,
            final String aUniqueIdentifier,
            final Role aRole,
            final String aPassword
    ) {
        return new CreateUserCommand(
                aName,
                aPhoneNumber,
                aEmail,
                aUniqueIdentifier,
                aRole,
                aPassword
        );
    }
}
