package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ServiceOb;
import com.example.demo.service.ServiceService;

@RestController
@RequestMapping("/travel-packages/{travelPackageId}/services")
public class TravelPackageServicesController {

	private ServiceService serviceService;
	
	public TravelPackageServicesController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}
//	
//	@GetMapping
//	public List<ServiceOb>
}
