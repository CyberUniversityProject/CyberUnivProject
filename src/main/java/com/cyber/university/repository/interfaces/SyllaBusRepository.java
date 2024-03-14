package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.SyllaBusFormDto;

/**
 * 
 * @FileName : SyllaBusRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 14.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 강의계획서
 */
@Mapper
public interface SyllaBusRepository {

	// 강의 등록하면 강의 id만 저장
	public Integer saveSubjectId(Integer subjectId);

	// 강의 삭제하면 해당강의 id 강의서 삭제하기
	public int delete(Integer subjectId);

	// 강의계획서 수정
	public int updateSyllabus(SyllaBusFormDto syllaBusFormDto);

}
