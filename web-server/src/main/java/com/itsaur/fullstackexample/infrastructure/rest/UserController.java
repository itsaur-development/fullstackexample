package com.itsaur.fullstackexample.infrastructure.rest;

import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.application.UserApplicationService;
import com.itsaur.fullstackexample.domain.application.UserCreateCommand;
import com.itsaur.fullstackexample.domain.application.UserUpdateCommand;
import com.itsaur.fullstackexample.domain.model.User;
import com.itsaur.fullstackexample.domain.model.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

/**
 * The controller MUST ONLY BE CONCERNED with the PROTOCOL handling. Since this is
 * a REST controller its only job must be to listen to REST endpoints convert the data and call
 * the appropriate method of the {@link UserApplicationService}.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        Preconditions.checkNotNull(userApplicationService, "userApplicationService cannot be null");

        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public Collection<User> users() {
        return userApplicationService.findAll();
    }

    @PostMapping
    public void create(@RequestBody UserCreateCommand userCreateCommand) {
        userApplicationService.execute(userCreateCommand);
    }

    @PutMapping("{id}")
    public void create(@PathVariable("id") String id, @RequestBody UserUpdateCommand userUpdateCommand) throws UserNotFoundException {
        userApplicationService.execute(userUpdateCommand.withId(UUID.fromString(id)));
    }
}