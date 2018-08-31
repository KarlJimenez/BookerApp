package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Reservation;
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
		if(reservationRepository.existsById(reservationId)) {
			reservation.setReservationId(reservationId);
			reservationRepository.save(reservation);
		}
		return reservation;
	}
	
	@Transactional
	public void deleteReservation(int reservationId) {
		if(reservationRepository.existsById(reservationId)) {
			reservationRepository.deleteById(reservationId);
		}
	}
	
	//*************** For Multiple Reservations ******************
	
	@Transactional
	public List<Reservation> getAllReservations(){
		return (List<Reservation>) reservationRepository.findAll();
	}
	
	@Transactional
	public List<Reservation> addReservations(List<Reservation> reservations){
		reservationRepository.saveAll(reservations);
		for(Reservation reservation : reservations) {
			
		}
		return reservations;
	}
}
