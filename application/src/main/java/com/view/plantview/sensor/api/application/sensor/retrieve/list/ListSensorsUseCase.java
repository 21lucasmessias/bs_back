package com.view.plantview.sensor.api.application.sensor.retrieve.list;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.sensor.SensorSearchQuery;

public abstract class ListSensorsUseCase extends UseCase<SensorSearchQuery, Pagination<SensorListOuput>> {
}
