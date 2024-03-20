package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.repository.interfaces.CommentRepository;
import com.cyber.university.repository.interfaces.CommunityRepository;
import com.cyber.university.repository.model.Comment;
import com.cyber.university.repository.model.Community;
import com.cyber.university.repository.model.Room;

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
	 
	 
	 
	 // 페이징 처리
	 public List<Community> findAllPost(int page, int size) {
	        // 페이징 처리를 위해 offset 계산
	        int offset = (page - 1) * size;
	        // 페이징된 강의실 목록 조회
	        return communityRepository.findAllwithPaging(offset, size);
	    }

	    public int getTotalPages(int size) {
	        // 전체 데이터 개수 가져오기
	        int totalRecords = communityRepository.getAllCount();
	        // 전체 페이지 수 계산
	        int totalPages = (int) Math.ceil((double) totalRecords / size);
	        return totalPages;
	    }

}
