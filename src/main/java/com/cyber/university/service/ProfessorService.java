package com.cyber.university.service;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.model.User;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.model.Professor;

/**
  * @FileName : ProfessorService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교수 서비스
  */
>>>>>>> dev

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
<<<<<<< HEAD
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
	public UpdateProfessorInfoDto updateProfessorInfo(Integer id, User user) {
		
		String enteredPassword = user.getPassword();
		
		String savedPassword = professorRepository.selectPassword(id);
		
		if (savedPassword == null || !savedPassword.equals(enteredPassword)) {
			throw new CustomRestfullException("비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		UpdateProfessorInfoDto updateDto = new UpdateProfessorInfoDto();
	    updateDto.setAddress(updateDto.getAddress());
	    updateDto.setTel(updateDto.getTel());
	    updateDto.setEmail(updateDto.getEmail());
	    updateDto.setId(updateDto.getId());
	    
	    professorRepository.updateProfessorInfo(updateDto);
	    
	    return updateDto;
	
	}
	
	
	
	
	
=======

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

>>>>>>> dev
}
