package com.view.plantview.sensor.api.application.downtime.update;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
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
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {

    @InjectMocks
    private DefaultUpdateDowntimeUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateDowntime_thenShouldReturnDowntimeId() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));
        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedDowntime -> Objects.equals(aUpdatedDowntime.getSensorId(), expectedSensorId) &&
                        Objects.equals(aUpdatedDowntime.getEquipment(), expectedEquipment) &&
                        Objects.equals(aUpdatedDowntime.getDate(), expectedDate) &&
                        Objects.equals(aUpdatedDowntime.getStart(), expectedStart) &&
                        Objects.equals(aUpdatedDowntime.getEnd(), expectedEnd) &&
                        Objects.equals(aUpdatedDowntime.getId(), expectedId)
        ));
    }

    @Test
    public void givenAValidDowntimeWithoutEquipment_whenCallsUpdateDowntime_thenShouldReturnDowntimeId() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));
        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedDowntime -> Objects.equals(aUpdatedDowntime.getSensorId(), expectedSensorId) &&
                        Objects.equals(aUpdatedDowntime.getEquipment(), expectedEquipment) &&
                        Objects.equals(aUpdatedDowntime.getDate(), expectedDate) &&
                        Objects.equals(aUpdatedDowntime.getStart(), expectedStart) &&
                        Objects.equals(aUpdatedDowntime.getEnd(), expectedEnd) &&
                        Objects.equals(aUpdatedDowntime.getId(), expectedId)
        ));
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithInvalidDowntimeDate_thenShouldReturnsDomainException() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final LocalDate expectedDate = null;
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));

        final var expectedErrorMessage = "'date' should not be null";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithInvalidDowntimeStart_thenShouldReturnsDomainException() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final var expectedDate = LocalDate.now();
        final Instant expectedStart = null;
        final var expectedEnd = Instant.now();

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));

        final var expectedErrorMessage = "'start' should not be null";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithInvalidDowntimeEnd_thenShouldReturnsDomainException() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final Instant expectedEnd = null;

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));

        final var expectedErrorMessage = "'end' should not be null";
        final var expectedErrorCount = 1;

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAValidDowntime_whenCallsUpdateDowntimeWithRandomGatewayError_thenShouldReturnsGatewayError() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plusSeconds(1);

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        final var expectedErrorMessage = "gateway error";
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aDowntime.clone()));
        when(gateway.update(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(1)).update(argThat(
                aUpdatedDowntime -> Objects.equals(aUpdatedDowntime.getSensorId(), expectedSensorId) &&
                        Objects.equals(aUpdatedDowntime.getEquipment(), expectedEquipment) &&
                        Objects.equals(aUpdatedDowntime.getDate(), expectedDate) &&
                        Objects.equals(aUpdatedDowntime.getStart(), expectedStart) &&
                        Objects.equals(aUpdatedDowntime.getEnd(), expectedEnd) &&
                        Objects.equals(aUpdatedDowntime.getId(), expectedId)
        ));
    }

    @Test
    public void givenAnInvalidId_whenCallsUpdateDowntime_thenShouldReturnsNotFoundException() {
        final var aDowntime = User.newDowntime(
                SensorId.from("2"),
                null,
                LocalDate.now(),
                Instant.now(),
                Instant.now().plusSeconds(1)
        );

        final var expectedSensorId = SensorId.from("1");
        final Equipment expectedEquipment = null;
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plusSeconds(1);

        final var expectedId = aDowntime.getId();

        final var aCommand = UpdateDowntimeCommand.with(
                expectedId.getValue(),
                expectedSensorId,
                expectedEquipment,
                expectedDate,
                expectedStart,
                expectedEnd
        );

        final var expectedErrorMessage = "Downtime with ID %s was not found.".formatted(expectedId.getValue());
        final var expectedErrorCount = 1;

        when(gateway.findById(eq(expectedId))).thenReturn(Optional.empty());

        final var exception = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, exception.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, exception.getErrors().size());

        Mockito.verify(gateway, times(1)).findById(eq(expectedId));
        Mockito.verify(gateway, times(0)).update(any());
    }
}
