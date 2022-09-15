package com.view.plantview.sensor.api.domain.schedule;

import com.view.plantview.sensor.api.domain.pagination.Pagination;

import java.util.Optional;

public interface ScheduleGateway {
    Schedule create(Schedule aUser);

    void deleteById(ScheduleId anId);

    Optional<Schedule> findById(ScheduleId anId);

    Schedule update(Schedule aUser);

    Pagination<Schedule> findAll(ScheduleSearchQuery aQuery);
}
