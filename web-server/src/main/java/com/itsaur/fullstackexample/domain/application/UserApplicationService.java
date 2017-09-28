package com.itsaur.fullstackexample.domain.application;

import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.DomainEventPublisher;
import com.itsaur.fullstackexample.domain.model.User;
import com.itsaur.fullstackexample.domain.model.UserRepository;
import com.itsaur.fullstackexample.domain.model.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * The ApplicationServices expose the business logic of our application to external systems.
 *
 * This logic can be exposed by various protocols like REST, SOAP, AMQP but in this layer we do not care
 * how the logic is exposed we only care about the logic itself.
 * See {@link com.itsaur.fullstackexample.infrastructure.rest.UserController} to see how the logic is exposed via REST.
 *
 * ApplicationServices are stateless (they aren't allowed to keep state in fields) if we need to keep the state
 * we could store it by using another entity via a repository.
 * This is done to keep our code clean and avoid having locks and other multithreaded issues.
 *
 * @see <a href="https://dzone.com/articles/hexagonal-architecture-is-powerful">Hexagonal Architecture</a>
 */
public class UserApplicationService {

    private static final Logger logger = LogManager.getLogger(UserApplicationService.class);

    private final UserRepository userRepository;
    private final DomainEventPublisher eventPublisher;

    public UserApplicationService(UserRepository userRepository, DomainEventPublisher eventPublisher) {
        Preconditions.checkNotNull(userRepository, "userRepository cannot be null");
        Preconditions.checkNotNull(eventPublisher, "eventPublisher cannot be null");

        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Methods that mutate the state of an entity should accept only a Command object.
     *
     * The Transactional attribute will open a transaction in the database and commit it when the method is finished.
     *
     * @see UserCreateCommand
     */
    @Transactional
    public void execute(UserCreateCommand command) {
        logger.info("Executing command {}", command);

        User user = User.create(command.name(), command.lastname());
        userRepository.save(user);

        user.changes().allEvents().forEach(eventPublisher::publish);
    }

    @Transactional
    public void execute(UserUpdateCommand command) throws UserNotFoundException {
        logger.info("Executing command {}", command);

        User user = userRepository.findById(command.id());
        if (user == null) {
            throw new UserNotFoundException();
        }

        user.update(command.name(), command.lastname());

        //Note that normal flows wherever possible shouldn't be inside an if statement, makes the code
        //more readable and removes deep nesting levels.
        if (user.changes().isEmpty()) {
            return;
        }

        userRepository.save(user);
        user.changes().allEvents().forEach(eventPublisher::publish);
    }
}
