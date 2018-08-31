package com.example.demo.service;

import com.example.demo.repository.ServiceFeeRepository;

public class ServiceFeeService {

	private ServiceFeeRepository serviceFeeRepository;
	
	public ServiceFeeService(ServiceFeeRepository serviceFeeRepository) {
		this.serviceFeeRepository = serviceFeeRepository;
	}
}
