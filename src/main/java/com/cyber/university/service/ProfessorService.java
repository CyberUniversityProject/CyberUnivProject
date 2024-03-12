package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.model.User;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.ProfessorListForm;

import com.cyber.university.repository.model.Professor;

/**
 * @FileName : ProfessorService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 교수 서비스
 */

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession session;
	
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
	 * @Method Name : updateProfessorInfo
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 장명근
	 * @변경이력 :
	 * @Method 설명 : 교수 정보 수정 서비스
	 */
	@Transactional
	public UpdateProfessorInfoDto updateProfessorInfo(UpdateProfessorInfoDto dto) {
		Object principalObject = session.getAttribute(Define.PRINCIPAL);

	    // PrincipalDto가 아닌 경우에는 로그인이 되어 있지 않은 것으로 처리
	    if (!(principalObject instanceof PrincipalDto)) {
	        throw new CustomRestfullException("로그인 해주세요", HttpStatus.BAD_REQUEST);
	    }

	    // PrincipalDto인 경우에는 User 객체로 형변환하여 아이디를 가져옴
	    PrincipalDto principal = (PrincipalDto) principalObject;
	    int userId = principal.getId();
		
		// 입력한 비밀번호 확인
	    String enteredPassword = dto.getPassword();
	    String savedPassword = professorRepository.selectPassword(userId);
	    System.out.println("교수 아이디 : " + userId);
	    System.out.println(savedPassword);
	    // 입력한 비밀번호를 암호화하여 비교
	    if (!passwordEncoder.matches(enteredPassword, savedPassword)) {
	    	System.out.println(enteredPassword);
	    	System.out.println(savedPassword);
	        throw new CustomRestfullException("비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST);
	    }
	    
	    // 비밀번호가 일치하는 경우에만 주소, 번호, 이메일 업데이트
	    professorRepository.updateProfessorInfo(dto);
	    
	    return dto;
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

}
