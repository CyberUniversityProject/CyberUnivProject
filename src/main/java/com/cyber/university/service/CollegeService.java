package com.cyber.university.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.CollegeDto;
import com.cyber.university.repository.interfaces.CollegeRepository;
import com.cyber.university.repository.interfaces.DepartmentRepository;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Department;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CollegeService {
	
	
	@Autowired
	private final CollegeRepository collegeRepository;

	@Autowired
	private final DepartmentRepository departmentRepository;

	/**
	 * @param college_id
	 * @return id로 해당 단과대 정보 가져옴
	 */
	public College readCollById(Integer id) {

		College collEntity = collegeRepository.selectCollegeDtoById(id);
		return collEntity;
	}

	/**
	 * @param dept_id
	 * @return id로 해당 학과 정보 가져옴
	 */
	public Department readDeptById(Integer id) {

		Department deptEntity = departmentRepository.selectById(id);
		return deptEntity;
	}

	/**
	 * @return 전체 학과 정보 조회
	 */
	public List<Department> readDeptAll() {

		List<Department> deptEntityList = departmentRepository.selectByDepartmentDto();
		return deptEntityList;
	}

	@Transactional
	public String insert(College college) {
	
		collegeRepository.insertById(college);
		return college.getName();
	}
	// 단과대학 목록 전체 불러오기
	public List<College> collegeList() {
		
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
		return collegeRepository.deleteById(id);
	}
	
	// 단과대학 조회 수정 시에 쓰임 수정 누르면 기존 내용을 가지로 페이지 이동
	public College findById(Integer id) {
		
		College college = collegeRepository.findById(id);
	
		return college;
	}
	//단과대학 수정
	@Transactional
	public void updateById(College college) {
		
		System.out.println("update" + college);
		
		int result = collegeRepository.updateById(college);
	}
	
	}

