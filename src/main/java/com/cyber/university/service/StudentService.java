package com.cyber.university.service;

import java.util.HashMap;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.LeaveAppDto;
import com.cyber.university.dto.LeaveStudentInfoDto;
import com.cyber.university.dto.StudentListForm;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.BreakRepository;
import com.cyber.university.repository.interfaces.StudentRepository;
import com.cyber.university.repository.model.Student;
import com.cyber.university.utils.Define;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @FileName : StudentService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 학생 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

	@Autowired
	private final StudentRepository studentRepository;
	@Autowired
	private final BreakRepository breakRepository;

	/**
	 * 
	 * @Method Name : readStudentList
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 학생리스트
	 */
	@Transactional
	public List<Student> readStudentList(StudentListForm studentListForm) {

		List<Student> list = null;

		if (studentListForm.getStudentId() != null) {
			list = studentRepository.selectByStudentId(studentListForm);
		} else if (studentListForm.getDeptId() != null) {
			list = studentRepository.selectByDepartmentId(studentListForm);
		} else {
			list = studentRepository.selectStudentList(studentListForm);
		}

		return list;
	}

	/**
	 * 
	 * @Method Name : readStudentAmount
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 학생수 가져오기
	 */
	@Transactional
	public Integer readStudentAmount(StudentListForm studentListForm) {

		Integer amount = null;
		if (studentListForm.getDeptId() != null) {
			amount = studentRepository.selectStudentAmountByDeptId(studentListForm.getDeptId());
		} else {
			amount = studentRepository.selectStudentAmount();
		}

		return amount;
	}

	/**
	 * @Method Name : findByStudentId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 박경진
	 * @변경이력 :
	 * @Method 설명 : studnetId로 학생 정보 조회
	 */
	public StudentInfoDto findByStudentId(Integer userId) {

		StudentInfoDto studentInfo = studentRepository.findStudentById(userId);

	

		return studentInfo;
	}

	/**
	  * @Method Name : updateStudentInfo
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : student info 수정(주소, 전화번호, 이메일)
	  */
	// TODO : 학과 전과 한다면 관리자 쪽에서, 학기도 자동으로 관리자 쪽 UPDATE 확인하기,
	@Transactional
	public int updateStudentInfo(Integer userId,StudentInfoDto studentInfoDto) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userId", userId);
		map.put("address", studentInfoDto.getAddress());
		map.put("tel", studentInfoDto.getTel());
		map.put("email", studentInfoDto.getEmail());
		map.put("originFileName", studentInfoDto.getOriginFileName());
		map.put("uploadFileName", studentInfoDto.getUploadFileName());
		
		int result = studentRepository.updateStudentInfo(map); 
		
		return result;
		
	}

	/**
	  * @Method Name : findLeaveStudentById
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학 할 학생 정보 조회
	  */
	public LeaveStudentInfoDto findLeaveStudentById(Integer userId) {
		
		LeaveStudentInfoDto leaveStudentInfoDto = studentRepository.findLeaveStudentById(userId);

		
		

		return leaveStudentInfoDto;
	}

	/**
	  * @Method Name : createLeaveApp
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 휴학 신청 정보 등록
	  */
	@Transactional
	public Integer createLeaveApp(Integer userId,LeaveAppDto leaveAppDto) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("studentGrade", leaveAppDto.getStudentGrade());
		map.put("fromYear", leaveAppDto.getFromYear());
		map.put("fromSemester", leaveAppDto.getFromSemester());
		map.put("toYear", leaveAppDto.getToYear());
		map.put("toSemester", leaveAppDto.getToSemester());
		map.put("type", leaveAppDto.getType());
		map.put("appDate", leaveAppDto.getAppDate());
		
		int result = studentRepository.createLeaveApp(map);
		
		if(result == 0) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.BAD_REQUEST);
		}
		
		return result;
		
	}

	/**
	  * @Method Name : getLeaveCount
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : userId로 휴학 횟수 카운팅
	  */
	public int getLeaveCount(Integer userId) {
		return studentRepository.getLeaveCount(userId);
	}

	/**
	  * @Method Name : hasPendingLeave
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 처리중인 휴학신청 내역 확인
	  */
	public int hasPendingLeave(Integer userId) {
		  return breakRepository.countByStatusAndUserId(userId);
	}
	
	
	/**
	 * 학생 학년과 학기 업데이트
	 */
	@Transactional
	public void updateStudentGradeAndSemester() {
		studentRepository.updateStudentGradeAndSemester1_2();
		studentRepository.updateStudentGradeAndSemester2_1();
		studentRepository.updateStudentGradeAndSemester2_2();
		studentRepository.updateStudentGradeAndSemester3_1();
		studentRepository.updateStudentGradeAndSemester3_2();
		studentRepository.updateStudentGradeAndSemester4_1();
		studentRepository.updateStudentGradeAndSemester4_2();
	}
	
}