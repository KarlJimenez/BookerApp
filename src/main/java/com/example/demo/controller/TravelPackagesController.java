package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TravelPackage;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackagesController {

	private TravelPackageService travelPackageService;
	
	public TravelPackagesController(TravelPackageService travelPackageService) {
		this.travelPackageService = travelPackageService;
	}
	
	@GetMapping
	public List<TravelPackage> getAllTravelPackages(){
		return travelPackageService.getAllTravelPackages();
	}
	
	@PutMapping
	public List<TravelPackage> updateTravelPackages(@RequestBody List<TravelPackage> travelPackages){
		return travelPackageService.updateTravelPackages(travelPackages);
	}
	
	@PostMapping
	public List<TravelPackage> addTravelPackages(@RequestBody List<TravelPackage> travelPackages){
		return travelPackageService.addTravelPackages(travelPackages);
	}
	
	@DeleteMapping
	public void deleteTravelPackages(@RequestParam("travelPackageId") List<Integer> travelPackageIds) {
		travelPackageService.deleteTravelPackages(travelPackageIds);
	}
}
