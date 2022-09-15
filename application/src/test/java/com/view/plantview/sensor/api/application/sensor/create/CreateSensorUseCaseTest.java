package com.view.plantview.sensor.api.application.sensor.create;

import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
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
public class CreateSensorUseCaseTest {

    @InjectMocks
    private DefaultCreateSensorUseCase useCase;

    @Mock
    private SensorGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateSensor_thenShouldReturnSensorId() {
        final var expectedExternalId = "1";
        final var expectedEquipment = new Equipment("", "", "");

        final var aCommand = CreateSensorCommand.with(expectedExternalId, expectedEquipment);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        verify(gateway, times(1)).create(argThat(aSensor ->
                Objects.equals(aSensor.getIdExternal(), expectedExternalId) &&
                        Objects.equals(aSensor.getEquipment(), expectedEquipment) &&
                        Objects.nonNull(aSensor.getId())));
    }

    @Test
    public void givenAValidCommandWithoutEquipment_whenCallsCreateSensor_thenShouldReturnSensorId() {
        final var expectedExternalId = "1";

        final var aCommand = CreateSensorCommand.with(expectedExternalId);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        verify(gateway, times(1)).create(argThat(aSensor ->
                Objects.equals(aSensor.getIdExternal(), expectedExternalId) && Objects.nonNull(aSensor.getId())));
    }

    @Test
    public void givenAnInvalidExternalId_whenCallsCreateSensor_thenShouldReturnDomainException() {
        final var expectedExternalId = "  ";
        final var expectedErrorMessage = "'idExternal' should not be empty";
        final var expectedErrorCount = 1;

        final var aCommand = CreateSensorCommand.with(expectedExternalId);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommandWithNullEquipment_whenCallsCreateSensor_thenShouldReturnDomainException() {
        final String expectedExternalId = null;
        final var expectedErrorMessage = "'idExternal' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateSensorCommand.with(expectedExternalId);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
        final String expectedExternalId = "1";
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateSensorCommand.with(expectedExternalId);

        when(gateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(1)).create(argThat(aSensor ->
                Objects.equals(aSensor.getIdExternal(), expectedExternalId) && Objects.nonNull(aSensor.getId())));
    }

}
