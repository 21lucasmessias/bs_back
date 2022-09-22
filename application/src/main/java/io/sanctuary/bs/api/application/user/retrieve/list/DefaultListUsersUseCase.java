package io.sanctuary.bs.api.application.user.retrieve.list;

import io.sanctuary.bs.api.domain.pagination.Pagination;
import io.sanctuary.bs.api.domain.user.UserGateway;
import io.sanctuary.bs.api.domain.user.UserSearchQuery;

import java.util.Objects;

public class DefaultListUsersUseCase extends ListUsersUseCase {
    private final UserGateway gateway;

    public DefaultListUsersUseCase(final UserGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public Pagination<UserListOuput> execute(final UserSearchQuery aQuery) {
        return this.gateway.findAll(aQuery)
                .map(UserListOuput::from);
    }
}
