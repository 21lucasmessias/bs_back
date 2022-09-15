package com.view.plantview.sensor.api.application.sensor.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
import com.view.plantview.sensor.api.domain.exceptions.DomainException;
import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
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
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateSensorUseCaseTest {

    @InjectMocks
    private DefaultUpdateSensorUseCase useCase;

    @Mock
    private SensorGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateSensor_thenShouldReturnSensorId() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        final var expectedExternalId = "1";
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();
        final var expectedDowntime = User.newDowntime(aSensor.getId(), expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var expectedId = aSensor.getId();

        final var aCommand = UpdateSensorCommand.with(
                expectedId.getValue(),
                expectedExternalId,
                expectedEquipment,
                expectedDowntime
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aSensor.clone()));
        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedDowntime -> Objects.equals(aUpdatedDowntime.getIdExternal(), expectedExternalId) &&
                        Objects.equals(aUpdatedDowntime.getEquipment(), expectedEquipment) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getDate(), expectedDate) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getStart(), expectedStart) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getEnd(), expectedEnd) &&
                        Objects.equals(aUpdatedDowntime.getId(), expectedId)
        ));
    }

    @Test
    public void givenAValidSensor_whenCallsUpdateDowntimeWithNullExternalId_thenShouldReturnsDomainException() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        final String expectedExternalId = null;
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();
        final var expectedDowntime = User.newDowntime(aSensor.getId(), expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var expectedId = aSensor.getId();

        final var aCommand = UpdateSensorCommand.with(
                expectedId.getValue(),
                expectedExternalId,
                expectedEquipment,
                expectedDowntime
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aSensor.clone()));

        final var expectedErrorMessage = "'idExternal' should not be null";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidSensor_whenCallsUpdateDowntimeWithInvalidExternalId_thenShouldReturnsDomainException() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        final String expectedExternalId = "  ";
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();
        final var expectedDowntime = User.newDowntime(aSensor.getId(), expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var expectedId = aSensor.getId();

        final var aCommand = UpdateSensorCommand.with(
                expectedId.getValue(),
                expectedExternalId,
                expectedEquipment,
                expectedDowntime
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aSensor.clone()));

        final var expectedErrorMessage = "'idExternal' should not be empty";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithRandomGatewayError_thenShouldReturnsGatewayError() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        final String expectedExternalId = "1";
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();
        final var expectedDowntime = User.newDowntime(aSensor.getId(), expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var expectedId = aSensor.getId();

        final var aCommand = UpdateSensorCommand.with(
                expectedId.getValue(),
                expectedExternalId,
                expectedEquipment,
                expectedDowntime
        );

        final var expectedErrorMessage = "gateway error";
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aSensor.clone()));
        when(gateway.update(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedDowntime -> Objects.equals(aUpdatedDowntime.getIdExternal(), expectedExternalId) &&
                        Objects.equals(aUpdatedDowntime.getEquipment(), expectedEquipment) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getDate(), expectedDate) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getStart(), expectedStart) &&
                        Objects.equals(aUpdatedDowntime.getDowntime().getEnd(), expectedEnd) &&
                        Objects.equals(aUpdatedDowntime.getId(), expectedId)
        ));
    }

    @Test
    public void givenAnInvalidId_whenCallsUpdateDowntime_thenShouldReturnsNotFoundException() {
        final var aSensor = Sensor.newSensor(
                "2",
                null
        );

        final String expectedExternalId = "  ";
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();
        final var expectedDowntime = User.newDowntime(aSensor.getId(), expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var expectedId = aSensor.getId();

        final var aCommand = UpdateSensorCommand.with(
                expectedId.getValue(),
                expectedExternalId,
                expectedEquipment,
                expectedDowntime
        );

        final var expectedErrorMessage = "Sensor with ID %s was not found.".formatted(expectedId.getValue());
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(0)).update(any());
    }
}
