package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.NoticeDto;
import com.cyber.university.repository.model.Notice;

/**
  * @FileName : NoticeRepository.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 
  * @프로그램 설명 : 공지사항 repository
  */

@Mapper
public interface NoticeRepository {
	public int insert(NoticeDto noticeDto);
	public List<Notice> selectByNoticeDto(NoticeDto noticeDto);
}
