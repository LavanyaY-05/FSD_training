package com.service;

import com.model.Customer;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerService {
    private Session session;

    public CustomerService(Session session) {
        this.session = session;
    }

    public Customer getCustomer(String username) {
        Transaction tx = session.beginTransaction();
        Customer customer = session.createQuery("select c from Customer c where c.user.username=:username", Customer.class)
                        .setParameter("username",username)
                                .getSingleResult();
        tx.commit();
        return customer;
    }

    public void UpdateProfile(Customer customer) {
        Transaction tx = session.beginTransaction();

        tx.commit();

    }
}
