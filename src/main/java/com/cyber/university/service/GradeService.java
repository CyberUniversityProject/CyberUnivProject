package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.response.GradeDto;
import com.cyber.university.dto.response.GradeForScholarshipDto;
import com.cyber.university.dto.response.MyGradeDto;
import com.cyber.university.repository.interfaces.GradeRespository;
import com.cyber.university.utils.Define;

/**
 * 
  * @FileName : GradeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 성적 서비스
 */
@Service
public class GradeService {
	
	
	@Autowired
	private GradeRespository gradeRespository;


	// 성적 평균 가져오기
	public GradeForScholarshipDto readAvgGrade(Integer studentId, Integer subYear, Integer semester) {
		GradeForScholarshipDto gradeEntity = gradeRespository.findAvgGradeByStudentIdAndSemester(studentId, subYear,
				semester);
		return gradeEntity;
	}

}
