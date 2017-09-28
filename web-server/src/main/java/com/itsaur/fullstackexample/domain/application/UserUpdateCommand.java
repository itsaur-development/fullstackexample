package com.itsaur.fullstackexample.domain.application;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

/**
 * @see UserCreateCommand
 */
public class UserUpdateCommand {

    private final UUID id;
    private final String name;
    private final String lastname;

    protected UserUpdateCommand(UUID id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public static UserUpdateCommand create(UUID id, String name, String lastname) {
        return new UserUpdateCommand(id, name, lastname);
    }

    /**
     * In case we want to modify the state of the command we have to create a new
     * command with the data we need since it is immutable.
     */
    public UserUpdateCommand withId(UUID id) {
        return new UserUpdateCommand(id, name, lastname);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String lastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
