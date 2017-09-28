package com.itsaur.fullstackexample.domain.model;

import java.util.Collection;
import java.util.UUID;

/**
 * Repositories are responsible for retrieving and storing data. No business logic should be put in the repositories.
 *
 * The only thing that this class should handle is storing and retrieving data, so that if we decide to change how the data are stored/retrieved
 * we can do this easily (e.g. move to a noSQL solution or drop JPA and use a different framework).
 *
 */
public interface UserRepository {

    User findById(UUID id);

    Collection<User> findAll();

    void save(User user);
}
