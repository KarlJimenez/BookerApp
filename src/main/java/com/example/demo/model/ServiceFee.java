package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ServiceFee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_fee_generator")
//	@SequenceGenerator(name="service_fee_generator", sequenceName = "service_fee_seq")
	private int serviceFeeId;
	private float amount;
	@OneToOne
	private ServiceOb service;
	private LocalDate startDate;
	
	public int getServiceFeeId() {
		return serviceFeeId;
	}
	public void setServiceFeeId(int serviceFeeId) {
		this.serviceFeeId = serviceFeeId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public ServiceOb getService() {
		return service;
	}
	public void setService(ServiceOb service) {
		this.service = service;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}
