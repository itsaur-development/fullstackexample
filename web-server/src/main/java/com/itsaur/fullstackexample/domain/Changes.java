package com.itsaur.fullstackexample.domain;

import java.util.*;

/**
 * Keeps tracks of all {@link DomainEvent} in an entity
 */
public class Changes {

    private Map<Class<? extends DomainEvent>, Collection<DomainEvent>> events;

    private Changes() {
        events = new HashMap<>();
    }

    public static Changes create() {
        return new Changes();
    }

    public <T extends DomainEvent> Changes add(T event) {
        if (!events.containsKey(event.getClass())) {
            events.put(event.getClass(), new ArrayList<>());
        }

        events.get(event.getClass()).add(event);
        return this;
    }

    public Collection<DomainEvent> allEvents() {
        Collection<DomainEvent> allEvents = new ArrayList<>();

        for (Collection<DomainEvent> domainEvents : events.values()) {
            allEvents.addAll(domainEvents);
        }

        return allEvents;
    }

    public void clear() {
        events = new HashMap<>();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }
}
