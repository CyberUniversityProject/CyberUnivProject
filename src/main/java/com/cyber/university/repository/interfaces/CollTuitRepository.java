package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.CollTuitFormDto;

/**
 * 
  * @FileName : CollTuitRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 단과대별 등록금 DTO
 */
@Mapper
public interface CollTuitRepository {
	
	public int insert(CollTuitFormDto collTuitFormDto);
	public List<CollTuitFormDto> selectByCollTuitDto();
	public int deleteById(Integer collegeId);
	public int updateByCollTuitDto(CollTuitFormDto collTuitFormDto);

}
