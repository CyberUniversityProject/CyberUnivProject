package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.UpdateStudentGradeDto;
import com.cyber.university.dto.response.StuSubAppDto;
import com.cyber.university.dto.response.StuSubDayTimeDto;
import com.cyber.university.dto.response.StuSubSumGradesDto;
import com.cyber.university.dto.response.StudentInfoForProfessorDto;
import com.cyber.university.repository.model.StuSub;

@Mapper
public interface StuSubRepository {
	
	// 과목 -> 학생정보 확인
	List<StudentInfoForProfessorDto> selectBySubjectId(Integer subjectId);
	
	
	// cu_stu_sub의 grade 컬럼에 성적 입력
	int updateGradeByStudentIdAndSubjectId(UpdateStudentGradeDto updateStudentGradeDto);

	
	
	// 학생의 수강 신청 내역에 해당 강의가 있는지 조회
	StuSub selectByStudentIdAndSubjectId(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId);
	
	// 학생의 이번 학기 전체 수강 신청 내역 조회
	List<StuSubAppDto> selectListByStudentIdAndSemester(@Param("studentId") Integer studentId, @Param("subYear") Integer subYear, @Param("semester") Integer semester);
	
	// 학생의 수강 신청 학점 조회
	StuSubSumGradesDto selectSumGrades(@Param("studentId") Integer studentId, @Param("subYear") Integer subYear, @Param("semester") Integer semester);
	
	// 학생의 이번 학기 수강 신청 내역 시간표 조회
	List<StuSubDayTimeDto> selectDayTime(@Param("studentId") Integer studentId, @Param("subYear") Integer subYear, @Param("semester") Integer semester);
	
	// 수강 신청 내역 추가
	int insert(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId);
	
	// 수강 신청 내역 삭제
	int delete(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId);
	
	// 수강 신청 내역과 예비 수강 신청 내역 조인 후 조회 
	// type == 1 : 수강 신청, 예비 수강 신청에 둘 다 존재
	// type == 0 : 예비 수강 신청에만 존재
	List<StuSubAppDto> selectJoinListByStudentId(Integer studentId);
	
	// 성적 입력 시 취득 학점 컬럼도 추가
	int updateCompleteGradeByStudentIdAndSubjectId(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId, @Param("completeGrade") Integer completeGrade);
	

}
