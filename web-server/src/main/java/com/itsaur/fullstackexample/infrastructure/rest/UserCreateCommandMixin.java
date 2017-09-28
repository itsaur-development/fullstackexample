package com.itsaur.fullstackexample.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsaur.fullstackexample.domain.application.UserCreateCommand;

public class UserCreateCommandMixin extends UserCreateCommand {

    @JsonCreator
    protected UserCreateCommandMixin(
            @JsonProperty("name") String name,
            @JsonProperty("lastname") String lastname) {
        super(name, lastname);
    }
}
