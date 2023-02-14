package com.kameleoon.trialtask.quote.dao;

import com.kameleoon.trialtask.quote.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        return entityManager.createQuery("from User where name = :name", User.class)
                .setParameter("name", userName)
                .getSingleResult();
    }
}
