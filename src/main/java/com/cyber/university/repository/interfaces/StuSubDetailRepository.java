package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.UpdateStudentGradeDto;
/**
 * 
  * @FileName : StuSubDetailRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생의 수강 과목에 대한 세부 정보
 */
@Mapper
public interface StuSubDetailRepository {

	// 학생 성적 업데이트
	int updateGrade(UpdateStudentGradeDto updateStudentGradeDto);
	
	// insert
	int insert(@Param("id") Integer id, @Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId);

}
