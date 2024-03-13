package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.CollegeFormDto;
import com.cyber.university.repository.model.College;

/**
 * 
  * @FileName : CollegeRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 단과대 레파지토리
 */
@Mapper
public interface CollegeRepository {
	
	
	public int insert(CollegeFormDto CollegeFormDto);

	public List<College> selectCollegeDto();

	public int selectCollegeDtoByName(String name);
	public College selectCollegeDtoById(Integer id);
	public int deleteById(Integer id);

}
