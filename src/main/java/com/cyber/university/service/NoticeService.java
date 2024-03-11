package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.NoticeDto;
import com.cyber.university.repository.interfaces.NoticeRepository;
import com.cyber.university.repository.model.Notice;

/**
  * @FileName : NoticeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 
  * @프로그램 설명 : notice 관련 서비스(목록 출력)
  */

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
	// public void readNotice(NoticeDto noticeDto) {
	//	int result
	// }
	
	
	/**
	 * 공지사항 조회 
	 */
	public List<Notice> readNotice(NoticeDto noticeDto) {
		List<Notice> noticeList = noticeRepository.selectByNoticeDto(noticeDto);
		return noticeList;
	}

}
