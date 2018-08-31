package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	//*************** For Single Customers ******************
	
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerRepository.findById(customerId).get();
	}

	@Transactional
	public Customer updateCustomer(int customerId, Customer customer) {
		if(customerRepository.existsById(customerId)) {
			customer.setCustomerId(customerId);
			customerRepository.save(customer);
		}
		return customer;
	}

	@Transactional
	public void deleteCustomer(int customerId) {
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
		}
	}
	
	//*************** For Multiple Customers ******************

	@Transactional
	public List<Customer> getAllCustomers(){
		return (List<Customer>) customerRepository.findAll();
	}

	@Transactional
	public List<Customer> addCustomers(List<Customer> customers){
		return (List<Customer>) customerRepository.saveAll(customers);
	}

	@Transactional
	public List<Customer> updateCustomers(List<Customer> customers){
		for(Customer customer : customers) {
			updateCustomer(customer.getCustomerId(), customer);
		}
		return customers;
	}

	@Transactional
	public void deleteCustomers(List<Integer> customerIds) {
		for(Customer customer : customerRepository.findAllById(customerIds)) {
			deleteCustomer(customer.getCustomerId());
		}
	}
	
	//*************** Special Methods ******************

	@Transactional
	public void attachReservation(Reservation reservation, Customer customer) {
		customer.addReservation(reservation);
		customerRepository.save(customer);
	}
	
	@Transactional
	public void detachReservation(Reservation reservation, Customer customer) {
		customer.removeReservation(reservation);
		customerRepository.save(customer);
	}
}
