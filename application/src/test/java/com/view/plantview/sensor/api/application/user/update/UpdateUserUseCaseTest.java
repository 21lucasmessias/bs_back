package com.view.plantview.sensor.api.application.user.update;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.user.User;
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
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {

    @InjectMocks
    private DefaultUpdateUserUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateUser_thenShouldReturnUserId() {
        final var givenUniqueIdentifier = "10254094937";
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var aUser = User.newUser(givenUniqueIdentifier, givenName, givenPhoneNumber, givenEmail, givenRole, givenPassword);

        final var expectedId = aUser.getId();

        final var expectedName = "Lucass";
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedPassword = "123456789";

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedEmail,
                expectedPhoneNumber,
                expectedPassword
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));
        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedUser ->
                        Objects.equals(aUpdatedUser.getName(), expectedName) &&
                                Objects.equals(aUpdatedUser.getPhoneNumber(), expectedPhoneNumber) &&
                                Objects.equals(aUpdatedUser.getEmail(), expectedEmail) &&
                                Objects.equals(aUpdatedUser.getPassword(), expectedPassword) &&
                                Objects.equals(aUpdatedUser.getId(), expectedId)
        ));
    }

    @Test
    public void givenAValidUser_whenCallsUpdateUserWithNullName_thenShouldReturnsDomainException() {
        final var givenUniqueIdentifier = "10254094937";
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var aUser = User.newUser(givenUniqueIdentifier, givenName, givenPhoneNumber, givenEmail, givenRole, givenPassword);

        final String expectedName = null;
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedPassword = "123456789";

        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedPassword
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));

        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidUser_whenCallsUpdateUserWithInvalidName_thenShouldReturnsDomainException() {
        final var givenUniqueIdentifier = "10254094937";
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var aUser = User.newUser(givenUniqueIdentifier, givenName, givenPhoneNumber, givenEmail, givenRole, givenPassword);

        final String expectedName = "";
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedPassword = "123456789";

        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedPassword
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));

        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithRandomGatewayError_thenShouldReturnsGatewayError() {
        final var givenUniqueIdentifier = "10254094937";
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var aUser = User.newUser(givenUniqueIdentifier, givenName, givenPhoneNumber, givenEmail, givenRole, givenPassword);

        final String expectedName = "Lucass";
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedPassword = "123456789";

        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedEmail,
                expectedPhoneNumber,
                expectedPassword
        );

        final var expectedErrorMessage = "gateway error";
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));
        when(gateway.update(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedUser ->
                        Objects.equals(aUpdatedUser.getUniqueIdentifier(), givenUniqueIdentifier) &&
                                Objects.equals(aUpdatedUser.getRole(), givenRole) &&
                                Objects.equals(aUpdatedUser.getName(), expectedName) &&
                                Objects.equals(aUpdatedUser.getPhoneNumber(), expectedPhoneNumber) &&
                                Objects.equals(aUpdatedUser.getEmail(), expectedEmail) &&
                                Objects.equals(aUpdatedUser.getPassword(), expectedPassword) &&
                                Objects.equals(aUpdatedUser.getId(), expectedId)
        ));
    }

    @Test
    public void givenAnInvalidId_whenCallsUpdateDowntime_thenShouldReturnsNotFoundException() {
        final var givenUniqueIdentifier = "10254094937";
        final var givenName = "Lucas";
        final var givenPhoneNumber = "42988442441";
        final var givenEmail = "21.lucasmessias@gmail.com";
        final var givenRole = Role.OWNER;
        final var givenPassword = "123456";

        final var aUser = User.newUser(givenUniqueIdentifier, givenName, givenPhoneNumber, givenEmail, givenRole, givenPassword);

        final String expectedName = "";
        final var expectedPhoneNumber = "42988442442";
        final var expectedEmail = "22.lucasmessias@gmail.com";
        final var expectedPassword = "123456789";

        final var expectedId = aUser.getId();

        final var aCommand = UpdateUserCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedPassword
        );

        final var expectedErrorMessage = "User with ID %s was not found.".formatted(expectedId.getValue());
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(0)).update(any());
    }
}
