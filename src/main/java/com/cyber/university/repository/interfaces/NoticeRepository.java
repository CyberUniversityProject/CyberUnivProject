package com.cyber.university.repository.interfaces;

import java.util.List;

import com.cyber.university.dto.response.NoticeResDto;
import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.NoticeFormDto;
import com.cyber.university.dto.NoticePageFormDto;
import com.cyber.university.repository.model.Notice;

/**
  * @FileName : NoticeRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 3.11 기본 CRUD 완료
  * @프로그램 설명 : 공지사항 repository
  */

@Mapper
public interface NoticeRepository {
	public int insert(NoticeFormDto noticeFormDto);
	public List<Notice> selectByNoticeDto(NoticePageFormDto noticePageFormDto);
	public Notice selectById(Integer id);
	public int updateByNoticeDto(NoticeFormDto noticeFormDto);
	public int deleteById(Integer id);

	
	// 페이징 
	public Integer selectNoticeCount(NoticePageFormDto noticePageFormDto);
	/**
	 * 메인화면에 보여줄 공지사항 조회
	 * @Author : 준혁
	 * @return
	 */
	public List<NoticeResDto> selectMainNotice();
	
	
	/**
	 * 검색 기능
	 */
	public List<Notice> selectNoticeByTitle(NoticePageFormDto noticePageFormDto);
	public List<Notice> selectNoticeByKeyword(NoticePageFormDto noticePageFormDto);
}
