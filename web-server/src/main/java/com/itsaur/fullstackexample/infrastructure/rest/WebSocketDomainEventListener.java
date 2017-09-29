package com.itsaur.fullstackexample.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.DomainEvent;
import com.itsaur.fullstackexample.domain.DomainEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * This listener listens for all {@link DomainEvent} and forwards them to websocket in /topic/events topic.
 */
public class WebSocketDomainEventListener implements DomainEventListener {

    private static final Logger logger = LogManager.getLogger(WebSocketDomainEventListener.class);

    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WebSocketDomainEventListener(SimpMessagingTemplate template, ObjectMapper mapper) {
        Preconditions.checkNotNull(template, "template cannot be null");
        Preconditions.checkNotNull(mapper, "mapper cannot be null");

        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public void eventPublished(DomainEvent event) {
        try {
            template.convertAndSend("/topic/events", mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            logger.warn("Could not send message, ignored...", e);
        }
    }

    @Override
    public Collection<Class<? extends DomainEvent>> listensFor() {
        return Collections.singleton(DomainEvent.class);
    }
}
