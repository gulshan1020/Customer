package com.customer.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.customer.app.bean.Customer;
import com.customer.app.customerRepository.CustomerRepository;
 

@RestController
@RequestMapping(value={"/customer"})
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	 // create Customer
	 @PostMapping("/create")
	 public ResponseEntity<Object> create(@RequestBody Customer customer) {
		 Customer savedCustomer = customerRepository.save(customer);

	 	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	 			.buildAndExpand(savedCustomer.getId()).toUri();

	 	return ResponseEntity.created(location).build();

	 }
	 
	 //find all customer
	 @GetMapping("/findAll")
	 public List<Customer> retrieveAllCustomer() {
	 	return customerRepository.findAll();
	 }
	 
	 // find customer using id
	 @GetMapping("/find/{id}")
	 public Customer getCustomerById(@PathVariable long id) throws Exception {
	 	Optional<Customer> customer = customerRepository.findById(id);

	 	if (!customer.isPresent())
	 		throw new Exception("id-" + "does not exixt");

	 	return customer.get();
	 }
	 
	 // delete customer on basis of id
	 @DeleteMapping("/delete/{id}")
	 public void deleteStudent(@PathVariable long id) {
		 customerRepository.deleteById(id);
	 }
	 
	 // update customer on the basis of id
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Object> update(@RequestBody Customer customer, @PathVariable long id) {

	 	Optional<Customer> studentOptional = customerRepository.findById(id);

	 	if (!studentOptional.isPresent())
	 		return ResponseEntity.notFound().build();

	 	customer.setId(id);
	 	
	 	customerRepository.save(customer);

	 	return ResponseEntity.noContent().build();
	 }
}
