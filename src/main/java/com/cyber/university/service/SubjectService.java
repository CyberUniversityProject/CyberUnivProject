package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.AllSubjectSearchFormDto;
import com.cyber.university.dto.CurrentSemesterSubjectSearchFormDto;
import com.cyber.university.dto.response.SubjectDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.utils.Define;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * @return 전체 강의 조회에 사용할 강의 정보 (학생용) 전체 연도-학기에 해당하는 강의가 출력됨
	 */
	@Transactional
	public List<SubjectDto> readSubjectList() {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoAll();

		return subDtoList;
	}

	/**
	 * 페이징 처리
	 */
	@Transactional
	public List<SubjectDto> readSubjectListPage(Integer page) {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoAllLimit(page);

		return subDtoList;
	}

	/**
	 * @param allSubjectSearchFormDto
	 * @return 전체 강의 목록에서 필터링할 때 출력할 강의
	 */
	@Transactional
	public List<SubjectDto> readSubjectListSearch(AllSubjectSearchFormDto allSubjectSearchFormDto) {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoBySemesterAndDeptAndName(allSubjectSearchFormDto);

		return subDtoList;
	}

	/**
	 * @return 수강 신청에 사용할 강의 정보 (학생용) 현재 연도-학기에 해당하는 강의만 출력됨
	 */
	@Transactional
	public List<SubjectDto> readSubjectListByCurrentSemester() {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoBySemester(Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		return subDtoList;
	}

	/**
	 * 페이징 처리
	 */
	@Transactional
	public List<SubjectDto> readSubjectListByCurrentSemesterPage(Integer page) {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoBySemesterLimit(Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER, page);

		return subDtoList;
	}

	/**
	 * @return 강의 시간표에서 필터링할 때 출력할 강의
	 */
	@Transactional
	public List<SubjectDto> readSubjectListSearchByCurrentSemester(CurrentSemesterSubjectSearchFormDto dto) {

		List<SubjectDto> subDtoList = subjectRepository.selectDtoBySemesterAndAndTypeAndDeptAndName(dto);

		return subDtoList;
	}

	/**
	 * 현재 인원을 1명 추가함
	 */
	@Transactional
	public void updatePlusNumOfStudent(Integer id) {
		int resultRowCount = subjectRepository.updateNumOfStudent(id, "추가");
		if (resultRowCount != 1) {
			throw new CustomRestfullException("현재 인원 수정이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 현재 인원을 1명 삭제함
	 */
	@Transactional
	public void updateMinusNumOfStudent(Integer id) {
		int resultRowCount = subjectRepository.updateNumOfStudent(id, "삭제");
		if (resultRowCount != 1) {
			throw new CustomRestfullException("현재 인원 수정이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public Subject readBySubjectId(Integer id) {
		Subject subjectEntity = subjectRepository.selectSubjectById(id);
		return subjectEntity;
	}

}
