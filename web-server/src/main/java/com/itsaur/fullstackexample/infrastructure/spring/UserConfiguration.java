package com.itsaur.fullstackexample.infrastructure.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsaur.fullstackexample.domain.DomainEvent;
import com.itsaur.fullstackexample.domain.DomainEventListener;
import com.itsaur.fullstackexample.domain.DomainEventPublisher;
import com.itsaur.fullstackexample.domain.application.UserApplicationService;
import com.itsaur.fullstackexample.domain.application.UserCreateCommand;
import com.itsaur.fullstackexample.domain.application.UserUpdateCommand;
import com.itsaur.fullstackexample.domain.model.User;
import com.itsaur.fullstackexample.domain.model.UserRepository;
import com.itsaur.fullstackexample.domain.model.event.UserCreatedEvent;
import com.itsaur.fullstackexample.domain.model.event.UserUpdatedEvent;
import com.itsaur.fullstackexample.infrastructure.jpa.JpaUserRepository;
import com.itsaur.fullstackexample.infrastructure.rest.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class UserConfiguration {

    /**
     * Everything that is passed in the method will be injected by spring. Here the userRepository is retrieved by
     * the {@link #userRepository(EntityManager)} method from this configuration.
     */
    @Bean
    UserApplicationService userApplicationService(UserRepository userRepository, DomainEventPublisher eventPublisher) {
        return new UserApplicationService(userRepository, eventPublisher);
    }

    /**
     * If we wanted to change the underlying implementation we could simply return a different one like an InMemoryUserRepository
     * that stores everything in memory and flushes them periodically to a file (or use Hazelcast).
     *
     **/
    @Bean
    UserRepository userRepository(EntityManager entityManager) {
        return new JpaUserRepository(entityManager);
    }

    @Bean
    UserController userController(UserApplicationService userApplicationService) {
        return new UserController(userApplicationService);
    }

    /**
     * Here we configure the Jackson mapper that is used by SpringMVC in order
     * to specify how to serialize/deserialize our objects.
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .mixIn(User.class, UserMixin.class)
                .mixIn(UserCreateCommand.class, UserCreateCommandMixin.class)
                .mixIn(UserUpdateCommand.class, UserUpdateCommandMixin.class)
                .mixIn(DomainEvent.class, DomainEventMixin.class)
                .mixIn(UserCreatedEvent.class, UserCreatedEventMixin.class)
                .mixIn(UserUpdatedEvent.class, UserUpdatedEventMixin.class);

    }

    /**
     * Since this is a collection, spring will set the value of the parameter to all registered @Bean that implement DomainEventListener class.
     */
    @Bean
    public DomainEventPublisher eventPublisher(DomainEventListener[] listeners) {
        DomainEventPublisher eventPublisher = new DomainEventPublisher();

        Arrays.stream(listeners).forEach(eventPublisher::register);

        return eventPublisher;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Bean
    public DomainEventListener logListener() {
        Logger logger = LogManager.getLogger(UserConfiguration.class);

        return new DomainEventListener() {
            @Override
            public void eventPublished(DomainEvent event) {
                logger.info("Received event {}", event);
            }

            @Override
            public Collection<Class<? extends DomainEvent>> listensFor() {
                //Listen for ALL events
                return Collections.singleton(DomainEvent.class);
            }
        };
    }

    @Bean
    public DomainEventListener websocketDomainEventListener(SimpMessagingTemplate template, ObjectMapper mapper) {
        return new WebSocketDomainEventListener(template, mapper);
    }
}
