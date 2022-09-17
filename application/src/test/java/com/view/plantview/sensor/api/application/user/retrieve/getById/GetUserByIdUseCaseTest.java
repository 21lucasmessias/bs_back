package com.view.plantview.sensor.api.application.user.retrieve.getById;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {

    @InjectMocks
    private DefaultGetUserByIdUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidUserId_whenCallsGetByIdUser_thenShouldReturnUser() {
        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aUser = User.newUser(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        UserId expectedId = aUser.getId();

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aUser.clone()));

        final var actualUser = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(UserOutput.from(aUser), actualUser);
    }

    @Test
    public void givenAnInvalidUserId_whenCallsDeleteUser_thenShouldBeOk() {
        final var expectedErrorMessage = "User with ID 123 was not found.";
        final var expectedId = UserId.from("123");

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidUserId_whenGatewayThrows_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";

        final var expectedUniqueIdentifier = "10254094937";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aUser = User.newUser(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        UserId expectedId = aUser.getId();

        when(gateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
