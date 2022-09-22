package io.sanctuary.bs.api.domain;

import io.sanctuary.bs.api.domain.exceptions.DomainException;
import io.sanctuary.bs.api.domain.schedule.Schedule;
import io.sanctuary.bs.api.domain.schedule.ScheduleStatus;
import io.sanctuary.bs.api.domain.schedule.ScheduleType;
import io.sanctuary.bs.api.domain.user.Role;
import io.sanctuary.bs.api.domain.user.User;
import io.sanctuary.bs.api.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ScheduleTest {

    @Test
    public void givenAValidParams_whenCallNewSchedule_thenInstantiateASchedule() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        Assertions.assertNotNull(actualSchedule);
        Assertions.assertEquals(expectedBarber, actualSchedule.getBarber());
        Assertions.assertEquals(expectedClient, actualSchedule.getClient());
        Assertions.assertEquals(expectedStart, actualSchedule.getStart());
        Assertions.assertEquals(expectedEnd, actualSchedule.getEnd());
        Assertions.assertEquals(expectedStatus, actualSchedule.getStatus());
        Assertions.assertEquals(expectedDescription, actualSchedule.getDescription());
        Assertions.assertEquals(expectedScheduleType, actualSchedule.getScheduleType());
    }

    @Test
    public void givenAnInvalidClient_whenCallNewSchedule_thenThrowException() {
        final User expectedBarber = null;

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'barber' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidBarber_whenCallNewSchedule_thenThrowException() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final User expectedClient = null;

        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'client' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidStart_whenCallNewSchedule_thenThrowException() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final Instant expectedStart = null;
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'start' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEnd_whenCallNewSchedule_thenThrowException() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final var expectedStart = Instant.now();
        final Instant expectedEnd = null;
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'end' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidStatus_whenCallNewSchedule_thenThrowException() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final ScheduleStatus expectedStatus = null;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'status' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidScheduleType_whenCallNewSchedule_thenThrowException() {
        final var expectedClientName = "Client";
        final var expectedClientPhoneNumber = "42988442441";
        final var expectedClientEmail = "client@gmail.com";
        final var expectedClientUniqueIdentifier = "client";
        final var expectedClientRole = Role.CLIENT;
        final var expectedClientPassword = "123456";

        final var expectedBarber = User.newUser(
                expectedClientName,
                expectedClientPhoneNumber,
                expectedClientEmail,
                expectedClientUniqueIdentifier,
                expectedClientRole,
                expectedClientPassword
        );

        final var expectedBarberName = "Barber";
        final var expectedBarberPhoneNumber = "42988442441";
        final var expectedBarberEmail = "barber@gmail.com";
        final var expectedBarberUniqueIdentifier = "barber";
        final var expectedBarberRole = Role.BARBER;
        final var expectedBarberPassword = "123456";

        final var expectedClient = User.newUser(
                expectedBarberName,
                expectedBarberPhoneNumber,
                expectedBarberEmail,
                expectedBarberUniqueIdentifier,
                expectedBarberRole,
                expectedBarberPassword
        );

        final var expectedStart = Instant.now();
        final var expectedEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.ACTIVE;
        final var expectedDescription = "";
        final ScheduleType expectedScheduleType = null;

        final var actualSchedule = Schedule.newSchedule(
                expectedBarber,
                expectedClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'scheduleType' should not be null";

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualSchedule.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidSchedule_whenCallUpdateSchedule_thenShouldReturnUpdatedSchedule() {
        final var givenBarberName = "Barber";
        final var givenBarberPhoneNumber = "42988442441";
        final var givenBarberEmail = "barber@gmail.com";
        final var givenBarberUniqueIdentifier = "barber";
        final var givenBarberRole = Role.BARBER;
        final var givenBarberPassword = "123456";

        final var givenBarber = User.newUser(
                givenBarberName,
                givenBarberPhoneNumber,
                givenBarberEmail,
                givenBarberUniqueIdentifier,
                givenBarberRole,
                givenBarberPassword
        );

        final var givenClientName = "Client";
        final var givenClientPhoneNumber = "42988442441";
        final var givenClientEmail = "client@gmail.com";
        final var givenClientUniqueIdentifier = "client";
        final var givenClientRole = Role.CLIENT;
        final var givenClientPassword = "123456";

        final var givenClient = User.newUser(
                givenClientName,
                givenClientPhoneNumber,
                givenClientEmail,
                givenClientUniqueIdentifier,
                givenClientRole,
                givenClientPassword
        );

        final var givenStart = Instant.now();
        final var givenEnd = Instant.now().plus(1, ChronoUnit.SECONDS);
        final var givenStatus = ScheduleStatus.ACTIVE;
        final var givenDescription = "";
        final var givenScheduleType = ScheduleType.HAIR_AND_BEARD;

        final var actualSchedule = Schedule.newSchedule(
                givenBarber,
                givenClient,
                givenStart,
                givenEnd,
                givenStatus,
                givenDescription,
                givenScheduleType
        );

        final var expectedStart = Instant.now().plus(2, ChronoUnit.SECONDS);
        final var expectedEnd = Instant.now().plus(3, ChronoUnit.SECONDS);
        final var expectedStatus = ScheduleStatus.CANCELLED;
        final var expectedDescription = "";
        final var expectedScheduleType = ScheduleType.HAIR_AND_BEARD;

        actualSchedule.update(
                givenClient,
                expectedStart,
                expectedEnd,
                expectedStatus,
                expectedDescription,
                expectedScheduleType
        );

        Assertions.assertNotNull(actualSchedule);
        Assertions.assertEquals(givenClient, actualSchedule.getClient());
        Assertions.assertEquals(expectedStart, actualSchedule.getStart());
        Assertions.assertEquals(expectedEnd, actualSchedule.getEnd());
        Assertions.assertEquals(expectedStatus, actualSchedule.getStatus());
        Assertions.assertEquals(expectedDescription, actualSchedule.getDescription());
        Assertions.assertEquals(expectedScheduleType, actualSchedule.getScheduleType());
    }
}
