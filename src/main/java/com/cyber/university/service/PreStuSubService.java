package com.cyber.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.response.StuSubAppDto;
import com.cyber.university.dto.response.StuSubDayTimeDto;
import com.cyber.university.dto.response.StuSubSumGradesDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.PreStuSubRepository;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.model.PreStuSub;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.utils.Define;
import com.cyber.university.utils.StuSubUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreStuSubService {
	
	
	@Autowired
	private final PreStuSubRepository preStuSubRepository;

	@Autowired
	private final SubjectRepository subjectRepository;

	@Autowired
	private final SubjectService subjectService;

	// 학생의 예비 수강신청 내역에 해당 강의가 존재하는지 확인
	public PreStuSub readPreStuSub(Integer studentId, Integer subjectId) {

		PreStuSub preStuSubEntity = preStuSubRepository.selectByStudentIdAndSubjectId(studentId, subjectId);

		return preStuSubEntity;
	}

	// 학생의 전체 예비 수강신청 내역 조회
	public List<StuSubAppDto> readPreStuSubList(Integer studentId) {

		List<StuSubAppDto> preStuSubList = preStuSubRepository.selectListByStudentIdAndSemester(studentId,
				Define.CURRENT_YEAR, Define.CURRENT_SEMESTER);

		return preStuSubList;
	}

	// 학생의 예비 수강신청 내역 추가
	@Transactional
	public void createPreStuSub(Integer studentId, Integer subjectId) {

		// 신청 대상 과목 정보
		Subject targetSubject = subjectRepository.selectSubjectById(subjectId);

		// 현재 총 신청 학점
		StuSubSumGradesDto stuSubSumGradesDto = preStuSubRepository.selectSumGrades(studentId, Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		// 최대 수강 가능 학점을 넘지 않는지 확인
		StuSubUtil.checkMaxGrades(targetSubject, stuSubSumGradesDto);

		// 해당 학생의 예비 수강 신청 내역 시간표
		List<StuSubDayTimeDto> dayTimeList = preStuSubRepository.selectDayTime(studentId);

		// 현재 학생의 시간표와 겹치지 않는지 확인
		StuSubUtil.checkNoTimeConflict(targetSubject, dayTimeList);

		// 수강신청 내역 추가
		int resultRowCount = preStuSubRepository.insert(studentId, subjectId);

		// 해당 강의 현재인원 +1
		subjectService.updatePlusNumOfStudent(subjectId);

		if (resultRowCount != 1) {
			throw new CustomRestfullException("예비 수강신청이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 학생의 예비 수강신청 내역 삭제
	@Transactional
	public void deletePreStuSub(Integer studentId, Integer subjectId) {

		// 수강신청 내역 삭제
		int resultRowCount = preStuSubRepository.delete(studentId, subjectId);

		// 해당 강의 현재인원 -1
		subjectService.updateMinusNumOfStudent(subjectId);

		if (resultRowCount != 1) {
			throw new CustomRestfullException("예비 수강신청 취소가 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
