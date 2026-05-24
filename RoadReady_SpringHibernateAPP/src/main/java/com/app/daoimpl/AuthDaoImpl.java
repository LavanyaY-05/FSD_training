package com.app.daoimpl;

import com.app.dao.AuthDao;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class AuthDaoImpl implements AuthDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public User login(String username, String password) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = ?1 and u.password = ?2",User.class);
        query.setParameter(1,username);
        query.setParameter(2,password);
        return query.getSingleResult();
    }
}
