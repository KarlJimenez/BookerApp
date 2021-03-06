package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.TravelPackageRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ServiceService;
import com.example.demo.service.TravelPackageService;

@Configuration
public class ServiceConfig {

	@Bean
	public CustomerService customerService(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);
	}
	
	@Bean
	public FeedbackService feedbackService(FeedbackRepository feedbackRepository) {
		return new FeedbackService(feedbackRepository);
	}
	
	@Bean
	public ImageService imageService(ImageRepository imageRepository) {
		return new ImageService(imageRepository);
	}
	
	@Bean
	public ReservationService reservationService(ReservationRepository reservationRepository, 
			CustomerService customerService, ServiceService serviceService) {
		return new ReservationService(reservationRepository, customerService, serviceService);
	}
	
	@Bean
	public ServiceService serviceService(ServiceRepository serviceRepository, 
			ImageService imageService, ReservationRepository reservationRepository) {
		return new ServiceService(serviceRepository, imageService, reservationRepository);
	}
	
	@Bean
	public TravelPackageService travelPackageService(TravelPackageRepository travelPackageRepository,
			ServiceService serviceService, ImageService imageService) {
		return new TravelPackageService(travelPackageRepository, serviceService, imageService);
	}
}
