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
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.BreakRepository;
import com.cyber.university.repository.model.Break;
import com.cyber.university.utils.Define;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @FileName : BreakService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 휴, 복학 서비스
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class BreakService {

	@Autowired
	private final BreakRepository breakAppRepository;

	@Autowired
	private final StuStatService stuStatService;

	

	

	/**
	 * @param status 처리하지 않은 휴학 신청 내역 조회 (교직원용)
	 */
	@Transactional
	public List<Break> readByStatus(String status) {

		List<Break> breakAppEntityList = breakAppRepository.selectByStatus(status);

		return breakAppEntityList;
	}

	/**
	 * @param id 특정 휴학 신청서 조회
	 */
	@Transactional
	public Break readById(Integer id) {

		Break breakAppEntity = breakAppRepository.selectById(id);

		return breakAppEntity;
	}


	/**
	 * 휴학 신청 처리 (교직원)
	 */
	@Transactional
	public void updateById(Integer id, String status) {
		
		

		int resultRowCount = breakAppRepository.updateById(id, status);
		
		
		

		// 승인 시 학적 상태를 휴학으로 변경하기
		if (status.equals("승인")) {
			Break breakAppEntity = breakAppRepository.selectById(id);

			String newToDate = null;
			if (breakAppEntity.getToSemester() == 1) {
				newToDate = breakAppEntity.getToYear() + "-08-31";
			} else {
				newToDate = (breakAppEntity.getToYear() + 1) + "-02-28";
			}

			stuStatService.updateStatus(breakAppEntity.getStudentId(), "휴학", newToDate, id);
		}

		if (resultRowCount != 1) {
			throw new CustomRestfullException("신청이 처리되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	  * @Method Name : findBreakByStudentId
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 학생 id로 휴학 내역 조회
	  */
	@Transactional
	public List<Break> findBreakByStudentId(Integer studentId) {
		
		
		List<Break> breakList = breakAppRepository.selectByStudentId(studentId);
		
		
	
		return breakList;
	}

	/**
	  * @Method Name : deleteLeaveAppById
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : leaveAppId로 해당 휴학 취소 처리
	  */
	@Transactional
	public void deleteLeaveAppById(Integer userId,Integer LeaveAppid) {

		Break breakEntity = breakAppRepository.selectById(LeaveAppid);
		
		if(!breakEntity.getStudentId().equals(userId)) {
			throw new CustomRestfullException(Define.SUBMIT_CHECK_ID, HttpStatus.BAD_REQUEST);
		}
		
		breakAppRepository.deleteById(LeaveAppid);
	}
	
	
	/**
	 * @param studentId 해당 학생의 휴학 신청 내역 조회
	 */
	@Transactional
	public List<Break> readByStudentId(Integer studentId) {

		List<Break> breakAppEntityList = breakAppRepository.selectByStudentId(studentId);

		return breakAppEntityList;
	}

	


}
