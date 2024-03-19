package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.repository.interfaces.CommentRepository;
import com.cyber.university.repository.model.Comment;

@Service
public class CommentService {
	
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	 public void insertComment(Comment comment) {
	        commentRepository.insertComment(comment);
	    }

	    public List<Comment> getCommentsByCommunityId(Integer communityId) {
	        return commentRepository.selectCommentsByCommunityId(communityId);
	    }

	    public Comment getCommentById(Integer id) {
	        return commentRepository.selectCommentById(id);
	    }

	    public void updateComment(Comment comment) {
	        commentRepository.updateComment(comment);
	    }

	    public void deleteComment(Integer id) {
	        commentRepository.deleteComment(id);
	    }

}
