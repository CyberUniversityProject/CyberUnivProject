package com.cyber.university.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyber.university.service.CommunityImageService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/imgUpload")
public class CommunityImageRestFulController {
	
	@Autowired
	private CommunityImageService communityImageService;
	
	
	 @PostMapping("/uploadImage")
	    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
	        if (!imageFile.isEmpty()) {
	            try {
	                byte[] imageData = imageFile.getBytes();
	                communityImageService.uploadImage(imageData);
	                return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
	            } catch (IOException e) {
	                e.printStackTrace();
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image file is empty");
	        }
	    }

}
