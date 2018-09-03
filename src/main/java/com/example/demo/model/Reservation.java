package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;
	@ManyToMany
	private List<ServiceOb> availedServiceList;
	
	private LocalDate departureDate;
	@ManyToOne
	private Customer customer;
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public List<ServiceOb> getAvailedServiceList() {
		return availedServiceList;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void addService(ServiceOb service) {
		availedServiceList.add(service);
	}
	public void removeService(ServiceOb service) {
		availedServiceList.remove(service);
	}
}
