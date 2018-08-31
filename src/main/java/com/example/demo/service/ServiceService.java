package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.ServiceOb;
import com.example.demo.repository.ServiceRepository;

public class ServiceService {

	private ServiceRepository serviceRepository;
	private ImageService imageService;
	
	public ServiceService(ServiceRepository serviceRepository, ImageService imageService) {
		this.serviceRepository = serviceRepository;
		this.imageService = imageService;
	}
	
	//*************** For Single Services ******************
	
	@Transactional
	public ServiceOb getService(int serviceId) {
		return serviceRepository.findById(serviceId).get();
	}
	
	@Transactional
	public ServiceOb updateService(int serviceId, ServiceOb service) {
		if(serviceRepository.existsById(serviceId)) {
			service.setServiceId(serviceId);
			serviceRepository.save(service);
			imageService.updateImages(service.getImages());
		}
		return service;
	}
	
	@Transactional
	public void deleteService(int serviceId) {
		if(serviceRepository.existsById(serviceId)) {
			ServiceOb service = serviceRepository.findById(serviceId).get();
			imageService.deleteImages(service.getImages());
			serviceRepository.deleteById(serviceId);
		}
	}
	
	//*************** For Multiple Services ******************
	
	@Transactional
	public List<ServiceOb> getAllServices(){
		return (List<ServiceOb>) serviceRepository.findAll();
	}
	
	@Transactional
	public List<ServiceOb> addServices(List<ServiceOb> services){
		serviceRepository.saveAll(services);
		for(ServiceOb service : services) {
			for(Image image : service.getImages()) {
				image.setService(service);
			}
			imageService.addImages(service.getImages());
		}
		return services;
	}
	
	@Transactional
	public List<ServiceOb> updateServices(List<ServiceOb> services){
		for(ServiceOb service : services) {
			updateService(service.getServiceId(), service);
		}
		return services;
	}
	
	@Transactional
	public void deleteServices(List<ServiceOb> services) {
		for(ServiceOb service : services) {
			deleteService(service.getServiceId());
		}
	}
}
