package com.smartshop.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleDaoImp implements UserRoleDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserRole> findAll() {
        TypedQuery<UserRole> query =  entityManager.createQuery("select ur from UserRole ur", UserRole.class);
        List<UserRole> results = query.getResultList();
        return results;
    }

    @Override
    public List<UserRole> findByUserName(String userName) {
        TypedQuery<UserRole> query = entityManager.createQuery("select ur from UserRole ur where ur.userName = :userName"
                , UserRole.class);
        query.setParameter("userName", userName);
        List<UserRole> results = query.getResultList();
        return results;
    }

    @Override
    public void add(UserRole userRole) {
        entityManager.merge(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        entityManager.remove(userRole);
    }

    @Override
    @Transactional
    public void deleteByUser(User user) {
        entityManager.createNativeQuery("delete from user_role where user_name='"+user.getUserName()+"';").executeUpdate();
    }
}
