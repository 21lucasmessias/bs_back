package com.view.plantview.sensor.api.application.user.retrieve.list;

import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.user.Role;
import com.view.plantview.sensor.api.domain.user.User;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserSearchQuery;
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
public class ListUsersUseCaseTest {

    @InjectMocks
    private DefaultListUsersUseCase useCase;

    @Mock
    private UserGateway gateway;

    @BeforeEach
    public void cleanUp() {
        Mockito.reset(gateway);
    }

    @Test
    public void givenAValidQuery_whenCallsListUsers_thenShouldReturnUsers() {
        final var expectedUniqueIdentifier = "1";
        final var expectedName = "Lucas";
        final var expectedPhoneNumber = "";
        final var expectedEmail = "21.lucasmessias@gmail.com";
        final var expectedRole = Role.OWNER;
        final var expectedPassword = "123456";

        final var aUser = User.newUser(
                expectedUniqueIdentifier,
                expectedName,
                expectedPhoneNumber,
                expectedEmail,
                expectedRole,
                expectedPassword
        );

        final var expectedUniqueIdentifier2 = "2";
        final var expectedName2 = "Lucas";
        final var expectedPhoneNumber2 = "";
        final var expectedEmail2 = "21.lucasmessias@gmail.com";
        final var expectedRole2 = Role.OWNER;
        final var expectedPassword2 = "123456";

        final var aUser2 = User.newUser(
                expectedUniqueIdentifier2,
                expectedName2,
                expectedPhoneNumber2,
                expectedEmail2,
                expectedRole2,
                expectedPassword2
        );

        final var users = List.of(
                aUser,
                aUser2
        );

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "uniqueIdentifier";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, users.size(), users);

        final var expectedItemsCount = 2;
        final var expectedResult = expectedPagination.map(UserListOuput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(users.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenHasNoResults_thenShouldReturnEmptyUsers() {
        final var users = List.<User>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "uniqueIdentifier";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage, users.size(), users);

        final var expectedItemsCount = 0;
        final var expectedResult = expectedPagination.map(UserListOuput::from);

        when(gateway.findAll(eq(aQuery))).thenReturn(expectedPagination);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedResult, actualResult);
        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(users.size(), actualResult.total());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_thenShouldReturnException() {
        final var expectedErrorMessage = "gateway error";

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "uniqueIdentifier";
        final var expectedDirection = "asc";

        final var aQuery = new UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        when(gateway.findAll(eq(aQuery))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aQuery));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
