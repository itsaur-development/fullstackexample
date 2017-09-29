package com.itsaur.fullstackexample.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, visible = true)
public interface DomainEventMixin {
}
