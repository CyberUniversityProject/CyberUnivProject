package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.repository.model.Comment;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentRestFulController {

	@Autowired
	private final CommentService commentService;

	@PostMapping("/create")
	public ResponseEntity<?> createComment(@RequestParam(name = "commentContent") String commentContent,
			@RequestParam(name = "userId") String userId, @RequestParam(name = "communityId") int communityId, @RequestParam(name ="role") String role) {
		Comment comment = new Comment();
		comment.setContent(commentContent);
		comment.setUserId(userId);
		comment.setCommunityId(communityId);
		comment.setRole(role);
		commentService.insertComment(comment);
		// 처리 결과를 반환합니다.
		return ResponseEntity.ok("success"); // 예시로 성공적으로 처리되었음을 나타내는 문자열을 반환합니다.
	}

	// 댓글 수 가져오기
	@GetMapping("/count/{communityId}")
	public ResponseEntity<?> getCommentCount(@PathVariable(name = "communityId") Integer communityId) {
		int commentCount = commentService.getCommentCount(communityId);
		return ResponseEntity.ok(commentCount);
	}

	// 수정함수
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateComment(@PathVariable(name = "id") Integer id, @RequestBody Comment comment) {
		commentService.updateComment(comment);
		return ResponseEntity.ok("Comment updated successfully");
	}

	// 삭제함수
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Integer id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// 한건 조회
	 @GetMapping("/{id}")
	    public ResponseEntity<Comment> getCommentById(@PathVariable(name ="id") Integer id) {
	        Comment comment = commentService.getCommentById(id);
	        if (comment != null) {
	            return ResponseEntity.ok(comment);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
