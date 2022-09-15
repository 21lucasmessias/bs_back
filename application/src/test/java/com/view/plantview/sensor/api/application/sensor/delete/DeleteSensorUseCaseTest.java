package com.view.plantview.sensor.api.application.sensor.delete;


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

@ExtendWith(MockitoExtension.class)
public class DeleteSensorUseCaseTest {

    @InjectMocks
    private DefaultDeleteSensorUseCase useCase;

    @Mock
    private SensorGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidSensorId_whenCallsDeleteSensor_thenShouldBeOk() {
        final var aSensor = Sensor.newSensor(
                "1",
                null
        );

        SensorId expectedId = aSensor.getId();

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAnInvalidSensorId_whenCallsDeleteSensor_thenShouldBeOk() {
        SensorId expectedId = SensorId.from("123");

        Mockito.doNothing().when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }

    @Test
    public void givenAValidSensorId_whenGatewayThrows_thenShouldReturnException() {
        final var aSensor = Sensor.newSensor(
                "1",
                null
        );

        SensorId expectedId = aSensor.getId();

        Mockito.doThrow(new IllegalStateException("gateway error")).when(gateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(gateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));
    }
}
