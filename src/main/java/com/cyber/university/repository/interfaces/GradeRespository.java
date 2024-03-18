package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.response.GradeDto;
import com.cyber.university.dto.response.GradeForScholarshipDto;
import com.cyber.university.dto.response.MyGradeDto;

/**
 * 
 * @FileName : GradeRespository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 15.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 성적 레파지토리
 */
@Mapper
public interface GradeRespository {


	// 성적 평균 가져오기
	public GradeForScholarshipDto findAvgGradeByStudentIdAndSemester(@Param("studentId") Integer studentId,
			@Param("subYear") Integer subYear, @Param("semester") Integer semester);


}
