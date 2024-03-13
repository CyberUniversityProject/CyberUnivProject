package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.BreakRepository;
import com.cyber.university.repository.model.Break;

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
public class BreakService {

	@Autowired
	private BreakRepository breakAppRepository;

	@Autowired
	private StuStatService stuStatService;

	

	

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
		
		System.out.println("id값 : " + id);
		System.out.println("status값 : " + status);

		int resultRowCount = breakAppRepository.updateById(id, status);
		
		
		System.out.println("resultRowCount ?" + resultRowCount);

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



}
