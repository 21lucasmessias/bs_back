package com.view.plantview.sensor.api.domain.schedule;

public record ScheduleSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
