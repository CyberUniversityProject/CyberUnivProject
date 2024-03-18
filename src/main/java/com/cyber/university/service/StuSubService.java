package com.cyber.university.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.SemesterGradeDto;
import com.cyber.university.dto.TotalScoreDto;
import com.cyber.university.dto.response.StuSubAppDto;
import com.cyber.university.dto.response.StuSubDayTimeDto;
import com.cyber.university.dto.response.StuSubSumGradesDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.PreStuSubRepository;
import com.cyber.university.repository.interfaces.StuSubDetailRepository;
import com.cyber.university.repository.interfaces.StuSubRepository;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.model.PreStuSub;
import com.cyber.university.repository.model.StuSub;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.utils.Define;
import com.cyber.university.utils.StuSubUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StuSubService {

	@Autowired
	private StuSubRepository stuSubRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private PreStuSubRepository preStuSubRepository;

	@Autowired
	private StuSubDetailRepository stuSubDetailRepository;

	// 학생의 수강신청 내역에 해당 강의가 존재하는지 확인
	public StuSub readStuSub(Integer studentId, Integer subjectId) {

		StuSub stuSubEntity = stuSubRepository.selectByStudentIdAndSubjectId(studentId, subjectId);

		return stuSubEntity;
	}

	// 학생의 해당 학기 수강신청 내역 조회
	public List<StuSubAppDto> readStuSubList(Integer studentId) {

		List<StuSubAppDto> stuSubList = stuSubRepository.selectListByStudentIdAndSemester(studentId,
				Define.CURRENT_YEAR, Define.CURRENT_SEMESTER);

		return stuSubList;
	}

	// 학생의 수강신청 내역 추가
	@Transactional
	public void createStuSub(Integer studentId, Integer subjectId) {

		// 신청 대상 과목 정보
		Subject targetSubject = subjectRepository.selectSubjectById(subjectId);

		// 신청 대상 과목의 정원이 다 찼다면 신청 불가
		if (targetSubject.getNumOfStudent() >= targetSubject.getCapacity()) {
			throw new CustomRestfullException("정원이 초과되었습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 현재 총 신청 학점
		StuSubSumGradesDto stuSubSumGradesDto = stuSubRepository.selectSumGrades(studentId, Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		// 최대 수강 가능 학점을 넘지 않는지 확인
		StuSubUtil.checkMaxGrades(targetSubject, stuSubSumGradesDto);

		// 해당 학생의 예비 수강 신청 내역 시간표
		List<StuSubDayTimeDto> dayTimeList = stuSubRepository.selectDayTime(studentId, Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		// 현재 학생의 시간표와 겹치지 않는지 확인
		StuSubUtil.checkNoTimeConflict(targetSubject, dayTimeList);

		// 수강신청 내역 추가
		int resultRowCount = stuSubRepository.insert(studentId, subjectId);

		// 수강 상세 내역에도 데이터 추가
		StuSub stuSub = stuSubRepository.selectByStudentIdAndSubjectId(studentId, subjectId);
		stuSubDetailRepository.insert(stuSub.getId(), studentId, subjectId);

		// 해당 강의 현재인원 +1
		subjectService.updatePlusNumOfStudent(subjectId);

		if (resultRowCount != 1) {
			throw new CustomRestfullException("수강신청이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// 학생의 수강신청 내역 삭제
	@Transactional
	public void deleteStuSub(Integer studentId, Integer subjectId) {

		// 수강신청 내역 삭제
		int resultRowCount = stuSubRepository.delete(studentId, subjectId);

		// 해당 강의 현재인원 -1
		subjectService.updateMinusNumOfStudent(subjectId);

		if (resultRowCount != 1) {
			throw new CustomRestfullException("예비 수강신청 취소가 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 예비 수강 신청 기간 -> 수강 신청 기간 변경 시 로직
	@Transactional
	public void createStuSubByPreStuSub() {

		// 1. 정원 >= 신청인원인 강의
		List<Integer> idList1 = subjectRepository.selectIdByLessNumOfStudent();

		for (Integer subjectId : idList1) {

			// 예비 수강 신청에서 해당 강의를 신청했던 내역 가져오기
			List<PreStuSub> preAppList = preStuSubRepository.selectBySubjectId(subjectId);

			// 예비 수강 신청했던 인원들이 자동으로 수강 신청되도록
			// 해당 내역 그대로 수강 신청 추가
			for (PreStuSub pss : preAppList) {
				// 수강 신청 내역이 없다면
				if (stuSubRepository.selectByStudentIdAndSubjectId(pss.getStudentId(), pss.getSubjectId()) == null) {
					stuSubRepository.insert(pss.getStudentId(), pss.getSubjectId());

					// 수강 상세 내역에도 데이터 추가
					StuSub stuSub = stuSubRepository.selectByStudentIdAndSubjectId(pss.getStudentId(),
							pss.getSubjectId());
					stuSubDetailRepository.insert(stuSub.getId(), pss.getStudentId(), pss.getSubjectId());
				}
			}
		}

		// 2. 정원 < 신청인원인 강의
		List<Integer> idList2 = subjectRepository.selectIdByMoreNumOfStudent();

		for (Integer subjectId : idList2) {

			// 강의의 현재 인원 초기화
			subjectRepository.updateNumOfStudent(subjectId, "초기화");

		}
	}

	// 수강 신청 내역과 예비 수강 신청 내역 조인 후 조회 -> 예비 수강 신청에만 존재
	@Transactional
	public List<StuSubAppDto> readPreStuSubByStuSub(Integer studentId) {
		List<StuSubAppDto> dtoList = stuSubRepository.selectJoinListByStudentId(studentId);
		return dtoList;
	}

	// 점수 입력 시 F면 취득학점 0, F가 아니면 강의의 이수학점
	@Transactional
	public void updateCompleteGrade(Integer studentId, Integer subjectId, Integer completeGrade) {
		stuSubRepository.updateCompleteGradeByStudentIdAndSubjectId(studentId, subjectId, completeGrade);
	}

	
	/**
	  * @Method Name : findThisSemesterGradeByStudentId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 금학기 성적 리스트
	  */
	public List<SemesterGradeDto> findThisSemesterGradeByStudentId(Integer studentId) {
		log.info("stuSubServce in111");
		List<SemesterGradeDto> thisSemesterGradeList = stuSubRepository.selectThisSemesterGradeByStudentId(studentId);
		log.info("stuSubServce select after thisSemesterGradeList : "+thisSemesterGradeList);
		return thisSemesterGradeList;
	}

	/**
	  * @Method Name : findTotalScoreByYearAndSemesterAndStudentId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 현재 년도와 달, studentId로 금학기 총점
	  */
	public TotalScoreDto findTotalScoreByYearAndSemesterAndStudentId(Integer currentYear, Integer currentMonth,
			Integer studentId) {
		
		Integer semester;

	    if (currentMonth >= 3 && currentMonth <= 8) {
	        semester = 1;
	    } else {
	        semester = 2;
	    }

	    log.info("service if에서 나온 semester 값은?"+semester);

	    log.info("studentId"+ studentId);
	    log.info("subYear"+ currentYear);
	    log.info("semester"+ semester);
	    
	    Map<String, Object> map = new HashMap<>() ;
	    map.put("studentId", studentId);
	    map.put("subYear", currentYear);
	    map.put("semester", semester);
	    
	    log.info("map" + map);
		
		TotalScoreDto totalScoreDto = stuSubRepository.selectTotalScoreByYearAndSemesterAndStudentId(map);
		
		log.info("dto:"+totalScoreDto);
		return totalScoreDto;
	}

	/**
	  * @Method Name : findTotalScoreAllSemesterByStudyId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : studyId로 조회한 모든 학기별 총점 조회
	  */
	public List<TotalScoreDto> findAllSemesterTotalScoreByStudyId(Integer studentId) {
		log.info("allsemesterservice로 들어오기는했음" + studentId);
		
		List<TotalScoreDto> allSemesterTotalScoreDto = stuSubRepository.selectAllSemesterTotalScoreByStudyId(studentId);

		log.info("service totalScoreDto:"+allSemesterTotalScoreDto);
		return allSemesterTotalScoreDto;
	}

}
