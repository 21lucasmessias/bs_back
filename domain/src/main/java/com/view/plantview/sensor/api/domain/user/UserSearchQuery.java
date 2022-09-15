package com.view.plantview.sensor.api.domain.user;

public record UserSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
