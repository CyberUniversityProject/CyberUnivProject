package com.cyber.university.service;

import com.cyber.university.dto.ChangePasswordDto;
import com.cyber.university.dto.CreateStaffDto;
import com.cyber.university.dto.LoginDto;
import com.cyber.university.dto.UserInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.StaffRepository;
import com.cyber.university.repository.interfaces.UserRepository;
import com.cyber.university.repository.model.User;
import com.cyber.university.utils.Define;

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
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StaffRepository staffRepository;

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
	 * staff 생성 서비스로 먼저 cu_staff에 insert한 후 cu_staff에 생긴 id를 끌고와 cu_user에 생성함
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
	  * @Method Name : findById
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : userId로 user 정보 조회
	  */
	// TODO: SELECT BY ID 준혁한테 물어보기
	public UserInfoDto findById(Integer userId) {
		
		UserInfoDto userInfoDto = userRepository.findById(userId);
		
		return userInfoDto;
	}


	/**
	  * @Method Name : updatePassword
	  * @작성일 : 2024. 3. 12.
	  * @작성자 : 박경진
	  * @변경이력 : 
	  * @Method 설명 : 패스워드 encoding 된 상태로 update
	  */
	public int updatePassword(ChangePasswordDto changePasswordDto) {
		int result = userRepository.updatePassword(changePasswordDto);
		
		return result;
	}

}
