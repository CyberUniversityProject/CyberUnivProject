package com.cyber.university.repository.interfaces;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.CreateProfessorDto;
import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;
import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.dto.SyllaBusFormDto;
import com.cyber.university.dto.UserUpdateDto;
import com.cyber.university.dto.payment.PaymentInfoDto;
import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.MyEvaluationDto;
import com.cyber.university.dto.professor.MysubjectDetailDto;
import com.cyber.university.dto.professor.ProfessorAndSubjectFormDto;
import com.cyber.university.dto.professor.SubjectNameDto;
import com.cyber.university.dto.professor.SubjectPeriodForProfessorDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateStudentSubDetailDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.dto.response.ReadSyllabusDto;
import com.cyber.university.dto.response.SubjectForProfessorDto;
import com.cyber.university.dto.response.UserInfoForUpdateDto;
import com.cyber.university.repository.model.ApplySubject;
import com.cyber.university.repository.model.Professor;
import com.cyber.university.repository.model.Student;

/**
 * @FileName : ProfessorRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 교수 레파지토리
 */
@Mapper
public interface ProfessorRepository {

	// 24.03.11 교수 내 정보 조회
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id);

	// 24.03.11 교수 개인 정보 조회
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id);

	// 24.03.11 교수 정보 수정
	public int updateProfessorInfo(UpdateProfessorInfoDto updateDto);

	// 24.03.11 비밀번호 찾기
	public String selectPassword(Integer id);
	
	
	public List<ProfessorAndSubjectFormDto> findProfessorAndDept();

	/**
	 * 
	 * @Method Name : selectByProfessorId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : id로 교수 조회
	 */
	public List<Professor> selectByProfessorId(ProfessorListForm professorListForm);

	/**
	 * 
	 * @Method Name : selectByDepartmentId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 페이지별, 학과별 교수조회
	 */
	public List<Professor> selectByDepartmentId(ProfessorListForm professorListForm);

	/**
	 * 
	 * @Method Name : selectProfessorList
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 페이지별 교수 조회
	 */
	public List<Professor> selectProfessorList(ProfessorListForm professorListForm);

	/**
	 * 
	 * @Method Name : selectProfessorAmountByDeptId
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 페이징 처리 학과별 교수 조회
	 */
	public Integer selectProfessorAmountByDeptId(Integer deptId);

	/**
	 * @Method Name : selectProfessorAmount
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 전체 교수 조회
	 */
	public Integer selectProfessorAmount();

	/**
	 * 
	 * @Method Name : insertToProfessor
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : cu_professor에 insert
	 */
	public int insertToProfessor(CreateProfessorDto createProfessorDto);

	/**
	 * 
	 * @Method Name : selectIdByCreateProfessorDto
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : cu_professor에서 생성된 id값 받아오기
	 */
	public Integer selectIdByCreateProfessorDto(CreateProfessorDto createProfessorDto);

	/**
	 * 
	 * @Method Name : selectIdByNameAndEmail
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 아이디 찾기
	 */
	public Integer selectIdByNameAndEmail(FindIdFormDto findIdFormDto);

	/**
	 * 
	 * @Method Name : selectProfessorByIdAndNameAndEmail
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비밀번호 발급용 model
	 */
	public Integer selectProfessorByIdAndNameAndEmail(FindPasswordFormDto findPasswordFormDto);
	
	
	
	/**
	 * 
	  * @Method Name : selectProfessorInfoById
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교수 단건 조회
	 */
	public ProfessorInfoDto selectProfessorInfoById(Integer id);
	
	
	/**
	 * 
	  * @Method Name : selectByUserId
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교수 정보 가져오기
	 */
	public UserInfoForUpdateDto selectByUserId(Integer userId);
	
	
	/**
	 * 
	  * @Method Name : updateProfessor
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교수 정보 업데이트
	 */
	public int updateProfessor(UserUpdateDto userUpdateDto);
	
	
	/**
	  * @Method Name : insertSubject
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청
	  */
	public int insertApplySubject(ApplySubject applySubject);
	
	
	/**
	  * @Method Name : selectSubName
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 이름 찾기
	  */
	public ApplySubjectDto selectSubName(String subName);
	
	/**
	  * @Method Name : selectMysub
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 조회
	  */
	public List<SubjectPeriodForProfessorDto> selectSemester(Integer id);

	
	/**
	  * @Method Name : selectSubYearAndSemester
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 개강 년도와 학기 조회
	  */
	public List<SubjectForProfessorDto> selectSubjectBySemester(SubjectPeriodForProfessorDto subjectPeriodForProfessorDto);
		
	
	/**
	  * @Method Name : selectMySubDetailList
	  * @작성일 : 2024. 3. 15.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청한 학생 리스트 조회
	  */
	public List<MysubjectDetailDto> selectMySubDetailList(Integer subjectId);
	
	
	/**
	  * @Method Name : selectSubjectName
	  * @작성일 : 2024. 3. 15.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 과목 명 찾기
	  */
	public SubjectNameDto selectSubjectName(Integer id);
	
	/**
	  * @Method Name : updateStudentSubDetail
	  * @작성일 : 2024. 3. 16.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 성적 업데이트
	  */
	public int updateStudentSubDetail(Map<String, Object> params);
	
	/**
	  * @Method Name : updateStudentGrade
	  * @작성일 : 2024. 3. 16.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 학생 성적 입력
	  */
	public int updateStudentGrade(Map<String, Object> params);
	
	/**
	  * @Method Name : selectGradesInfo
	  * @작성일 : 2024. 3. 16.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 이수 학점 찾기
	  */
	public UpdateStudentSubDetailDto selectGradesInfo(Integer subjectId);
	
	
	/**
	  * @Method Name : selectMyEvaluationDtoByProfessorId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 과목별 강의평가 조회
	  */
	public List<MyEvaluationDto> selectEvaluationDtoByprofessorIdAndName(@Param("professorId") Integer professorId, 
																		@Param("name") String Name, @Param ("offset") int offset, @Param ("limit") int limit);
	
	/**
	  * @Method Name : selectEvaluationDto
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가 과목 조회
	  */
	public List<MyEvaluationDto> selectEvaluationDto(Integer professorId);
	
	/**
	  * @Method Name : selectMyEvaluationDtoByProfessorId
	  * @작성일 : 2024. 3. 18.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의평가 조회
	  */
	public List<MyEvaluationDto> selectMyEvaluationDtoByProfessorId(@Param("professorId") Integer professorId, 
													@Param ("offset") int offset, @Param ("limit") int limit);
	
	/**
	  * @Method Name : getMyEvaluationTotalCount
	  * @작성일 : 2024. 3. 20.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 평가 총 개수
	  */
	public int getMyEvaluationTotalCount();
	
	/**
	  * @Method Name : selectSyllabusBySubjectId
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의계획서 조회
	  */
	public ReadSyllabusDto selectSyllabusBySubjectId(Integer subjectId);
	
	/**
	  * @Method Name : updateSyllabus
	  * @작성일 : 2024. 3. 19.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 계획서 수정
	  */
	public int updateSyllabus(SyllaBusFormDto dto);
	

}