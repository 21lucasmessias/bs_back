package com.view.plantview.sensor.api.application.downtime.retrieve.list;

import com.view.plantview.sensor.api.domain.user.UserGateway;
import com.view.plantview.sensor.api.domain.user.UserSearchQuery;
import com.view.plantview.sensor.api.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListDowntimeUseCase extends ListDowntimeUseCase {
    private final UserGateway gateway;

    public DefaultListDowntimeUseCase(final UserGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Pagination<DowntimeListOutput> execute(UserSearchQuery aQuery) {
        return this.gateway.findAll(aQuery)
                .map(DowntimeListOutput::from);
    }
}
