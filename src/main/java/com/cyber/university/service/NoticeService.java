package com.cyber.university.service;

import java.util.List;

import com.cyber.university.dto.response.NoticeResDto;
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
  * @변경이력 : 3.11 기본 CRUD 완료
  * @프로그램 설명 : 공지사항 Service
  */
@Slf4j
@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	/**
	 * 
	  * @Method Name : insertNotice
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
		int resultRowCount = noticeRepository.insert(noticeFormDto);
		if(resultRowCount != 1) {
			System.out.println("공지 입력 오류");
		}
		
		
	 }
	
	/**
	 * 공지사항 조회 
	 */
	public List<Notice> readNotice(NoticePageFormDto noticePageFormDto) {
		List<Notice> noticeList = noticeRepository.selectByNoticeDto(noticePageFormDto);
		return noticeList;
	}

	/**
	 * 
	  * @Method Name : readByIdNotice
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 조유빈
	  * @변경이력 : 
	  * @Method 설명 : 공지사항 상세페이지
	  * @param id
	  * @return
	 */
	
	/**
	 * 공지사항 상세페이지 
	 */
	public Notice readNoticeById(Integer id) {
		Notice notice = noticeRepository.selectById(id);
		// 조회수
		// Integer view = noticeRepository.updateView(id);
		// notice.setViews(view);
		return notice;
	}
	
	/**
	 * 공지사항 수정 
	 */
	public int updateNotice(NoticeFormDto noticeFormDto) {
		int resultRowCount = noticeRepository.updateByNoticeDto(noticeFormDto);
		if (resultRowCount != 1) {
			System.out.println("공지 수정 서비스 오류");
		}
		return resultRowCount;
	}

	/**
	 * 공지사항 삭제
	 */
	public int deleteNotice(Integer id) {
		int resultRowCount = noticeRepository.deleteById(id);
		return resultRowCount;
	}

	/**
	 * 메인 화면에 보여줄 공지사항 조회
	 * @Author : 준혁
	 * @return List<NoticeResDto>
	 */
	public List<NoticeResDto> readMainNotice() {
		return noticeRepository.selectMainNotice();
	}
	
	/**
	 * 공지사항 검색
	 */
	public List<Notice> searchNotice(NoticePageFormDto noticePageFormDto){
		List<Notice> noticeList = null;
		
		if("title".equals(noticePageFormDto.getType())) {
			noticeList = noticeRepository.selectNoticeByTitle(noticePageFormDto);
		} else {
			noticeList = noticeRepository.selectNoticeByKeyword(noticePageFormDto);
		}
		return noticeList;
	}
	
	/**
	 * 공지사항 개수 확인
	 */
	public Integer noticeAmount (NoticePageFormDto noticePageFormDto) {
		Integer amount = null;
		if(noticePageFormDto.getKeyword() == null) {
			amount = noticeRepository.selectNoticeCount(noticePageFormDto);
		} else {
			if("title".equals(noticePageFormDto.getType())) {
				amount = noticeRepository.selectNoticeCountByTitle(noticePageFormDto);
			}else {
				amount = noticeRepository.selectNoticeCountByKeyword(noticePageFormDto);
			}
		}
		return amount;
	}
	
}
