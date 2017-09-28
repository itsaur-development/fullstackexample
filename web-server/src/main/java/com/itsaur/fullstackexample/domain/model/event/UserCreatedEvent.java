package com.itsaur.fullstackexample.domain.model.event;

import com.itsaur.fullstackexample.domain.DomainEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class UserCreatedEvent implements DomainEvent {

    private final UUID id;
    private final String name;
    private final String lastname;

    public UserCreatedEvent(UUID id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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
