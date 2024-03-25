package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.payment.PaymentDto;
import com.cyber.university.dto.payment.PaymentInfoDto;
import com.cyber.university.dto.payment.SelectPaymentDateDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.PaymentRepository;
import com.cyber.university.repository.model.Payment;
import com.cyber.university.repository.model.Student;
import com.cyber.university.repository.model.Tuition;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
  * @FileName : PaymentService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 20. 
  * @작성자 : 장명근
  * @변경이력 :
  * @프로그램 설명 : 결제 서비스
  */
@Service
@RequiredArgsConstructor
public class PaymentService {
	
	@Autowired
	private final PaymentRepository paymentRepository;
	
	@Autowired
	private final HttpSession session;
	
	/**
	  * @Method Name : selectPaymentInfo
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 정보 조회
	  */
	public PaymentInfoDto selectPaymentInfo(Integer studentId, Integer tuiYear, Integer semester) {
		PrincipalDto princlipal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		return paymentRepository.selectPaymentInfo(princlipal.getId(), tuiYear, semester);
	}
	
	/**
	  * @Method Name : selectTuiYearAndSemester
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 년도, 학기 조회
	  */
	public PaymentInfoDto selectTuiYearAndSemester() {
		return paymentRepository.selectTuiYearAndSemester();
	}
	
	/**
	  * @Method Name : selectStudentName
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 이름 조회
	  */
	public Student selectStudentName(Integer studentId) {
		return paymentRepository.selectStudentName(studentId);
	}
	
	/**
	  * @Method Name : insertPayment
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 기록 등록
	  */
	@Transactional
	public void insertPayment(PaymentDto dto) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer StudentId = principal.getId();
		
		Payment payment = new Payment();
		payment.setUId(dto.getImpUid());
		payment.setMId(dto.getMerchantUid());
		payment.setStudentId(StudentId);
		payment.setBuyerName(dto.getBuyerName());
		payment.setTotalPrice(dto.getTotalPrice());
		
		int resultRowCount = paymentRepository.insertPayment(payment);
		if (resultRowCount != 1) {
			throw new CustomRestfullException("결제가 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} 	
	}
	
	/**
	  * @Method Name : upateTuitionStatus
	  * @작성일 : 2024. 3. 21.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 완료 후 고지서 상태 변경
	  */
	@Transactional
	public void upateTuitionStatus() {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		PaymentInfoDto dto = paymentRepository.selectTuiYearAndSemester();
		
		Tuition tuition = new Tuition();
		tuition.setTuiYear(dto.getTuiYear());
	    tuition.setSemester(dto.getSemester());
	    tuition.setStudentId(principal.getId());
		
	    System.out.println("tuition : " + tuition);
	    
		int result = paymentRepository.updateTuition(tuition);
		if (result != 1) {
			throw new CustomRestfullException("등록금 상태 업데이트에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	  * @Method Name : selectPaymentDate
	  * @작성일 : 2024. 3. 22.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 결제 완료 기간 조회
	  */
	public SelectPaymentDateDto selectPaymentDate() {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer studentId = principal.getId();
		
		return paymentRepository.selectPaymentDate(studentId);
	}
	
	/**
	  * @Method Name : selectPaymentAmount
	  * @작성일 : 2024. 3. 22.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 실제 결제 금액 조회
	  */
	public Tuition selectPaymentAmount(Integer tuiYear, Integer semester) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer studentId = principal.getId();
		
		return paymentRepository.selectPaymentAmount(studentId, tuiYear, semester);
	}
}
