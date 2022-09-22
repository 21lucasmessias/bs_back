package io.sanctuary.bs.api.infrastructure.api.controllers;

import io.sanctuary.bs.api.domain.pagination.Pagination;
import io.sanctuary.bs.api.infrastructure.api.UserAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserAPI {

    @Override
    public ResponseEntity<?> createUser() {
        return null;
    }

    @Override
    public Pagination<?> listUsers(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
