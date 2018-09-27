package com.customer.app.customerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.app.bean.Customer;

public interface CustomerRepository extends  JpaRepository<Customer, Long>{

}
