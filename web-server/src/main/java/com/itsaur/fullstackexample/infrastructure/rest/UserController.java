package com.itsaur.fullstackexample.infrastructure.rest;

import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.application.UserApplicationService;
import com.itsaur.fullstackexample.domain.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        Preconditions.checkNotNull(userApplicationService, "userApplicationService cannot be null");

        this.userApplicationService = userApplicationService;
    }

    @GetMapping()
    public Collection<User> users() {
        return null;
    }
}