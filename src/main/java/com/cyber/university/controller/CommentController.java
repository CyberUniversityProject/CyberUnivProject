package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.repository.model.Comment;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	@Autowired
	private final CommentService commentService;
	
	// 댓글 생성
	@PostMapping("/create")
	public void createComment(@RequestParam(name ="commentContent") String commentContent,
	                          @RequestParam(name ="userId") String userId,
	                          @RequestParam(name ="communityId") int communityId,
	                          @RequestParam(name ="role") String role) {
	    Comment comment = new Comment();
	    comment.setContent(commentContent);
	    comment.setUserId(userId); // 작성자의 ID 설정
	    comment.setCommunityId(communityId); // 게시글의 ID 설정
	    comment.setRole(role);
	    commentService.insertComment(comment);
	}


    @GetMapping("/community/{communityId}")
    public List<Comment> getCommentsByCommunityId(@PathVariable Integer communityId) {
        return commentService.getCommentsByCommunityId(communityId);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        comment.setId(id);
        commentService.updateComment(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable(name = "id") Integer id) {
        commentService.deleteComment(id);
    }

}
