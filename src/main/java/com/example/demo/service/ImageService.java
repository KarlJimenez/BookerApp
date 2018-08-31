package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

public class ImageService {

	private ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	//*************** For Multiple Images ******************
	
	public List<Image> getAllImages(){
		return (List<Image>) imageRepository.findAll();
	}
	
	public List<Image> addImages(List<Image> images){
		return (List<Image>) imageRepository.saveAll(images);
	}
	
	public List<Image> updateImages(List<Image> images){
		for(Image image : images) {
			if(imageRepository.existsById(image.getImageId())) {
				imageRepository.save(image);
			}
		}
		return images;
	}
	
	public void deleteImages(List<Image> images) {
		imageRepository.deleteAll(images);
	}
}
