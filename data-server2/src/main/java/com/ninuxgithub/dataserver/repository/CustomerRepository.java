package com.ninuxgithub.dataserver.repository;

import com.ninuxgithub.dataserver.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {


    Customer findCustomerByUserName(String userName);

}
