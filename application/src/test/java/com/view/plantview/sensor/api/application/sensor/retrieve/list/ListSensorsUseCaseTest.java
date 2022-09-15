package com.view.plantview.sensor.api.application.sensor.retrieve.list;

import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.sensor.Sensor;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorSearchQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListSensorsUseCaseTest {

    @InjectMocks
    private DefaultListSensorsUseCase useCase;

    @Mock
    private SensorGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidQuery_whenCallsListSensors_thenShouldReturnSensors() {
        final var sensors = List.of(
                Sensor.newSensor("1", null),
                Sensor.newSensor("2", null)
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "idExternal";
        final var expectedDirection = "asc";

        final var aQuery = new SensorSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, sensors.size(), sensors);

        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(SensorListOuput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(sensors.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptySensors() {
        final var sensors = List.<Sensor>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "idExternal";
        final var expectedDirection = "asc";

        final var aQuery = new SensorSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, sensors.size(), sensors);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(SensorListOuput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(sensors.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "idExternal";
        final var expectedDirection = "asc";

        final var aQuery = new SensorSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(gateway.findAll(eq(aQuery))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
