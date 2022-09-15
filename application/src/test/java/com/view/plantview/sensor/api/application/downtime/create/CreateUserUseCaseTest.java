package com.view.plantview.sensor.api.application.downtime.create;

import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.equipment.Equipment;
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

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @InjectMocks
    private DefaultCreateDowntimeUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidDowntime_whenCallsCreateDowntime_thenShouldReturnDowntimeId() {
        final var expectedSensorId = SensorId.from("1");
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        verify(gateway, times(1)).create(
                argThat(aDowntime ->
                        Objects.equals(aDowntime.getSensorId(), expectedSensorId) &&
                                Objects.equals(aDowntime.getEquipment(), expectedEquipment) &&
                                Objects.equals(aDowntime.getDate(), expectedDate) &&
                                Objects.equals(aDowntime.getStart(), expectedStart) &&
                                Objects.equals(aDowntime.getEnd(), expectedEnd) &&
                                Objects.nonNull(aDowntime.getId())
                ));
    }

    @Test
    public void givenAValidDowntimeWithoutEquipment_whenCallsCreateDowntime_thenShouldReturnDowntimeId() {
        final Equipment expectedEquipment = null;
        final var expectedSensorId = SensorId.from("1");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        when(gateway.create(any())).thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand).get();

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        verify(gateway, times(1)).create(
                argThat(aDowntime ->
                        Objects.equals(aDowntime.getSensorId(), expectedSensorId) &&
                                Objects.equals(aDowntime.getEquipment(), expectedEquipment) &&
                                Objects.equals(aDowntime.getDate(), expectedDate) &&
                                Objects.equals(aDowntime.getStart(), expectedStart) &&
                                Objects.equals(aDowntime.getEnd(), expectedEnd) &&
                                Objects.nonNull(aDowntime.getId())
                ));
    }

    @Test
    public void givenAnInvalidSensorId_whenCallsCreateDowntime_thenShouldThrowsDomainException() {
        final SensorId expectedSensorId = null;
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedErrorMessage = "'sensorId' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidDate_whenCallsCreateDowntime_thenShouldThrowsDomainException() {
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedSensorId = SensorId.from("1");
        final LocalDate expectedDate = null;
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedErrorMessage = "'date' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidStart_whenCallsCreateDowntime_thenShouldThrowsDomainException() {
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedSensorId = SensorId.from("1");
        final var expectedDate = LocalDate.now();
        final Instant expectedStart = null;
        final var expectedEnd = Instant.now();

        final var expectedErrorMessage = "'start' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEnd_whenCallsCreateDowntime_thenShouldThrowsDomainException() {
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedSensorId = SensorId.from("1");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final Instant expectedEnd = null;

        final var expectedErrorMessage = "'end' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnValidCommand_whenGatewayThrowsRandomException_thenShouldReturnException() {
        final var expectedEquipment = new Equipment("", "", "");
        final var expectedSensorId = SensorId.from("1");
        final var expectedDate = LocalDate.now();
        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now();

        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDowntimeCommand.with(expectedSensorId, expectedEquipment, expectedDate, expectedStart, expectedEnd);

        when(gateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notificaton = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notificaton.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notificaton.getErrors().size());

        verify(gateway, times(1)).create(
                argThat(aDowntime ->
                        Objects.equals(aDowntime.getSensorId(), expectedSensorId) &&
                                Objects.equals(aDowntime.getEquipment(), expectedEquipment) &&
                                Objects.equals(aDowntime.getDate(), expectedDate) &&
                                Objects.equals(aDowntime.getStart(), expectedStart) &&
                                Objects.equals(aDowntime.getEnd(), expectedEnd) &&
                                Objects.nonNull(aDowntime.getId())
                ));
    }
}
