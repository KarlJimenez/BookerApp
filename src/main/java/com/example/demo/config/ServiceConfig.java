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
	public CustomerService getCustomerService(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);
	}
	
	@Bean
	public FeedbackService getFeedbackService(FeedbackRepository feedbackRepository) {
		return new FeedbackService(feedbackRepository);
	}
	
	@Bean
	public ImageService getImageService(ImageRepository imageRepository) {
		return new ImageService(imageRepository);
	}
	
	@Bean
	public ReservationService getReservationService(ReservationRepository reservationRepository, 
			ServiceRepository serviceRepository, CustomerRepository customerRepository) {
		return new ReservationService(reservationRepository, serviceRepository, customerRepository);
	}
	
	@Bean
	public ServiceService getServiceService(ServiceRepository serviceRepository) {
		return new ServiceService(serviceRepository);
	}
	
	@Bean
	public TravelPackageService getTravelPackageService(TravelPackageRepository travelPackageRepository,
			ServiceRepository serviceRepository, ImageRepository imageRepository) {
		return new TravelPackageService(travelPackageRepository, serviceRepository, imageRepository);
	}
}
