package com.itsaur.fullstackexample.domain.model;

import com.itsaur.fullstackexample.domain.Changes;
import com.itsaur.fullstackexample.domain.model.event.UserCreatedEvent;
import com.itsaur.fullstackexample.domain.model.event.UserUpdatedEvent;

import java.util.UUID;

/**
 * This is an Entity so it is mutable and has an id.
 *
 * This class handles all the business logic for a single User Entity. For example it could
 * have a method change password that would check if the password is valid etc.
 *
 * Cross-entity business logic goes to ApplicationServices.
 *
 * It doesn't expose setters for each property only business methods that can change its state.
 * For a simple update that would be the {@link #update(String, String)} method, we could have
 * a lock()/unlock() method that locks the user etc.
 */
public class User {

    private UUID id;
    private String name;
    private String lastname;
    private Changes changes;

    private User() {
        changes = Changes.create();
    }

    private User(UUID id, String name, String lastname) {
        this();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public static User create(String name, String lastname) {
        User user = new User(UUID.randomUUID(), name, lastname);
        user.changes.add(new UserCreatedEvent(user.id, user.name, user.lastname));

        return user;
    }

    public void update(String name, String lastname) {
        final UserUpdatedEvent event = new UserUpdatedEvent(id, name, lastname, this.name, this.lastname);

        if (!event.changed()) {
            return;
        }

        this.name = name;
        this.lastname = lastname;

        this.changes.add(event);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Changes changes() {
        return changes;
    }
}
