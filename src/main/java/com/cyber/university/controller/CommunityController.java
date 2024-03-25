package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.repository.model.Community;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.CommunityService;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

	@Autowired
	private final CommunityService communityService;

	@Autowired
	private final HttpSession session;

	// 전체게시글 보기
	@GetMapping("/list")
	public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		List<Community> communityList = communityService.findAllPost(page, size);
		int totalPages = communityService.getTotalPages(size);
		model.addAttribute("communityList", communityList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		return "/community/list";
	}

	// 게시글 작성 페이지
	@GetMapping("/write")
	public String write(Model model) {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

		model.addAttribute("principal", principal);
		System.out.println("--------------" + principal);
		return "/community/write";
	}

	// 게시글 post
	@PostMapping("/create")
	public String createCommunity(@ModelAttribute Community community) {
		communityService.insertCommunity(community);
		return "redirect:/community/list"; // 적절한 리다이렉트 경로로 수정해주세요
	}

	@GetMapping("/all")
	public List<Community> getAllCommunities() {
		return communityService.getAllCommunities();
	}

	// 특정 게시글 상세보기
	@GetMapping("/{id}")
	public String getCommunityById(@PathVariable(name = "id") Integer id, Model model) {
		Community community = communityService.getCommunityByIdWithComments(id);
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		model.addAttribute("principal", principal);
		model.addAttribute("community", community);
		return "/community/detail";
	}

	// 특정게시글 수정 페이지
	@GetMapping("/update/{id}")
	public String updateCommunity(@PathVariable(name = "id") Integer id, Model model) {
		Community community = communityService.getCommunityByIdWithComments(id);
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		model.addAttribute("principal", principal);
		model.addAttribute("community", community);

		return "/community/update";
	}

	@PutMapping("/{id}")
	public String updateCommunity(@ModelAttribute Community community) {

		communityService.updateCommunity(community);

		return "redirect:/community/list";
	}

	// 삭제함수
	@DeleteMapping("/delete/{id}")
	public RedirectView deleteCommunity(@PathVariable(name = "id") int id) {
		communityService.deleteCommunityAndComments(id);
		return new RedirectView("/community/list");
	}

}
