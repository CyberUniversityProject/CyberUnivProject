package com.cyber.university.service;

import com.cyber.university.dto.ChangePasswordDto;

import com.cyber.university.dto.CreateProfessorDto;
import com.cyber.university.dto.CreateStaffDto;
import com.cyber.university.dto.CreateStudentDto;
import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;
import com.cyber.university.dto.LoginDto;
import com.cyber.university.dto.UserInfoDto;
import com.cyber.university.dto.UserUpdateDto;


import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.dto.response.UserInfoForUpdateDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.interfaces.StaffRepository;
import com.cyber.university.repository.interfaces.StuStatRepository;
import com.cyber.university.repository.interfaces.StudentRepository;
import com.cyber.university.repository.interfaces.UserRepository;
import com.cyber.university.repository.model.Staff;
import com.cyber.university.repository.model.Student;
import com.cyber.university.repository.model.User;
import com.cyber.university.utils.Define;
import com.cyber.university.utils.TempPassword;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.cyber.university.service

 * fileName       : UserService
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 유저 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
/**
 * @FileName : UserService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 12.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 :
 */
@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StuStatService stuStatService;
	@Autowired
	private StuStatRepository stuStatRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public PrincipalDto login(LoginDto loginDto) {
		PrincipalDto userEntity = userRepository.selectById(loginDto.getId());

		if (userEntity == null) {
			System.out.println("유저 null");
			throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
			throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		return userEntity;
	}

	/**
	 * 직원 생성 서비스로 먼저 cu_staff에 insert한 후 cu_staff에 생긴 id를 끌고와 cu_user에 생성함
	 * 
	 * @param createStaffDto
	 */
	@Transactional
	public void createStaffToStaffAndUser(CreateStaffDto createStaffDto) {

		int resultCountRow = staffRepository.insertToStaff(createStaffDto);
		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Integer staffId = staffRepository.selectIdByCreateStaffDto(createStaffDto);
		User user = new User();

		user.setId(staffId);
		user.setPassword(passwordEncoder.encode(staffId + ""));
		user.setUserRole("staff");

		resultCountRow = userRepository.insertToUser(user);
		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @Method Name : createProfessorToProfessorAndUser
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 교수 등록 처리
	 */
	@Transactional
	public void createProfessorToProfessorAndUser(CreateProfessorDto createProfessorDto) {

		int resultCountRow = professorRepository.insertToProfessor(createProfessorDto);

		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Integer professorId = professorRepository.selectIdByCreateProfessorDto(createProfessorDto);

		User user = new User();
		user.setId(professorId);
		user.setPassword(passwordEncoder.encode(professorId + ""));
		user.setUserRole("professor");

		resultCountRow = userRepository.insertToUser(user);
		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @Method Name : createStudentToStudentAndUser
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 학생 등록처리
	 */
	@Transactional
	public void createStudentToStudentAndUser(CreateStudentDto createStudentDto) {
		int resultCountRow = studentRepository.insertToStudent(createStudentDto);

		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Integer studentId = studentRepository.selectIdByCreateStudentDto(createStudentDto);

		// 학적 상태 생성 (재학)
		stuStatService.createFirstStatus(studentId);

		User user = new User();
		user.setId(studentId);
		user.setPassword(passwordEncoder.encode(studentId + ""));
		user.setUserRole("student");

		resultCountRow = userRepository.insertToUser(user);
		if (resultCountRow != 1) {
			throw new CustomRestfullException(Define.CREATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/**
	 * 
	 * @Method Name : readStaff
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 직원조회
	 */
	@Transactional
	public Staff readStaff(Integer id) {
		Staff staffEntity = staffRepository.selectStaffById(id);
		return staffEntity;
	}

	/**
	 * 
	 * @Method Name : updatePassword
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비번변경
	 */
	@Transactional
	public void updatePassword(ChangePasswordDto changePasswordDto) {
		int resultCountRaw = userRepository.updatePassword(changePasswordDto);
		if (resultCountRaw != 1) {
			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @Method Name : readIdByNameAndEmail
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 아이디 찾기
	 */
	@Transactional
	public Integer readIdByNameAndEmail(FindIdFormDto findIdFormDto) {

		Integer findId = null;
		if (findIdFormDto.getUserRole().equals("student")) {
			findId = studentRepository.selectIdByNameAndEmail(findIdFormDto);
		} else if (findIdFormDto.getUserRole().equals("professor")) {
			findId = professorRepository.selectIdByNameAndEmail(findIdFormDto);
		} else if (findIdFormDto.getUserRole().equals("staff")) {
			findId = staffRepository.selectIdByNameAndEmail(findIdFormDto);
		}

		if (findId == null) {
			throw new CustomRestfullException("아이디를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return findId;

	}

	/**
	 * 
	 * @Method Name : updateTempPassword
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비번찾기
	 */
	@Transactional
	public String updateTempPassword(FindPasswordFormDto findPasswordFormDto) {
		String password = null;
		Integer findId = 0;

		switch (findPasswordFormDto.getUserRole()) {
		case "student":
			findId = studentRepository.selectStudentByIdAndNameAndEmail(findPasswordFormDto);
			break;
		case "professor":
			findId = professorRepository.selectProfessorByIdAndNameAndEmail(findPasswordFormDto);
			break;
		case "staff":
			findId = staffRepository.selectStaffByIdAndNameAndEmail(findPasswordFormDto);
			break;
		default:
			throw new CustomRestfullException("잘못된 접근입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (findId == null) {
			throw new CustomRestfullException("회원정보를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		password = new TempPassword().returnTempPassword();
		System.out.println("임시 비밀번호: " + password);

		ChangePasswordDto changePasswordDto = new ChangePasswordDto();
		changePasswordDto.setAfterPassword(passwordEncoder.encode(password));
		changePasswordDto.setId(findPasswordFormDto.getId());
		userRepository.updatePassword(changePasswordDto);

		return password;
	}

	/**
	 * 
	 * @Method Name : readStudentInfo
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 학생정보조회
	 */
	@Transactional
	public StudentInfoDto readStudentInfo(Integer id) {
		StudentInfoDto studentEntity = studentRepository.selectStudentInfoById(id);
		return studentEntity;
	}

	/**
	 * 
	 * @Method Name : readProfessorInfo
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 교수 정보 조회
	 */
	@Transactional
	public ProfessorInfoDto readProfessorInfo(Integer id) {
		ProfessorInfoDto professorEntity = professorRepository.selectProfessorInfoById(id);
		return professorEntity;
	}
	
	
	
	/**
	 * 
	  * @Method Name : readStaffInfoForUpdate
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 직원 회원정보 업데이트
	 */
	public UserInfoForUpdateDto readStaffInfoForUpdate(Integer userId) {

		UserInfoForUpdateDto userInfoForUpdateDto = staffRepository.selectByUserId(userId);

		return userInfoForUpdateDto;
	}
	
	
	/**
	 * 
	  * @Method Name : readStudentInfoForUpdate
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생 회원정보 업데이트
	 */
	public UserInfoForUpdateDto readStudentInfoForUpdate(Integer userId) {

		UserInfoForUpdateDto userInfoForUpdateDto = studentRepository.selectByUserId(userId);

		return userInfoForUpdateDto;
	}
	
	
	/**
	 * 
	  * @Method Name : readProfessorInfoForUpdate
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교수 정보 업데이트
	 */
	public UserInfoForUpdateDto readProfessorInfoForUpdate(Integer userId) {

		UserInfoForUpdateDto userInfoForUpdateDto = professorRepository.selectByUserId(userId);

		return userInfoForUpdateDto;
	}
	
	
	/**
	 * 
	  * @Method Name : updateStaff
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 직원 회원정보 수정
	 */
	@Transactional
	public void updateStaff(UserUpdateDto updateDto) {

		int resultCountRaw = staffRepository.updateStaff(updateDto);
		if (resultCountRaw != 1) {
			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * 
	  * @Method Name : updateStudent
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생정보 수정
	 */
	@Transactional
	public void updateStudent(UserUpdateDto updateDto) {

		int resultCountRaw = studentRepository.updateStudent(updateDto);
		if (resultCountRaw != 1) {
			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	
	/**
	 * 
	  * @Method Name : updateProfessor
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 교수정보 업데이트
	 */
	@Transactional
	public void updateProfessor(UserUpdateDto updateDto) {

		int resultCountRaw = professorRepository.updateProfessor(updateDto);
		if (resultCountRaw != 1) {
			throw new CustomRestfullException(Define.UPDATE_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	  * @Method Name : findById
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : userId로 user 정보 조회
	  */
	public UserInfoDto findById(Integer userId) {
		
		UserInfoDto userInfoDto = userRepository.findById(userId);
		
		return userInfoDto;

	}
	
	/**
	 * 
	  * @Method Name : readStudent
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생조회
	 */
	@Transactional
	public Student readStudent(Integer studentId) {
		Student studentEntity = studentRepository.selectByStudentId(studentId);
		return studentEntity;
	}


}
