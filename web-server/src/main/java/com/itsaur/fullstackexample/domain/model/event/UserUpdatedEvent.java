package com.itsaur.fullstackexample.domain.model.event;

import com.itsaur.fullstackexample.domain.DomainEvent;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

/**
 * Updated events keep track of what has changed.
 */
public class UserUpdatedEvent implements DomainEvent {

    private final UUID id;
    private final String name;
    private final String lastname;

    private final String oldName;
    private final String oldLastname;

    public UserUpdatedEvent(UUID id, String name, String lastname, String oldName, String oldLastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.oldName = oldName;
        this.oldLastname = oldLastname;
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

    public String oldName() {
        return oldName;
    }

    public String oldLastname() {
        return oldLastname;
    }

    public boolean changed() {
        return nameChanged() || lastnameChanged();
    }

    public boolean nameChanged() {
        return !Objects.equals(name, oldName);
    }

    public boolean lastnameChanged() {
        return !Objects.equals(lastname, oldLastname);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
