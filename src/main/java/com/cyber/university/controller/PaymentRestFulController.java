package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.dto.PaymentDto;
import com.cyber.university.dto.PaymentInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Student;
import com.cyber.university.repository.model.Tuition;
import com.cyber.university.service.PaymentService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class PaymentRestFulController {
	
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/buy/{tuiYear}/{semester}")
	public PaymentInfoDto paymentPage() {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		
		PaymentInfoDto dto = paymentService.selectTuiYearAndSemester();
		Student student = paymentService.selectStudentName(principal.getId());
		log.info("student : " + student);
		PaymentInfoDto paymentdto = paymentService.selectPaymentInfo(principal.getId() ,dto.getTuiYear(), dto.getSemester());
		paymentdto.setBuyerName(student.getName());
		log.info("paymentdto : " + paymentdto);
		return paymentdto;
	}
	
	@PostMapping("/buy/{tuiYear}/{semester}")
	public ResponseEntity<?> paymentProc(@RequestBody PaymentDto dto) {
		
		try {				
				paymentService.insertPayment(dto);			
				paymentService.upateTuitionStatus();	
            	return ResponseEntity.ok("결제가 완료되었습니다.");
            	
        } catch (CustomRestfullException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
	
}
