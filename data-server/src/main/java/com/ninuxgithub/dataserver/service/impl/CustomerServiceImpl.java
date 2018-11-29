package com.ninuxgithub.dataserver.service.impl;

import com.ninuxgithub.dataserver.model.Customer;
import com.ninuxgithub.dataserver.repository.CustomerRepository;
import com.ninuxgithub.dataserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer findCustomerByUserName(String userName) {
        return customerRepository.findCustomerByUserName(userName);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(String uid) {
        return customerRepository.findById(uid).orElse(null);
    }
}
