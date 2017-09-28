package com.itsaur.fullstackexample.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class DomainEventPublisher {

    private Collection<DomainEventListener> listeners;

    public DomainEventPublisher() {
        this.listeners = new HashSet<>();
    }

    public void publish(DomainEvent domainEvent) {
        Collections.unmodifiableCollection(this.listeners).forEach(listener -> {
            boolean supported = listener.listensFor()
                    .stream()
                    .anyMatch(clazz -> clazz.isAssignableFrom(domainEvent.getClass()));

            if(supported) {
                listener.eventPublished(domainEvent);
            }
        });
    }

    public void register(DomainEventListener listener) {
        this.listeners.add(listener);
    }

    public void unregister(DomainEventListener listener) {
        this.listeners.remove(listener);
    }
}
