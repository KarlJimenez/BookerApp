package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TravelPackage;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages/{travelPackageId}")
public class TravelPackageController {

	private TravelPackageService travelPackageService;
	
	public TravelPackageController(TravelPackageService travelPackageService) {
		this.travelPackageService = travelPackageService;
	}
	
	@GetMapping
	public TravelPackage getTravelPackage(@PathVariable("travelPackageId") int travelPackageId) {
		return travelPackageService.getTravelPackage(travelPackageId);
	}
	
	@PutMapping
	public TravelPackage updateTravelPackage(@PathVariable("travelPackageId") int travelPackageId,
			@RequestBody TravelPackage travelPackage) {
		return travelPackageService.updateTravelPackage(travelPackageId, travelPackage);
	}
	
	@DeleteMapping
	public void deleteTravelPackage(@PathVariable("travelPackageId") int travelPackageId) {
		travelPackageService.deleteTravelPackage(travelPackageId);
	}
}
