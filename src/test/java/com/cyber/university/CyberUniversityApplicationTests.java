package com.cyber.university;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cyber.university.repository.model.Community;
import com.cyber.university.service.CommunityService;

@SpringBootTest
class CyberUniversityApplicationTests {
	
	@Autowired
	private CommunityService communityService;

	@Test
	void contextLoads() {
	}
	
	
	 @Test
	    void insertDummyData() {
	        for (int i = 0; i < 1000; i++) {
	            Community community = new Community();
	            community.setTitle("게시글 제목 " + i);
	            community.setContent("게시글 내용 " + i);
	            community.setUserName("user" + i);
	            communityService.insertCommunity(community);
	        }
	    }

}
