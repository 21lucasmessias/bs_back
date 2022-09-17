package com.view.plantview.sensor.api.application.user.retrieve.list;

import com.view.plantview.sensor.api.application.UseCase;
import com.view.plantview.sensor.api.domain.pagination.Pagination;
import com.view.plantview.sensor.api.domain.user.UserSearchQuery;

public abstract class ListUsersUseCase extends UseCase<UserSearchQuery, Pagination<UserListOuput>> {
}
