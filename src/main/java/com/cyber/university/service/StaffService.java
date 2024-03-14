package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.cyber.university.dto.SubjectFormDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.CollegeRepository;
import com.cyber.university.repository.interfaces.DepartmentRepository;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.interfaces.SyllaBusRepository;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.utils.SubjectUtil;

/**
 * 
  * @FileName : StaffService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교직원 서비스
 */
@Service
public class StaffService {
	
	
	@Autowired
	private CollegeRepository collegeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SyllaBusRepository syllaBusRepository;
	
	
	
	/**
	 * 강의 입력 서비스
	 */
	@Transactional
	public List<Subject> createSubjectAndSyllabus(@Validated SubjectFormDto subjectFormDto) {
		// 강의실, 강의시간 중복 검사
		List<Subject> subjectList = subjectRepository.findSubjectsByRoomIdAndDayAndYearAndSemester(subjectFormDto);
		if (subjectList != null) {
			SubjectUtil subjectUtil = new SubjectUtil();
			boolean result = subjectUtil.calculate(subjectFormDto, subjectList);
			if (result == false) {
				throw new CustomRestfullException("해당 시간대는 강의실을 사용중입니다! 다시 선택해주세요", HttpStatus.BAD_REQUEST);
			}
		}
		subjectRepository.insert(subjectFormDto);

		// 강의계획서에 강의 ID 저장
		Integer subjectId = subjectRepository.findIdByOrderById(subjectFormDto);
		syllaBusRepository.saveSubjectId(subjectId);
		return subjectList;
	}

	/**
	 * 강의 조회 서비스
	 */
	public List<Subject> readSubject() {
		List<Subject> subjectList = subjectRepository.selectAll();
		return subjectList;
	}

	/**
	 * 강의 삭제 서비스
	 */
	public int deleteSubject(Integer id) {
		int resultRowCount = subjectRepository.deleteById(id);
		syllaBusRepository.delete(id);
		return resultRowCount;
	}

	/**
	 * 강의 수정 서비스
	 */
	public int updateSubject(SubjectFormDto subjectFormDto) {
		// ID로 연도 학기 조회
		Subject subject = subjectRepository.selectSubjectById(subjectFormDto.getId());
		subjectFormDto.setSubYear(subject.getSubYear());
		subjectFormDto.setSemester(subject.getSemester());
		// 강의실, 강의시간 중복 검사
		List<Subject> subjectList = subjectRepository.findSubjectsByRoomIdAndDayAndYearAndSemester(subjectFormDto);
		if (subjectList != null) {
			SubjectUtil subjectUtil = new SubjectUtil();
			boolean result = subjectUtil.calculate(subjectFormDto, subjectList);
			if (result == false) {
				throw new CustomRestfullException("해당 시간대는 강의실을 사용중입니다! 다시 선택해주세요", HttpStatus.BAD_REQUEST);
			}
		}
		int resultRowCount = subjectRepository.updateBySubjectDto(subjectFormDto);
		if (resultRowCount != 1) {
			System.out.println("강의 수정 서비스 오류");
		}
		return resultRowCount;
	}
	
	/**
	 * 단과대 조회 서비스
	 */
	@Transactional
	public List<College> readCollege() {
		List<College> collegeList = collegeRepository.selectCollegeDto();
		return collegeList;
	}

}
