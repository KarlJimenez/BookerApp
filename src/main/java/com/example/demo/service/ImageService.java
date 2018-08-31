package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

public class ImageService {

	private ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	//*************** For Single Images ******************
	
	@Transactional
	public Image getImage(int imageId) {
		return imageRepository.findById(imageId).get();
	}
	
	@Transactional
	public Image updateImage(int imageId, Image image) {
		if(imageRepository.existsById(imageId)) {
			imageRepository.save(image);
		}
		return image;
	}
	
	@Transactional
	public void deleteImage(int imageId) {
		if(imageRepository.existsById(imageId)) {
			imageRepository.deleteById(imageId);
		}
	}
	
	//*************** For Multiple Images ******************
	
	@Transactional
	public List<Image> getAllImages(){
		return (List<Image>) imageRepository.findAll();
	}
	
	@Transactional
	public List<Image> addImages(List<Image> images){
		return (List<Image>) imageRepository.saveAll(images);
	}
	
	@Transactional
	public List<Image> updateImages(List<Image> images){
		for(Image image : images) {
			updateImage(image.getImageId(), image);
		}
		return images;
	}
	
	@Transactional
	public void deleteImages(List<Integer> imageIds) {
		for(Integer imageId : imageIds) {
			deleteImage(imageId);
		}
	}
	
	//*************** Special Methods ******************
	
	@Transactional
	public void deleteImagesFromParent(List<Image> images) {
		for(Image image : images) {
			deleteImage(image.getImageId());
		}
	}
}
