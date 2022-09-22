package io.sanctuary.bs.api.application.user.update;


public record UpdateUserCommand(
        String id,
        String name,
        String email,
        String phoneNumber,
        String password
) {

    public static UpdateUserCommand with(
            final String anId,
            final String aName,
            final String anEmail,
            final String aPhoneNumber,
            final String aPassword
    ) {
        return new UpdateUserCommand(
                anId,
                aName,
                anEmail,
                aPhoneNumber,
                aPassword
        );
    }
}
