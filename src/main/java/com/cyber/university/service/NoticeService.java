package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cyber.university.dto.NoticeFormDto;
import com.cyber.university.dto.NoticePageFormDto;
import com.cyber.university.repository.interfaces.NoticeRepository;
import com.cyber.university.repository.model.Notice;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : NoticeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 
  * @프로그램 설명 : notice 관련 서비스(목록 출력)
  */
@Slf4j
@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	
	/**
	 * 
	  * @Method Name : readNotice
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 조유빈
	  * @변경이력 : 
	  * @Method 설명 : 공지사항 작성, 목록 조회
	  * @param noticeDto
	  * @return
	 */
	
	/**
	 * 공지사항 작성
	 */
	 public void insertNotice(@Validated NoticeFormDto noticeFormDto) {
	    log.info("dto2 : " + noticeFormDto);
		int resultRowCount = noticeRepository.insert(noticeFormDto);
		if(resultRowCount != 1) {
			System.out.println("공지 입력 오류");
		}
		
	 }
	
	
	/**
	 * 공지사항 조회 
	 */
	public List<Notice> readNotice(NoticePageFormDto noticePageFormDto) {
		log.info("dto3 : " + noticePageFormDto);
		List<Notice> noticeList = noticeRepository.selectByNoticeDto(noticePageFormDto);
		return noticeList;
	}

}
