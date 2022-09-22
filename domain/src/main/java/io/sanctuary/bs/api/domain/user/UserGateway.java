package io.sanctuary.bs.api.domain.user;

import io.sanctuary.bs.api.domain.pagination.Pagination;

import java.util.Optional;

public interface UserGateway {
    User create(User aUser);

    void deleteById(UserId anId);

    Optional<User> findById(UserId anId);

    User update(User aUser);

    Pagination<User> findAll(UserSearchQuery aQuery);
}
