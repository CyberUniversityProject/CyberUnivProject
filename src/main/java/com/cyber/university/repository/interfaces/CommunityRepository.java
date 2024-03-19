package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.repository.model.Comment;
import com.cyber.university.repository.model.Community;

@Mapper
public interface CommunityRepository {

	public void insertCommunity(Community community);

	public List<Community> selectAllCommunities();

	public Community selectCommunityById(Integer id);

	public void updateCommunity(Community community);

	
	public void deleteCommunity(Integer id);
	
	
	
	public Community getCommunityById(Integer id);
	
	
	public long existById(int id);
	
	



}
