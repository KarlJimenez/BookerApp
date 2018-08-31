package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.model.ServiceOb;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ServiceRepository;

public class ReservationService {

	private ReservationRepository reservationRepository;
	private ServiceRepository serviceRepository;
	private CustomerRepository customerRepository;
	
	public ReservationService(ReservationRepository reservationRepository, 
			ServiceRepository serviceRepository, CustomerRepository customerRepository) {
		this.reservationRepository = reservationRepository;
		this.customerRepository = customerRepository;
	}
	
	//*************** For Single Reservations ******************
	
	//*************** For Multiple Reservations ******************
	
	public List<Reservation> getAllReservations(){
		return (List<Reservation>) reservationRepository.findAll();
	}
	
	public List<Reservation> addReservations(@RequestBody List<Reservation> reservations){
		List<Reservation> newReservations = (List<Reservation>) reservationRepository.saveAll(reservations);
		for(Reservation reservation : reservations) {
			for(ServiceOb service : reservation.getAvailedServiceList()) {
				int serviceId = service.getServiceId();
				if(serviceRepository.existsById(serviceId)) {
					
				}
			}
			int customerId = reservation.getCustomer().getCustomerId();
			if(customerRepository.existsById(customerId)) {
				Customer customer = customerRepository.findById(customerId).get();
				customer.addReservations(reservation);
				customerRepository.save(customer);
			}
		}
		return newReservations;
	}
}
