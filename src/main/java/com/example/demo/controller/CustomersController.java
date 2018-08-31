package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomersController {

	private CustomerService customerService;
	
	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public Iterable<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@PutMapping
	public List<Customer> updateCustomers(@RequestBody List<Customer> customers){
		return customerService.updateCustomers(customers);
	}
	
	@PostMapping
	public Iterable<Customer> addCustomers(@RequestBody List<Customer> customers){
		return customerService.addCustomers(customers);
	}
	
	@DeleteMapping
	public void deleteCustomers(@RequestParam("customerId") List<Integer> customerIds) {
		customerService.deleteCustomers(customerIds);
	}
}
