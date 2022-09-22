package io.sanctuary.bs.api.application.user.retrieve.list;

import io.sanctuary.bs.api.application.UseCase;
import io.sanctuary.bs.api.domain.pagination.Pagination;
import io.sanctuary.bs.api.domain.user.UserSearchQuery;

public abstract class ListUsersUseCase extends UseCase<UserSearchQuery, Pagination<UserListOuput>> {
}
