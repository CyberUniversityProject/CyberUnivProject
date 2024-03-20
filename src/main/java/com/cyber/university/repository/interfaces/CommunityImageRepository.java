package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityImageRepository {
	
	public void uploadImage(byte[] imageData);

}
