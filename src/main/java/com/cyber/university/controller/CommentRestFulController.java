package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.repository.model.Comment;
import com.cyber.university.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentRestFulController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestParam(name ="commentContent") String commentContent,
                                                @RequestParam(name ="userId") String userId,
                                                @RequestParam(name ="communityId") int communityId) {
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setUserId(userId);
        comment.setCommunityId(communityId);
        commentService.insertComment(comment);
        // 처리 결과를 반환합니다.
        return ResponseEntity.ok("success"); // 예시로 성공적으로 처리되었음을 나타내는 문자열을 반환합니다.
    }
	
	
	 @GetMapping("/count/{communityId}")
	    public ResponseEntity<?> getCommentCount(@PathVariable(name ="communityId") Integer communityId) {
	        int commentCount = commentService.getCommentCount(communityId);
	        return ResponseEntity.ok(commentCount);
	    }
}


