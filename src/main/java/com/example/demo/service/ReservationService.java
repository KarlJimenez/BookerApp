package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.model.ServiceOb;
import com.example.demo.repository.ReservationRepository;

public class ReservationService {

	private ReservationRepository reservationRepository;
	private CustomerService customerService;
	private ServiceService serviceService;
	
	public ReservationService(ReservationRepository reservationRepository, 
			CustomerService customerService, ServiceService serviceService) {
		this.reservationRepository = reservationRepository;
		this.customerService = customerService;
		this.serviceService = serviceService;
	}
	
	//*************** For Single Reservations ******************
	
	@Transactional
	public Reservation getReservation(int reservationId) {
		return reservationRepository.findById(reservationId).get();
	}
	
	@Transactional
	public Reservation updateReservation(int reservationId, Reservation reservation) {
		Reservation newReservation;
		if(reservationRepository.existsById(reservation.getReservationId())) {
			reservation = assignRelationships(reservation);
			if(reservation != null) {
				reservationRepository.save(reservation);
			}
			newReservation = reservation;
		}
		else {
			newReservation = null;
		}
		return newReservation;
	}
	
	@Transactional
	public void deleteReservation(int reservationId) {
		if(reservationRepository.existsById(reservationId)) {
			/*Reservation reservation = getReservation(reservationId);
			customerService.detachReservation(reservation, reservation.getCustomer());
			serviceService.detachReservation(reservation, reservation.getAvailedServiceList());
			*/reservationRepository.deleteById(reservationId);
		}
	}
	
	//*************** For Multiple Reservations ******************
	
	@Transactional
	public List<Reservation> getAllReservations(){
		return (List<Reservation>) reservationRepository.findAll();
	}
	
	@Transactional
	public List<Reservation> addReservations(List<Reservation> reservations){
		for(Reservation reservation : reservations) {
			reservation = assignRelationships(reservation);
			if(reservation == null) {
				reservations.remove(reservation);
			}
		}
		return (List<Reservation>) reservationRepository.saveAll(reservations);
	}
	
	@Transactional
	public List<Reservation> updateReservations(List<Reservation> reservations){
		for(Reservation reservation : reservations) {
			reservation = updateReservation(reservation.getReservationId(), reservation);
			if(reservation == null) {
				reservations.remove(reservation);
			}
		}
		return reservations;
	}
	
	@Transactional
	public void deleteReservations(List<Integer> reservationIds) {
		for(Integer reservationId : reservationIds) {
			deleteReservation(reservationId);
		}
	}
	
	//*************** Special Methods ******************
	
	//****** Assigning customer and services for a reservation (returns null when customer doesn't exist) ******
	@Transactional
	public Reservation assignRelationships(Reservation reservation) {
		Customer customer = customerService.getCustomer(reservation.getCustomer().getCustomerId());
		if(customer != null) {
			customerService.attachReservation(reservation, customer);
		}
		//****** if there is no such customer, disregard reservation ******
		else {
			return null;
		}
		for(ServiceOb service : reservation.getAvailedServiceList()) {
			//****** if there is no such service, disregard service ******
			if(!serviceService.attachReservation(reservation, service)) {
				reservation.removeService(service);
			}
		}
		return reservation;
	}
}
