package com.view.plantview.sensor.api.application.downtime.retrieve.getById;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserId;
import com.view.plantview.sensor.api.domain.exceptions.DomainException;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {

    @InjectMocks
    private DefaultGetDowntimeByIdUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidDowntimeId_whenCallsGetByIdDowntime_thenShouldReturnDowntime() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        UserId expectedId = aDowntime.getId();

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aDowntime.clone()));

        final var actualDowntime = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(DowntimeOutput.from(aDowntime), actualDowntime);
    }

    @Test
    public void givenAnInvalidDowntimeId_whenCallsDeleteDowntime_thenShouldBeOk() {
        final var expectedErrorMessage = "Downtime with ID 123 was not found.";
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
    public void givenAValidDowntimeId_whenGatewayThrows_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        UserId expectedId = aDowntime.getId();

        when(gateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
