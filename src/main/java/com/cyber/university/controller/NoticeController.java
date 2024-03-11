package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.NoticeDto;
import com.cyber.university.repository.model.Notice;
import com.cyber.university.service.NoticeService;

/**
  * @FileName : NoticeController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 
  * @프로그램 설명 : 공지사항 목록 출력
  */

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired 
	NoticeService noticeService;

	@GetMapping("")
	public String notice(Model model) {
		NoticeDto noticeDto = new NoticeDto();
		List<Notice> noticeList = noticeService.readNotice(noticeDto);
		model.addAttribute("noticeList", noticeList);
		
		return "/board/notice";
	}
}
