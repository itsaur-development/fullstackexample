package com.itsaur.fullstackexample.infrastructure.jpa;

import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.model.User;
import com.itsaur.fullstackexample.domain.model.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

/**
 * Implementation of {@link UserRepository} that reads and writes in RDBMS using the JPA framework.
 */
public class JpaUserRepository implements UserRepository {

    private final EntityManager entityManager;

    public JpaUserRepository(EntityManager entityManager) {
        Preconditions.checkNotNull(entityManager, "entityManager cannot be null");

        this.entityManager = entityManager;
    }

    @Override
    public User findById(UUID id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Collection<User> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> from = query.from(User.class);

        // You can do where queries:
        // e.g. query.where(builder.equal(from.get("name"), "A"));

        query.orderBy(builder.asc(from.get("name")));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
