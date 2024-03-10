package com.cyber.university.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * packageName    : com.cyber.university.dto
 * fileName       : LoginDto
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 로그인 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

@Data
public class LoginDto {

    @Min(100000)
    @Max(2147483646)
    private Integer id;
    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String password;
    private String rememberId;
}
