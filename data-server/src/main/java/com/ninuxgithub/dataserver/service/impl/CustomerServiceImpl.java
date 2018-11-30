package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ninuxgithub.dataserver.model.Customer;
import com.ninuxgithub.dataserver.repository.CustomerRepository;
import com.ninuxgithub.dataserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer findCustomerByUserName(String userName) {
        return customerRepository.findCustomerByUserName(userName);
    }

    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(String uid) {
        return customerRepository.findById(uid).orElse(null);
        //return customerRepository.findOne(uid);
    }
}
