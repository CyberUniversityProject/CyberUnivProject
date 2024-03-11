package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;

/**
  * @FileName : ProfessorRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 교수 Repository
  */
@Mapper
public interface ProfessorRepository {
	
	// 24.03.11 교수 내 정보 조회
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id);
	
	// 24.03.11 교수 개인 정보 조회
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id);
	
	// 24.03.11 교수 정보 수정
	public UpdateProfessorInfoDto updateProfessorInfo(UpdateProfessorInfoDto updateDto);
	
	// 24.03.11 비밀번호 찾기
	public String selectPassword(Integer id);
}
