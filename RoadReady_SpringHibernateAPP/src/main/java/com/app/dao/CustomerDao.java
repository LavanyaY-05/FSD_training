package com.app.dao;

import com.app.model.Customer;

public interface CustomerDao {
    Customer getCustomerByUsername(String username);
}
