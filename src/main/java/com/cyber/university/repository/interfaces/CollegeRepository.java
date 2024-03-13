package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.repository.model.College;

/**
  * @FileName : CollegeRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 김수현
  * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
  * @프로그램 설명 : 단과대학 등록 repository
  */
@Mapper
public interface CollegeRepository {

	//단과대학 등록
	public int insertById(College college);
	// 단과대학 등록 리스트
	public List<College> findAll();
	// 단과대학 삭제
	public int deleteById(Integer id);
	// 단과대학 수정
	public int updateById(College college);
	// 단과대학 조회(글 상세보기와는 달리 수정을 위한 기능)
	public College findById(Integer id);
	// 전체 페이지 불러오기
	public List<College> findAllwithPasing(@Param ("offset") int offset, @Param ("limit") int limit, @Param("type") String type);
	// 전체 게시물개수 계산
	public int getAllPgCount();
}
