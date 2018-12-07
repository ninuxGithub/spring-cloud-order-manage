package com.ninuxgithub.dataserver.service;

import com.ninuxgithub.dataserver.model.Customer;

public interface CustomerService {

    Customer findCustomerByUserName(String userName);

    Customer saveCustomer(Customer customer);

    Customer findCustomerById(String uid);
}
