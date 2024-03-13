package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyber.university.dto.DepartmentFormDto;
import com.cyber.university.repository.model.Department;

/**
 * 
 * @FileName : DepartmentRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 학과 레파지토리
 */

@Mapper
public interface DepartmentRepository {
	public int insert(DepartmentFormDto departmentFormDto);

	public Department selectById(Integer id);

	public List<Department> selectByDepartmentDto();

	public int deleteById(Integer id);

	public int updateByDepartmentDto(DepartmentFormDto departmentFormDto);

}
