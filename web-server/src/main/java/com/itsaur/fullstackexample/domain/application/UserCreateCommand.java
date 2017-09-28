package com.itsaur.fullstackexample.domain.application;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Whenever we want to change the state of an entity we do it by using a command object.
 * see <a href="https://martinfowler.com/bliki/CQRS.html">CQRS</a> for more information.
 *
 * Command objects are immutable and should have only getters. It isn't necessary for the command object to follow
 * the structure of the entity. It is the ApplicationService responsibility to take the command and execute whatever action
 * is needed.
 */
public class UserCreateCommand {

    private final String name;
    private final String lastname;

    protected UserCreateCommand(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public static UserCreateCommand create(String name, String lastname) {
        return new UserCreateCommand(name, lastname);
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
