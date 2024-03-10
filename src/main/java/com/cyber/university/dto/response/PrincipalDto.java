package com.cyber.university.dto.response;

import lombok.Data;

/**
 * packageName    : com.cyber.university.dto.response
 * fileName       : PrincipalDto
 * author         : 이준혁
 * date           : 2024/03/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

@Data
public class PrincipalDto {
    private Integer id;
    private String password;
    private String userRole;
    private String name;
}
