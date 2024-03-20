package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.repository.interfaces.CommunityImageRepository;

@Service
public class CommunityImageService {
	
	@Autowired
    private CommunityImageRepository communityImageRepository;
    
    public void uploadImage(byte[] imageData) {
        communityImageRepository.uploadImage(imageData);
    }

}
