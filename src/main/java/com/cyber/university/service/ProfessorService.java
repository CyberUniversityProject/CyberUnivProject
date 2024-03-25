package com.cyber.university.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.dto.SyllaBusFormDto;
import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.FindDeptIdDto;
import com.cyber.university.dto.professor.MyEvaluationDto;
import com.cyber.university.dto.professor.MysubjectDetailDto;
import com.cyber.university.dto.professor.ProfessorAndSubjectFormDto;
import com.cyber.university.dto.professor.SubjectNameDto;
import com.cyber.university.dto.professor.SubjectPeriodForProfessorDto;
import com.cyber.university.dto.professor.UpdateApplySubListDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateStudentSubDetailDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.dto.response.ReadSyllabusDto;
import com.cyber.university.dto.response.SubjectForProfessorDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.interfaces.StudentRepository;
import com.cyber.university.repository.interfaces.SubjectRepository;
import com.cyber.university.repository.model.ApplySubject;
import com.cyber.university.repository.model.Department;
import com.cyber.university.repository.model.PageReq;
import com.cyber.university.repository.model.PageRes;
import com.cyber.university.repository.model.Professor;
import com.cyber.university.repository.model.Room;
import com.cyber.university.repository.model.Student;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * @FileName : ProfessorService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 교수 서비스
 */

@Service
@RequiredArgsConstructor
public class ProfessorService {

	@Autowired
	private final ProfessorRepository professorRepository;
	
	@Autowired
	private final StudentRepository studentRepository;
	
	@Autowired
	private final SubjectRepository subjectRepository;
	
	@Autowired
	private final HttpSession session;

	/**
	 * @Method Name : selectProfessorInfoWithCollegeAndDepartment
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 장명근
	 * @변경이력 :
	 * @Method 설명 : 교수 내 정보 조회 서비스
	 */
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id) {

		return professorRepository.selectProfessorInfoWithCollegeAndDepartment(id);
	}
	
	
	public List<ProfessorAndSubjectFormDto> findAllProfessor() {
		return professorRepository.findProfessorAndDept();
	}

	/**
	 * @Method Name : selectProfessorInfo
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 장명근
	 * @변경이력 :
	 * @Method 설명 : 교수 개인 정보 조회
	 */
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id) {

		return professorRepository.selectProfessorInfo(id);
	}
	

	/**
	 * @param professorListForm
	 * @return 교수 리스트 조회
	 */
	@Transactional
	public List<Professor> readProfessorList(ProfessorListForm professorListForm) {
		List<Professor> list = null;
		if (professorListForm.getProfessorId() != null) {
			list = professorRepository.selectByProfessorId(professorListForm);
		} else if (professorListForm.getDeptId() != null) {
			list = professorRepository.selectByDepartmentId(professorListForm);
		} else {
			list = professorRepository.selectProfessorList(professorListForm);
		}

		return list;
	}

	/**
	 * 
	 * @param studentListForm
	 * @return 교수 수
	 */
	@Transactional
	public Integer readProfessorAmount(ProfessorListForm professorListForm) {

		Integer amount = null;
		if (professorListForm.getDeptId() != null) {
			amount = professorRepository.selectProfessorAmountByDeptId(professorListForm.getDeptId());
		} else {
			amount = professorRepository.selectProfessorAmount();
		}

		return amount;
	}
	
	/**
	  * @Method Name : insertApplySubject
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청 
	  */
	@Transactional
	public void insertApplySubject(ApplySubjectDto dto, Integer proId) {
		
		if (selectSubName(dto.getName()) != null) {
			throw new CustomRestfullException("이미 존재하는 강의 입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ApplySubject subject = new ApplySubject();
		subject.setProfessorId(dto.getProfessorId());
		subject.setName(dto.getName());
		subject.setRoomId(dto.getRoomId());
		subject.setDeptId(dto.getDeptId());
		subject.setType(dto.getType());
		subject.setStartTime(dto.getStartTime());
		subject.setEndTime(dto.getEndTime());
		subject.setSubYear(dto.getSubYear());
		subject.setSemester(dto.getSemester());
		subject.setSubDay(dto.getSubDay());
		subject.setGrades(dto.getGrades());
		subject.setCapacity(dto.getCapacity());
		
		int resultRowCount = professorRepository.insertApplySubject(subject);
		if (resultRowCount != 1) {
			throw new CustomRestfullException("강의 신청 실패하셨습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	  * @Method Name : selectSubName
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 이름 조회
	  */
	public ApplySubjectDto selectSubName(String subName) {
		return professorRepository.selectSubName(subName);
	}
	
	/**
	  * @Method Name : selectMysub
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 조회
	  */
	public List<SubjectForProfessorDto> selectSubjectBySemester(SubjectPeriodForProfessorDto SubjectPeriodForProfessorDto) {
		
		List<SubjectForProfessorDto> list = professorRepository.selectSubjectBySemester(SubjectPeriodForProfessorDto);

	    return list;
	}
	

	
	/**
	  * @Method Name : selectMySubDetailList
	  * @작성일 : 2024. 3. 15.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청한 학생 리스트 조회
	  */
	public List<MysubjectDetailDto> selectMySubDetailList(Integer subjectId) {
		
		return professorRepository.selectMySubDetailList(subjectId);
	}
	
	/**
	  * @Method Name : selectSubjectName
	  * @작성일 : 2024. 3. 15.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 과목 명 찾기
	  */
	public SubjectNameDto selectSubjectName(Integer id) {
		
		return professorRepository.selectSubjectName(id);
	}
	
	/**
	  * @Method Name : selectByStudentId
	  * @작성일 : 2024. 3. 16.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 아이디와 이름 조회
	  */
	public Student selectByStudentId(Integer studentId) {
		return studentRepository.selectByStudentId(studentId);
	}
	
	/**
	  * @Method Name : updateStudentSubDetail
	  * @작성일 : 2024. 3. 16.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 성적 업데이트
	  */
	@Transactional
	public int updateStudentSubDetail(Integer studentId, Integer subjectId, UpdateStudentSubDetailDto dto) {
		UpdateStudentSubDetailDto grades = professorRepository.selectGradesInfo(subjectId);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("studentId", dto.getStudentId());
		map.put("subjectId", dto.getSubjectId());
		map.put("absent", dto.getAbsent());
		map.put("lateness", dto.getLateness());
		if (dto.getLateness() >= 5) {
			map.put("grade", "F");
		}
		map.put("homework", dto.getHomework());
		map.put("midExam", dto.getMidExam());
		map.put("finalExam", dto.getFinalExam());
		map.put("grade", dto.getGrade());
		if(dto.getGrade().equals("F")) {
			map.put("completeGrade", 0);
		} else {
			map.put("completeGrade", grades.getGrades());
		}
		map.put("convertedMark", dto.getConvertedMark());
		
		
		
		int result = professorRepository.updateStudentSubDetail(map);
		int result2 = professorRepository.updateStudentGrade(map);
		
		
		
		return result + result2;
	}
	
	
	/**
	  * @Method Name : readSubjectName
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 아이디로 과목 이름 찾기
	  */
	public List<MyEvaluationDto> readSubjectName(Integer professorId) {
		List<MyEvaluationDto> subjectName = professorRepository.selectEvaluationDto(professorId);
		
		return subjectName;
	}
	
	/**
	  * @Method Name : readEvaluationByProfessorIdAndName
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 과목별 강의 평가 조회
	  */
	public PageRes<MyEvaluationDto> readEvaluationByProfessorIdAndName(PageReq pageReq, Integer professorId, String name) {
		int page = pageReq.getPage();
		int size = pageReq.getSize();
		int offset = (page - 1) * size;
		
		long totalElements = professorRepository.getMyEvaluationTotalCount();
		
		List<MyEvaluationDto> evaluation = professorRepository.selectEvaluationDtoByprofessorIdAndName(professorId, name, offset, size);
		
		PageRes<MyEvaluationDto> pageRes = new PageRes<>(evaluation, page, totalElements, size);
		
		return pageRes;
	}
	
	/**
	  * @Method Name : selectMyEvaluationDtoByProfessorId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의평가 조회
	  */
	public PageRes<MyEvaluationDto> readEvaluationByProfessorId(PageReq pageReq, Integer professorId) {
		int page = pageReq.getPage();
		int size = pageReq.getSize();
		int offset = (page - 1) * size;
		
		long totalElements = professorRepository.getMyEvaluationTotalCount();
				
		List<MyEvaluationDto> evaluation = professorRepository.selectMyEvaluationDtoByProfessorId(professorId, offset, size);
		
		PageRes<MyEvaluationDto> pageRes = new PageRes<>(evaluation, page, totalElements, size);
		
		return pageRes;
	}
	
	/**
	  * @Method Name : getTotalCount
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가 개수 조회
	  */
	public int getTotalCount() {
		return professorRepository.getMyEvaluationTotalCount();
	}
	
	
	/**
	  * @Method Name : selectSubYearAndSemester
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 개강 년도와 학기 조회
	  */
	public List<SubjectPeriodForProfessorDto> selectSemester(Integer id) {
		
		return professorRepository.selectSemester(id);
	}
	
	/**
	  * @Method Name : selectSyllabusBySubjectId
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의계획서 조회
	  */
	public ReadSyllabusDto selectSyllabusBySubjectId(Integer subjectId) {
		
		return professorRepository.selectSyllabusBySubjectId(subjectId);
	}
	
	/**
	  * @Method Name : updateSyllabus
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 계획서 수정
	  */
	@Transactional
	public void updateSyllabus(SyllaBusFormDto dto) {
		
		int resultRowCount = professorRepository.updateSyllabus(dto);
		if (resultRowCount != 1) {
			throw new CustomRestfullException("제출 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	// 강의계획서 불러오기
	@Transactional
	public ReadSyllabusDto readSyllabus(Integer subjectId) {

		ReadSyllabusDto readSyllabusDto = subjectRepository.selectSyllabusBySubjectId(subjectId);
		System.out.println(readSyllabusDto.toString());
		return readSyllabusDto;
	}
	
	/**
	  * @Method Name : selectRoom
	  * @작성일 : 2024. 3. 23.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학과 강의실 조회
	  */
	public List<Room> selectRoom(Integer collegeId) {
		return professorRepository.selectRoom(collegeId);
	}
	
	/**
	  * @Method Name : selectDepartment
	  * @작성일 : 2024. 3. 23.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학과 이름 조회
	  */
	public List<Department> selectDepartment(Integer collegeId) {
		return professorRepository.selectDepartment(collegeId);
	}
	
	/**
	  * @Method Name : selectDeptId
	  * @작성일 : 2024. 3. 23.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 단과대 아이디 조회
	  */
	public FindDeptIdDto selectDeptId() {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer professorId = principal.getId();
		
		return professorRepository.selectDeptId(professorId);
	}
	
	/**
	  * @Method Name : selectUpdateSubInfo
	  * @작성일 : 2024. 3. 25.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 신청 강의 수정 정보 조회
	  */
	public ApplySubjectDto selectUpdateSubInfo(Integer id) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer professorId = principal.getId();
		
		return professorRepository.selectUpdateSubInfo(professorId, id);
	}
	
	/**
	  * @Method Name : selectMyApplySubList
	  * @작성일 : 2024. 3. 25.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 내 신청 강의 목록 조회
	  */
	public List<UpdateApplySubListDto> selectMyApplySubList() {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer professorId = principal.getId();
		
		return professorRepository.selectMyApplySubList(professorId);
	}
	
	
}
