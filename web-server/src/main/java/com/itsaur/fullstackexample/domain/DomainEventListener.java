package com.itsaur.fullstackexample.domain;

import java.util.Collection;

public interface DomainEventListener {

    void eventPublished(DomainEvent event);

    Collection<Class<? extends DomainEvent>> listensFor();
}
