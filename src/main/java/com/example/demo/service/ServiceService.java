package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ServiceOb;
import com.example.demo.repository.ServiceRepository;

public class ServiceService {

	private ServiceRepository serviceRepository;
	
	public ServiceService(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
	
	//*************** For Single Services ******************
	
	//*************** For Multiple Services ******************
	
	public List<ServiceOb> getAllServices(){
		return (List<ServiceOb>) serviceRepository.findAll();
	}
	
	public List<ServiceOb> addServices(List<ServiceOb> services){
		return (List<ServiceOb>) serviceRepository.saveAll(services);
	}
	
	public List<ServiceOb> updateServices(List<ServiceOb> services){
		return services;
	}
}
