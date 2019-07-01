package com.smartshop.dao.user;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return null;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }
}
