package com.roadready.Mapper;

import com.roadready.dto.CustomerDetailsRespDto;
import com.roadready.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDetailsRespDto mapCustomerEntityToDto(Customer customer) {
        return new CustomerDetailsRespDto(
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getUser().getUsername()
        );
    }
}
