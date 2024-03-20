package com.cyber.university.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.PaymentInfoDto;
import com.cyber.university.dto.UpdateTuitionInfoDto;
import com.cyber.university.repository.model.Payment;
import com.cyber.university.repository.model.Student;

/**
  * @FileName : PaymentRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 20. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제 레파지토리
  */
@Mapper
public interface PaymentRepository {
	
	/**
	  * @Method Name : selectPaymentInfo
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 정보 조회
	  */
	public PaymentInfoDto selectPaymentInfo(@Param("studentId") Integer studentId, @Param("tuiYear") Integer tuiYear, @Param("semester") Integer semester);
	
	/**
	  * @Method Name : selectTuiYearAndSemester
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 년도, 학기 조회
	  */
	public PaymentInfoDto selectTuiYearAndSemester();
	
	/**
	  * @Method Name : selectStudentName
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 이름 조회
	  */
	public Student selectStudentName(Integer studentId);
	
	/**
	  * @Method Name : insertPayment
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 기록 등록
	  */
	public int insertPayment(Payment payment);
	
	/**
	  * @Method Name : updateTuition
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 후 고지서 업데이트
	  */
	public int updateTuition(UpdateTuitionInfoDto dto);
}
