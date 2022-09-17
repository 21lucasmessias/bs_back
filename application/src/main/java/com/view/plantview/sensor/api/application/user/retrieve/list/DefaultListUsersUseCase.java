package com.view.plantview.sensor.api.application.user.retrieve.list;

import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserSearchQuery;

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
