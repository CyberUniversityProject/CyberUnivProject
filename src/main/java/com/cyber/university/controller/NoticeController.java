package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.NoticeFormDto;
import com.cyber.university.dto.NoticePageFormDto;
import com.cyber.university.repository.model.Notice;
import com.cyber.university.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : NoticeController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 
  * @프로그램 설명 : 공지사항 목록 출력
  */
@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired 
	NoticeService noticeService;
	
	/**
	 * 
	 * @return 공지사항 페이지
	 */
	@GetMapping("")
	public String notice(Model model)  {
	
		NoticePageFormDto noticePageFormDto = new NoticePageFormDto();
		List<Notice> noticeList = noticeService.readNotice(noticePageFormDto);
		log.info("dto1 : " + noticePageFormDto);
		if (noticeList.isEmpty()) {
			model.addAttribute("noticeList", null);
		} else {
			model.addAttribute("noticeList", noticeList);
		}
		
		return "/board/notice";
	}
	
	/**
	 * 
	 * @return 공지사항 입력 기능
	 */
	@PostMapping("/write")
	public String insertNotice(@Validated NoticeFormDto noticeFormDto) {
		noticeService.insertNotice(noticeFormDto);
		return "redirect:/notice";
	}
}
