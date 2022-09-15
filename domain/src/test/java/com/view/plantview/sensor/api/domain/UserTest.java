package com.view.plantview.sensor.api.domain;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void givenAValidParams_whenCallNewUser_thenInstantiateAUser() {
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertEquals(expectedPhoneNumber, actualUser.getPhoneNumber());
        Assertions.assertEquals(expectedEmail, actualUser.getEmail());
        Assertions.assertEquals(expectedUniqueIdentifier, actualUser.getUniqueIdentifier());
        Assertions.assertEquals(expectedRole, actualUser.getRole());
    }

    @Test
    public void givenAnInvalidName_whenCallNewUser_thenThrowException() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final String expectedName = null;
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidPhoneNumber_whenCallNewUser_thenThrowException() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'phone number' should not be null";

        final var expectedName = "Lucas";
        final String expectedPhoneNumber = null;
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmail_whenCallNewUser_thenThrowException() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be null";

        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final String expectedEmail = null;
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidUniqueIdentifier_whenCallNewUser_thenThrowException() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'unique identifier' should not be null";

        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final String expectedUniqueIdentifier = null;
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidPassword_whenCallNewUser_thenThrowException() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'password' should not be null";

        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedRole = Role.OWNER;
        final String expectedPassword = null;

        final var actualUser = User.newUser(expectedName, expectedPhoneNumber, expectedEmail, expectedUniqueIdentifier, expectedRole, expectedPassword);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidUser_whenCallUpdateUser_thenShouldReturnUpdatedUser() {
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenUniqueIdentifier = "10254094937";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var actualUser = User.newUser(givenName, givenPhoneNumber, givenEmail, givenUniqueIdentifier, givenRole, givenPassword);

        final var expectedName = "Lucass";
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedRole = Role.BARBER;
        final var expectedPassword = "123456789";

        actualUser.update(expectedName, expectedPhoneNumber, expectedEmail, expectedRole, expectedPassword);

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedName, actualUser.getName());
        Assertions.assertEquals(expectedPhoneNumber, actualUser.getPhoneNumber());
        Assertions.assertEquals(expectedEmail, actualUser.getEmail());
        Assertions.assertEquals(expectedRole, actualUser.getRole());
        Assertions.assertEquals(expectedPassword, actualUser.getPassword());
    }
}
