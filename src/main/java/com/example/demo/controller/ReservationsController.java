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

import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

	private ReservationService reservationService;
	
	public ReservationsController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping
	public List<Reservation> getAllReservations(){
		return reservationService.getAllReservations();
	}
	
	@PutMapping
	public List<Reservation> updateReservations(@RequestBody List<Reservation> reservations){
		return reservationService.updateReservations(reservations);
	}
	
	@PostMapping
	public List<Reservation> addReservations(@RequestBody List<Reservation> reservations){
		return reservationService.addReservations(reservations);
	}
	
	@DeleteMapping
	public void deleteReservations(@RequestParam List<Integer> reservationIds) {
		reservationService.deleteReservations(reservationIds);
	}
}
