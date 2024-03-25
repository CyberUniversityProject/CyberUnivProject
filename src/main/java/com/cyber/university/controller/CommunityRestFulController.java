package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.repository.model.Community;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.CommunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/Community")
@Slf4j
@RequiredArgsConstructor
public class CommunityRestFulController {
	
	@Autowired
	private final CommunityService communityService;
	
	  // 게시글 삭제
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteCommunity(@PathVariable(name ="id")  int id) {
	        communityService.deleteCommunityAndComments(id);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Community and comments deleted successfully");
	    }
	 
	 
	 

	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateCommunity(@PathVariable(name ="id") Integer id, @RequestBody Community community) {
	        community.setId(id);
	        communityService.updateCommunity(community);
	        return ResponseEntity.ok("Community updated successfully");
	    }


	    
	    

}
