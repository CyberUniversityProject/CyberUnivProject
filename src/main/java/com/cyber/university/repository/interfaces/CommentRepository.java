package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.repository.model.Comment;

@Mapper
public interface CommentRepository {
	

    public void insertComment(Comment comment);

    public List<Comment> selectCommentsByCommunityId(Integer communityId);

    public Comment selectCommentById(Integer id);

    public void updateComment(Comment comment);

    public void deleteComment(Integer id);
    
    
    public List<Comment> getCommentsByCommunityId(Integer communityId);
    
    public Integer selectCommentCount(Integer communityId);
    
    
 // 연관된 댓글 삭제
    public int deleteCommentsByCommunityId(Integer id);
    
   
}
