package com.cyber.university.service;

import java.util.List;

import com.cyber.university.dto.response.SubjectListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.cyber.university.dto.CollTuitFormDto;
import com.cyber.university.dto.SubjectFormDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.CollTuitRepository;
import com.cyber.university.repository.interfaces.CollegeRepository;
import com.cyber.university.repository.interfaces.DepartmentRepository;
import com.cyber.university.repository.interfaces.RoomRepository;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.interfaces.SyllaBusRepository;
import com.cyber.university.repository.model.College;
import com.cyber.university.repository.model.Subject;
import com.cyber.university.utils.SubjectUtil;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class StaffService {
	
	
	@Autowired
	private final CollegeRepository collegeRepository;
	@Autowired
	private final DepartmentRepository departmentRepository;
	
	@Autowired
	private final SubjectRepository subjectRepository;
	
	@Autowired
	private final SyllaBusRepository syllaBusRepository;
	
	@Autowired
	private final CollTuitRepository collTuitRepository;
	@Autowired
	private final RoomRepository roomRepository;
	
	
	
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
	
	public List<SubjectListDto> findAllPost(int page, int size) {
        // 페이징 처리를 위해 offset 계산
        int offset = (page - 1) * size;
        // 페이징된 강의실 목록 조회
        return subjectRepository.findAllwithPaging(offset, size);
    }
	
	 public int getTotalPages(int size) {
	        // 전체 데이터 개수 가져오기
	        int totalRecords = subjectRepository.getAllCount();
	        // 전체 페이지 수 계산
	        int totalPages = (int) Math.ceil((double) totalRecords / size);
	        return totalPages;
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
	
	
	
	/**
	 * 단과대별 등록금 입력 서비스
	 */
	@Transactional
	public void createCollTuit(@Validated CollTuitFormDto collTuitFormDto) {
		// 등록금 중복 입력 검사
		List<CollTuitFormDto> collTuitList = collTuitRepository.selectByCollTuitDto();
		for (int i = 0; i < collTuitList.size(); i++) {
			if (collTuitList.get(i).getCollegeId() == (collTuitFormDto.getCollegeId())) {
				throw new CustomRestfullException("이미 등록금이 입력된 학과입니다", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		int resultRowCount = collTuitRepository.insert(collTuitFormDto);
		if (resultRowCount != 1) {
			System.out.println("단과대 등록금 입력 서비스 오류");
		}
	}
	
	/**
	 * 단과대 등록금 조회 서비스
	 */
	public List<CollTuitFormDto> readCollTuit() {
		List<CollTuitFormDto> collTuitList = collTuitRepository.selectByCollTuitDto();
		return collTuitList;
	}

	/**
	 * 단과대 등록금 삭제 서비스
	 */
	public int deleteCollTuit(Integer collegeId) {
		int resultRowCount = collTuitRepository.deleteById(collegeId);
		return resultRowCount;
	}

	/**
	 * 단과대 등록금 수정 서비스
	 */
	public int updateCollTuit(CollTuitFormDto collTuitFormDto) {
		int resultRowCount = collTuitRepository.updateByCollTuitDto(collTuitFormDto);
		if (resultRowCount != 1) {
			System.out.println("단과대 등록금 수정 서비스 오류");
		}
		return resultRowCount;
	}

}
