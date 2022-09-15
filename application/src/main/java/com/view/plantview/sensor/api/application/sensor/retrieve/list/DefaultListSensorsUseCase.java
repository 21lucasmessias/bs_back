package com.view.plantview.sensor.api.application.sensor.retrieve.list;

import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.sensor.SensorGateway;
import com.view.plantview.sensor.api.domain.sensor.SensorSearchQuery;

import java.util.Objects;

public class DefaultListSensorsUseCase extends ListSensorsUseCase {
    private final SensorGateway gateway;

    public DefaultListSensorsUseCase(final SensorGateway aGateway) {
        this.gateway = Objects.requireNonNull(aGateway);
    }

    @Override
    public Pagination<SensorListOuput> execute(final SensorSearchQuery aQuery) {
        return this.gateway.findAll(aQuery)
                .map(SensorListOuput::from);
    }
}
