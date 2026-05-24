package com.app.daoimpl;

import com.app.dao.CustomerDao;
import com.app.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getCustomerByUsername(String username) {
        return entityManager.createQuery("select c from Customer c where c.user.username = ?1", Customer.class)
                .setParameter(1,username)
                .getSingleResult();
    }
}
