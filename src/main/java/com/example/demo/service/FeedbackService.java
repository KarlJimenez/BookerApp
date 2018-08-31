package com.example.demo.service;

import com.example.demo.repository.FeedbackRepository;

public class FeedbackService {

	private FeedbackRepository feedbackRepository;
	
	public FeedbackService(FeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}
}
