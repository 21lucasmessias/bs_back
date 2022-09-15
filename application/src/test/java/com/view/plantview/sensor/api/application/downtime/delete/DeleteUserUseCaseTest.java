package com.view.plantview.sensor.api.application.downtime.delete;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.sensor.SensorId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @InjectMocks
    private DefaultDeleteDowntimeUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidDowntimeId_whenCallsDeleteDowntime_thenShouldBeOk() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        UserId expectedId = aDowntime.getId();

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAnInvalidDowntimeId_whenCallsDeleteDowntime_thenShouldBeOk() {
        UserId expectedId = UserId.from("123");

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAValidDowntimeId_whenGatewayThrows_thenShouldReturnException() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        UserId expectedId = aDowntime.getId();

        Mockito.doThrow(new IllegalStateException("gateway error")).when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }
}
