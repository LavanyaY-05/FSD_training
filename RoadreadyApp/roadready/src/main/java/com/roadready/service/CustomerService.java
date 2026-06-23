package com.roadready.service;

import com.roadready.Mapper.CustomerMapper;
import com.roadready.dto.*;
import com.roadready.enums.Role;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Customer;
import com.roadready.model.User;
import com.roadready.repository.CustomerRepository;
import com.roadready.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final UserRepository userRepository;

    public List<CustomerDetailsRespDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapCustomerEntityToDto)
                .toList();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Customer Id"));
    }

    public void addCustomer(CustomerDetailsReqDto dto) {


        //step 1: get the username and password
        String password = dto.password();

        // step2 : encode the password
        String encodedPassword = passwordEncoder.encode(password);

        // step 3: create user object, set the role - CUSTOMER
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setRole(Role.CUSTOMER);

        // step 4: save the user
        userService.save(user);

        // step 5: create the customer and add the details
        Customer customer = new Customer();
        customer.setFirstname(dto.firstName());
        customer.setLastname(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());
        customer.setUser(user);

        // step 6: save the customer
        customerRepository.save(customer);
    }

    public CustomerDetailsRespDto getCustomer(int id) {
        Customer customer = getCustomerById(id);
        return customerMapper.mapCustomerEntityToDto(customer);
    }

    public void updateCustomer(String username, @Valid UpdateReqDto dto) {

        Customer customer = findByUsername(username);
        customer.setFirstname(dto.firstName());
        customer.setLastname(dto.lastName());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());

        customerRepository.save(customer);

    }

    public CustomerDetailsRespDto getByUsername(String username) {

        return customerMapper.mapCustomerEntityToDto(findByUsername(username));

    }

    public Customer findByUsername(String username) {

        return customerRepository.findByUserUsername(username);

    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
