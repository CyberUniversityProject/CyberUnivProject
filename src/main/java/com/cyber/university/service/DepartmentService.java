package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.DepartmentDto;
import com.cyber.university.dto.DepartmentWithCollegeDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.DepartmentRepository;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Department;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : DepartmentService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 김수현
  * @변경이력 : 브랜치 shu로 변경 후 프로젝트 새로 setting
  * @프로그램 설명 : 학과 등록 service
  */
@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

	@Autowired
	private final DepartmentRepository departmentRepository;
	
	@Transactional
	public void insert(DepartmentDto departmentDto) {
		
		Department department = new Department();
		department.setName(departmentDto.getName());
		department.setCollegeId(departmentDto.getCollegeId());
		
		 int result = departmentRepository.insertById(department);
		 if (result != 1) {
			throw new CustomRestfullException("등록 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 학과 목록 전체 불러오기
	public List<Department> departmentList() {
		List<Department> list = departmentRepository.findAll();
		return departmentRepository.findAll();
	}
	// 학과 삭제
	public int deleteById(Integer id) {
		return departmentRepository.deleteById(id);
	}
	// 학과 조회 수정 시에 쓰임 수정 누르면 기존 내용을 가지로 페이지 이동
	public Department findById(Integer id) {
		Department department = departmentRepository.findById(id);
		return department;
	}
	
	// 학과 수정
	@Transactional
	public void updateById(Department department) {
		int result = departmentRepository.updateById(department);
	}
	
	
	@Transactional
	public List<DepartmentWithCollegeDto> findAll(){
		return departmentRepository.findAlldeptWithCol();
	}
	/**
	  * @FileName : DepartmentService.java
	  * @Project : CyberUniversity
	  * @Date : 2024. 3. 19. 
	  * @작성자 : 김수현
	  * @변경이력 : 
	  * @프로그램 설명 : department paging 
	  */
	public List<Department> findAllDepartments(int page, int size) {
		// 페이징 처리를 위해 offset 계산
		 int offset = (page -1) * size;
		 // 페이징 된 학과 목록 조회
		 return departmentRepository.findAllwithPasing(offset, size);
	}
	
	public int getTotalPages(int size) {
		// 전체 데이터 개수 가져오기
		int totalRecords = departmentRepository.getAllPgCount();
		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double)totalRecords / size);
		return totalPages;
	}
	
	
	
	}

