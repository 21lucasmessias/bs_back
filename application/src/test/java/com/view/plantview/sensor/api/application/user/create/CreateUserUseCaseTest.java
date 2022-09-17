package com.view.plantview.sensor.api.application.user.create;

import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @InjectMocks
    private DefaultCreateUserUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateUser_thenShouldReturnUserId() {
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        verify(gateway, times(1)).create(argThat(aUser ->
                Objects.equals(aUser.getUniqueIdentifier(), expectedUniqueIdentifier) &&
                        Objects.equals(aUser.getName(), expectedName) &&
                        Objects.equals(aUser.getPhoneNumber(), expectedPhoneNumber) &&
                        Objects.equals(aUser.getEmail(), expectedEmail) &&
                        Objects.equals(aUser.getRole(), expectedRole) &&
                        Objects.equals(aUser.getPassword(), expectedPassword) &&
                        Objects.nonNull(aUser.getId())));
    }

    @Test
    public void givenAnInvalidUniqueIdentifier_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'uniqueIdentifier' should not be empty";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullUniqueIdentifier_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'uniqueIdentifier' should not be null";
        final var expectedErrorCount = 1;

        final String expectedUniqueIdentifier = null;
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullName_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final String expectedName = null;
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidPhoneNumber_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'phoneNumber' should not be empty";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullPhoneNumber_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'phoneNumber' should not be null";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final String expectedPhoneNumber = null;
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEmail_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'email' should not be empty";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final String expectedEmail = "";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullEmail_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'email' should not be null";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final String expectedEmail = null;
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullRole_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'role' should not be null";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final Role expectedRole = null;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidPassword_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'password' length should be greater than 5";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final String expectedPassword = "";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullPassword_whenCallsCreateUser_thenShouldReturnDomainException() {
        final var expectedErrorMessage = "'password' should not be null";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final String expectedPassword = null;

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "42988442441";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aCommand = CreateUserCommand.with(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        when(gateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(1)).create(argThat(aUser ->
                Objects.equals(aUser.getUniqueIdentifier(), expectedUniqueIdentifier) &&
                        Objects.equals(aUser.getName(), expectedName) &&
                        Objects.equals(aUser.getPhoneNumber(), expectedPhoneNumber) &&
                        Objects.equals(aUser.getEmail(), expectedEmail) &&
                        Objects.equals(aUser.getRole(), expectedRole) &&
                        Objects.equals(aUser.getPassword(), expectedPassword) &&
                        Objects.nonNull(aUser.getId())));
    }

}
