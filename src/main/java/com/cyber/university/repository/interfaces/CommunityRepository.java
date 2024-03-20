package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.repository.model.Comment;
import com.cyber.university.repository.model.Community;
import com.cyber.university.repository.model.Room;

@Mapper
public interface CommunityRepository {

	public void insertCommunity(Community community);

	public List<Community> selectAllCommunities();

	public Community selectCommunityById(Integer id);

	public void updateCommunity(Community community);

	
	public void deleteCommunity(Integer id);
	
	
	
	public Community getCommunityById(Integer id);
	
	
	public long existById(int id);
	
	
	 // 페이징 처리된 게시판 목록 조회
    public List<Community> findAllwithPaging(@Param("offset") int offset, @Param("limit") int limit);

    // 전체 데이터 개수 가져오기
    public int getAllCount();
	
	



}
