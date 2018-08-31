package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.ServiceOb;
import com.example.demo.model.TravelPackage;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {

	private TravelPackageRepository travelPackageRepository;
	private ServiceService serviceService;
	private ImageService imageService;
	
	public TravelPackageService(TravelPackageRepository travelPackageRepository, 
			ServiceService serviceService, ImageService imageService) {
		this.travelPackageRepository = travelPackageRepository;
		this.serviceService = serviceService;
		this.imageService = imageService;
	}

	//*************** For Single Travel Packages ******************

	@Transactional
	public TravelPackage getTravelPackage(int travelPackageId){
		return travelPackageRepository.findById(travelPackageId).get();
	}

	@Transactional
	public TravelPackage updateTravelPackage(int travelPackageId, TravelPackage travelPackage) {
		if(travelPackageRepository.existsById(travelPackageId)) {
			travelPackage.setTravelPackageId(travelPackageId);
			travelPackageRepository.save(travelPackage);
			serviceService.updateServices(travelPackage.getAvailableServiceList());
			imageService.updateImages(travelPackage.getImages());
		}
		return travelPackage;
	}

	@Transactional
	public void deleteTravelPackage(int travelPackageId) {
		if(travelPackageRepository.existsById(travelPackageId)) {
			TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();
			serviceService.deleteServices(travelPackage.getAvailableServiceList());
			imageService.deleteImages(travelPackage.getImages());
			travelPackageRepository.deleteById(travelPackageId);
		}
	}
	
	//*************** For Multiple Travel Packages ******************

	@Transactional
	public List<TravelPackage> getAllTravelPackages(){
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}

	@Transactional
	public List<TravelPackage> addTravelPackages(List<TravelPackage> travelPackages){
		travelPackageRepository.saveAll(travelPackages);
		for(TravelPackage travelPackage : travelPackages) {
			for(ServiceOb service : travelPackage.getAvailableServiceList()) {
				service.setTravelPackage(travelPackage);
			}
			serviceService.addServices(travelPackage.getAvailableServiceList());
			for(Image image : travelPackage.getImages()) {
				image.setTravelPackage(travelPackage);
			}
			imageService.addImages(travelPackage.getImages());
		}
		return travelPackages;
	}

	@Transactional
	public List<TravelPackage> updateTravelPackages(List<TravelPackage> travelPackages){
		for(TravelPackage travelPackage : travelPackages) {
			updateTravelPackage(travelPackage.getTravelPackageId(), travelPackage);
		}
		return travelPackages;
	}

	@Transactional
	public void deleteTravelPackages(List<Integer> travelPackageIds) {
		for(TravelPackage travelPackage : travelPackageRepository.findAllById(travelPackageIds)) {
			deleteTravelPackage(travelPackage.getTravelPackageId());
		}
	}
}
