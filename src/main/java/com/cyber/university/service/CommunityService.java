package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.repository.interfaces.CommentRepository;
import com.cyber.university.repository.interfaces.CommunityRepository;
import com.cyber.university.repository.model.Comment;
import com.cyber.university.repository.model.Community;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private CommentRepository commentRepository;

	public void insertCommunity(Community community) {
		communityRepository.insertCommunity(community);
	}

	public List<Community> getAllCommunities() {
		return communityRepository.selectAllCommunities();
	}

	public Community getCommunityById(Integer id) {
		return communityRepository.selectCommunityById(id);
	}

	public Community getCommunityByIdWithComments(Integer id) {
		Community community = communityRepository.getCommunityById(id);
		List<Comment> comments = commentRepository.getCommentsByCommunityId(id);
		community.setComments(comments);
		return community;
	}

	public void updateCommunity(Community community) {
		communityRepository.updateCommunity(community);
	}

	 @Transactional
	    public void deleteCommunityAndComments(Integer communityId) {
	        // 해당 게시글에 연관된 댓글들을 먼저 삭제
	        commentRepository.deleteCommentsByCommunityId(communityId);
	        
	        // 게시글 삭제
	        communityRepository.deleteCommunity(communityId);
	    }

}
