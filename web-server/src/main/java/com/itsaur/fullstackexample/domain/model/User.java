package com.itsaur.fullstackexample.domain.model;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;

    private User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User create(UUID id, String name) {
        return new User(id, name);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }
}
