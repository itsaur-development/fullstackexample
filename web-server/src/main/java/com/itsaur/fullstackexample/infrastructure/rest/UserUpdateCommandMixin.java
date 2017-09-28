package com.itsaur.fullstackexample.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsaur.fullstackexample.domain.application.UserUpdateCommand;

import java.util.UUID;

public class UserUpdateCommandMixin extends UserUpdateCommand {

    @JsonCreator
    protected UserUpdateCommandMixin(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("lastname") String lastname) {
        super(id, name, lastname);
    }
}
