package com.view.plantview.sensor.api.application.downtime.retrieve.list;

import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserSearchQuery;
import com.view.plantview.sensor.api.domain.pagination.Pagination;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListUserUseCaseTest {

    @InjectMocks
    private DefaultListDowntimeUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidQuery_whenCallsListDowntime_thenShouldReturnDowntime() {
        final var downtime = List.of(
                User.newDowntime(SensorId.from("1"), null, LocalDate.now(), Instant.now(), Instant.now().plusSeconds(1)),
                User.newDowntime(SensorId.from("2"), null, LocalDate.now(), Instant.now(), Instant.now().plusSeconds(1))
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "date";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, downtime.size(), downtime);

        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(DowntimeListOutput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(downtime.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyDowntime() {
        final var downtime = List.<User>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "idExternal";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, downtime.size(), downtime);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(DowntimeListOutput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(downtime.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "idExternal";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(gateway.findAll(eq(aQuery))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
