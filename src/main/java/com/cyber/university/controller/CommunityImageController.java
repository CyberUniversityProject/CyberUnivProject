package com.cyber.university.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cyber.university.service.CommunityImageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommunityImageController {
	
    @Autowired
    private CommunityImageService communityImageService;
    
    
    @GetMapping("/imgUpload")
    public String uploadPage() {
    	
    	return "/community/imgUpload";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
    	System.out.println("imageFile???"+ imageFile);
        if (!imageFile.isEmpty()) {
            try {
                byte[] imageData = imageFile.getBytes();
                System.out.println("imageData???" + imageData);
                communityImageService.uploadImage(imageData);
                return "redirect:/success"; // 이미지 업로드 성공 시 리다이렉트
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/error"; // 이미지 업로드 실패 시 리다이렉트
    }

}
