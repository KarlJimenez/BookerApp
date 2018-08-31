package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
}
