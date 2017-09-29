package com.itsaur.fullstackexample.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserCreatedEventMixin {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastname")
    private String lastname;
}
