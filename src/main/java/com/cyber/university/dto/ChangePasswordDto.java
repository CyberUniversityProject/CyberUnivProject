package com.cyber.university.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * packageName    : com.cyber.university.repository.model
 * fileName       : ChangePasswordDto
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 패스워드 변경용 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

@Data
public class ChangePasswordDto {

    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String beforePassword;
    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String afterPassword;
    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String passwordCheck;
    private Integer id;
}
