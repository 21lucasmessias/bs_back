package com.view.plantview.sensor.api.domain.user;

import com.view.plantview.sensor.api.domain.AggregateRoot;
import com.view.plantview.sensor.api.domain.validation.ValidationHandler;

public class User extends AggregateRoot<UserId> implements Cloneable {

    private final String uniqueIdentifier;
    private String name;
    private String phoneNumber;
    private String email;
    private Role role;
    private String password;

    private User(
            final UserId anId,
            final String aUniqueIdentifier,
            final String aName,
            final String aPhoneNumber,
            final String aEmail,
            final Role aRole,
            final String aPassword
    ) {
        super(anId);
        this.uniqueIdentifier = aUniqueIdentifier;
        this.name = aName;
        this.phoneNumber = aPhoneNumber;
        this.email = aEmail;
        this.role = aRole;
        this.password = aPassword;
    }

    public static User newUser(
            final String aUniqueIdentifier,
            final String aName,
            final String aPhoneNumber,
            final String aEmail,
            final Role aRole,
            final String aPassword
    ) {
        final var id = UserId.unique();

        return new User(
                id,
                aUniqueIdentifier,
                aName,
                aPhoneNumber,
                aEmail,
                aRole,
                aPassword
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public User update(
            final String aName,
            final String aPhoneNumber,
            final String aEmail,
            final String aPassword
    ) {
        this.name = aName;
        this.phoneNumber = aPhoneNumber;
        this.email = aEmail;
        this.password = aPassword;

        return this;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
