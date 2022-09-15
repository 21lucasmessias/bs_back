package com.view.plantview.sensor.api.application.sensor.retrieve.getById;

import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorId;
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
public class GetSensorByIdUseCaseTest {

    @InjectMocks
    private DefaultGetSensorByIdUseCase useCase;

    @Mock
    private SensorGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidSensorId_whenCallsGetByIdSensor_thenShouldReturnSensor() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        SensorId expectedId = aSensor.getId();

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aSensor.clone()));

        final var actualSensor = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(SensorOutput.from(aSensor), actualSensor);
    }

    @Test
    public void givenAnInvalidSensorId_whenCallsDeleteSensor_thenShouldBeOk() {
        final var expectedErrorMessage = "Sensor with ID 123 was not found.";
        final var expectedId = SensorId.from("123");

        when(gateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidSensorId_whenGatewayThrows_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        SensorId expectedId = aSensor.getId();

        when(gateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
