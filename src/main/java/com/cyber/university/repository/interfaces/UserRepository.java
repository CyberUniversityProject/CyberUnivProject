package com.cyber.university.repository.interfaces;

import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.repository.model.ChangePasswordDto;
import com.cyber.university.repository.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName    : com.cyber.university.repository.interfaces
 * fileName       : UserRepository
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 유저 레파지토리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Mapper
public interface UserRepository {

    // 로그인용
    public PrincipalDto selectById(Integer userId);

    // 패스워드 변경
    public int updatePassword(ChangePasswordDto changePasswordDto);

    // id 이용해서 user_tb에 insert
    public int insertToUser(User user);

}
