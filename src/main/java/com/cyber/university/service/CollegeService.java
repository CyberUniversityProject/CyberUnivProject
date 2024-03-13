package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cyber.university.repository.interfaces.CollegeRepository;
import com.cyber.university.repository.model.College;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : CollegeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 김수현
  * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
  * @프로그램 설명 : 단과대학 등록 service
  */
@Slf4j
@Service
public class CollegeService {

	@Autowired
	private CollegeRepository collegeRepository;
	
	@Transactional
	public String insert(College college) {
		System.out.println(college.toString());
		collegeRepository.insertById(college);
		return college.getName();
	}
	// 단과대학 목록 전체 불러오기
	public List<College> collegeList() {
		System.out.println("collegeList" + toString());
		return collegeRepository.findAll();
	}
/**
  * @FileName : CollegeService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 김수현
  * @변경이력 :
  * @프로그램 설명 : 단과대학 삭제
  */
	// 단과대학 삭제
	public int deleteById(Integer id) {
		System.out.println("delete" + id);
		return collegeRepository.deleteById(id);
	}
	// 단과대학 조회 수정 시에 쓰임 수정 누르면 기존 내용을 가지로 페이지 이동
	public College findById(Integer id) {
		log.info("id");
		College college = collegeRepository.findById(id);
		log.info("where");
		return college;
	}
	//단과대학 수정
	@Transactional
	public void updateById(College college) {
		log.info("update");
		System.out.println("update" + college);
		log.info("complete");
		int result = collegeRepository.updateById(college);
	}
		
	}

