package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Image;
import com.example.demo.model.ServiceOb;
import com.example.demo.model.TravelPackage;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {

	private TravelPackageRepository travelPackageRepository;
	private ServiceRepository serviceRepository;
	private ImageRepository imageRepository;
	
	public TravelPackageService(TravelPackageRepository travelPackageRepository, 
			ServiceRepository serviceRepository, ImageRepository imageRepository) {
		this.travelPackageRepository = travelPackageRepository;
		this.serviceRepository = serviceRepository;
		this.imageRepository = imageRepository;
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
			for(ServiceOb service : travelPackage.getAvailableServiceList()) {
				service.setTravelPackage(travelPackage);
				serviceRepository.save(service);
				for(Image image : service.getImages()) {
					image.setService(service);
					imageRepository.save(image);
				}
			}
			for(Image image : travelPackage.getImages()) {
				image.setTravelPackage(travelPackage);
				imageRepository.save(image);
			}
		}
		return travelPackage;
	}

	@Transactional
	public void deleteTravelPackage(int travelPackageId) {
		if(travelPackageRepository.existsById(travelPackageId)) {
			TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();
			for(ServiceOb service : travelPackage.getAvailableServiceList()) {
				imageRepository.deleteAll(service.getImages());
			}
			serviceRepository.deleteAll(travelPackage.getAvailableServiceList());
			imageRepository.deleteAll(travelPackage.getImages());
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
				serviceRepository.save(service);
				for(Image image : service.getImages()) {
					image.setService(service);
					imageRepository.save(image);
				}
			}
			for(Image image : travelPackage.getImages()) {
				image.setTravelPackage(travelPackage);
				imageRepository.save(image);
			}
		}
		return travelPackages;
	}

	@Transactional
	public List<TravelPackage> updateTravelPackages(List<TravelPackage> travelPackages){
		for(TravelPackage travelPackage : travelPackages) {
			if(travelPackageRepository.existsById(travelPackage.getTravelPackageId())) {
				for(ServiceOb service : travelPackage.getAvailableServiceList()) {
					service.setTravelPackage(travelPackage);
					serviceRepository.save(service);
					for(Image image : service.getImages()) {
						image.setService(service);
						imageRepository.save(image);
					}
				}
				for(Image image : travelPackage.getImages()) {
					image.setTravelPackage(travelPackage);
					imageRepository.save(image);
				}
			}
		}
		return travelPackages;
	}

	@Transactional
	public void deleteTravelPackages(List<Integer> travelPackageIds) {
		Iterable<TravelPackage> travelPackages = travelPackageRepository.findAllById(travelPackageIds);
		for(TravelPackage travelPackage : travelPackages) {
			for(ServiceOb service : travelPackage.getAvailableServiceList()) {
				imageRepository.deleteAll(service.getImages());
			}
			serviceRepository.deleteAll(travelPackage.getAvailableServiceList());
			imageRepository.deleteAll(travelPackage.getImages());
		}
		travelPackageRepository.deleteAll(travelPackages);
	}
}
