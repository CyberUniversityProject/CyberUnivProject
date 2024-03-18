package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.NoticeFormDto;
import com.cyber.university.dto.NoticePageFormDto;
import com.cyber.university.repository.model.Notice;
import com.cyber.university.service.NoticeService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : NoticeController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 조유빈
  * @변경이력 : 3.11 기본 CRUD 완료
  * @프로그램 설명 : 공지사항 Controller
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
		if (noticeList.isEmpty()) {
			model.addAttribute("noticeList", null);
		} else {
			model.addAttribute("noticeList", noticeList);
		}
		
		return "/notice/noticeList";
	}
	
	/**
	 * 
	 * @return 공지사항 입력 기능
	 */
	
	@GetMapping("/write")
	public String insertNoticeForm () {
		return "/notice/noticeWrite";
	}
	@PostMapping("/write")
	public String insertNotice(@Validated NoticeFormDto noticeFormDto) {
		noticeService.insertNotice(noticeFormDto);
		return "redirect:/notice";
	}

/**
  * @Method Name : update
  * @작성일 : 2024. 3. 13.
  * @작성자 : 조유빈
  * @변경이력 : 
  * @Method 설명 : 공지사항 상세 페이지, 수정 기능
  * @param noticeFormDto
  * @return
  */
	
	/**
	 * 
	 * @return 공지사항 상세페이지
	 */
	@GetMapping("/read")
	public String selectByIdNotice(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("id", id);
		Notice notice = noticeService.readNoticeById(id);
		if (notice == null) {
			model.addAttribute("notice", null);
		} else {
			model.addAttribute("notice", notice);
		}
		notice.setContent(notice.getContent().replace("\r\n", "<br>"));
		
		return "/notice/noticeDetail";
	}
	/**
	 * 
	 * @return 공지사항 수정 페이지
	 */
	@GetMapping("/update")
	public String update(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("id", id);
		
		Notice notice = noticeService.readNoticeById(id);
		model.addAttribute("notice", notice);
		return "/notice/noticeUpdate";
	}
	
	/**
	 * 
	 * @return 공지사항 수정 기능
	 */
	@PutMapping("/update")
	public String update(@Validated NoticeFormDto noticeFormDto) {
		noticeService.updateNotice(noticeFormDto);
		return "redirect:/notice";
	}
	
	/**
	 * 
	 * @return 공지사항 삭제 기능
	 */
	@GetMapping("/delete")
	public String delete(Model model,  @RequestParam("id") Integer id) {
		model.addAttribute("id", id);
		noticeService.deleteNotice(id);
		return "redirect:/notice";
	}
	
	
	/**
	  * @Method Name : noticeSearch
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 조유빈
	  * @변경이력 : 3.18 생성
	  * @Method 설명 : 공지사항 검색
	  * @return
	  */
	
	@GetMapping("/search")
	public String searchNoticeByKeyword(Model model, NoticePageFormDto noticePageFormDto) {
		model.addAttribute("keyword", noticePageFormDto.getKeyword());
		noticePageFormDto.setPage(0);
		List<Notice> noticeList = noticeService.searchNotice(noticePageFormDto);
		if(noticeList.isEmpty()) {
			model.addAttribute("noticeList", null);
		} else {
			model.addAttribute("noticeList", noticeList);
		}
		
		return "/notice/noticeList";
		
	}
	
//	public String searchNoticeByKeywordAndPage(Model model, NoticePageFormDto noticePageFormDto, 
//			@PathVariable Integer page, @RequestParam("keyword") String keyword) {
//		model.addAttribute("keyword", noticePageFormDto.getKeyword());
//		noticePageFormDto.setPage((page - 1) * 10);
//		List<Notice> noticeList = noticeService.searchNotice(noticePageFormDto);
//		Integer amount = noticeService.searchNoticeAmount(noticePageFormDto);
//		
//		model.addAttribute("list")
//		if(noticeList.isEmpty()) {
//			model.addAttribute("noticeList", null);
//		} else {
//			model.addAttribute("noticeList", noticeList);
//		}
//		
//		return "/notice/noticeList";
//		
//	}
}
