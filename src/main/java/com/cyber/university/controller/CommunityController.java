package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.repository.model.Community;
import com.cyber.university.service.CommunityService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {
	
	
	@Autowired
	private  CommunityService communityService;
	
	
	 @GetMapping("/list")
	    public String list(Model model) {
	        List<Community> communityList = communityService.getAllCommunities();
	        model.addAttribute("communityList", communityList);
	        return "/community/list";
	    }
	
	
	
	  @PostMapping("/create")
	    public void createCommunity(@RequestBody Community community) {
	        communityService.insertCommunity(community);
	    }

	    @GetMapping("/all")
	    public List<Community> getAllCommunities() {
	        return communityService.getAllCommunities();
	    }

	    @GetMapping("/{id}")
	    public String getCommunityById(@PathVariable(name ="id") Integer id, Model model) {
	        Community community = communityService.getCommunityByIdWithComments(id);
	        model.addAttribute("community", community);
	        return "/community/detail";
	    }

	    @PutMapping("/{id}")
	    public void updateCommunity(@PathVariable Integer id, @RequestBody Community community) {
	        community.setId(id);
	        communityService.updateCommunity(community);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteCommunity(@PathVariable Integer id) {
	        communityService.deleteCommunity(id);
	    }

}
