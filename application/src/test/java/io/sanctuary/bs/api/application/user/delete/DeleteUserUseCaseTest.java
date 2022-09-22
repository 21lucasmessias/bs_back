package io.sanctuary.bs.api.application.user.delete;

import io.sanctuary.bs.api.domain.user.Role;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.user.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @InjectMocks
    private DefaultDeleteUserUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidUserId_whenCallsDeleteUser_thenShouldBeOk() {
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

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAnInvalidUserId_whenCallsDeleteUser_thenShouldBeOk() {
        UserId expectedId = UserId.from("123");

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAValidUserId_whenGatewayThrows_thenShouldReturnException() {
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

        Mockito.doThrow(new IllegalStateException("gateway error")).when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }
}
