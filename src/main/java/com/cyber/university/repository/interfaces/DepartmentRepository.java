package com.cyber.university.repository.interfaces;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.DepartmentFormDto;
import com.cyber.university.dto.DepartmentWithCollegeDto;
import com.cyber.university.repository.model.College;
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

	public int updateByDepartmentDto(DepartmentFormDto departmentFormDto);

  // 단과대학 join 
	public int joinWithCollege(College college);
	// 학과 등록
	public int insertById(Department department);
	// 학과 등록 리스트
	public List<Department> findAll();
	// 학과 삭제
	public int deleteById(Integer id);
	// 학과 수정
	public int updateById(Department department);
	// 학과 조회(글 상세보기와는 달리 수정을 위한 기능)
	public Department findById(Integer id);
	// 전체 페이지 불러오기
	public List<Department> findAllwithPasing(@Param ("offset") int offset, @Param ("limit") int limit);
	// 전체 게시물개수 계산
	public int getAllPgCount();
	
	
	public List<DepartmentWithCollegeDto> findAlldeptWithCol ();

}