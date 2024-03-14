package com.cyber.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.university.service.ApplySubjectService;
/**
 * 
  * @FileName : ApplySubjectRestFulController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의 신청 승인/거절 restfulController
 */
@RestController
@RequestMapping("/api/applySub")
public class ApplySubjectRestFulController {
	
	 @Autowired
	    private ApplySubjectService applySubjectService;
	 
	 
	 
	 // 강의 개설 승인
	 @PutMapping("/updateApproval/{id}")
	    public ResponseEntity<?> updateApproval(@PathVariable(name = "id") int id, @RequestParam(name = "approval") String approval) {
	        applySubjectService.updateApproval(id, approval);
	        return ResponseEntity.ok("상태값 변경 성공");
	    }
	 
	 
	 // 강의 개설 반려
	 @PutMapping("/updateReason/{id}")
	 public ResponseEntity<?> updateReason(@PathVariable(name = "id") int id, @RequestParam(name = "reason") String reason) {
		 applySubjectService.updateReason(id, reason);
		 
		 return ResponseEntity.ok("반려 요청 성공");
	 }
	 
	 

}
