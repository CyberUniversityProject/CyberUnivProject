package com.cyber.university.repository.model;

import lombok.Data;

/**
 * packageName    : com.cyber.university.repository.model
 * fileName       : User
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 유저 모델
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Data
public class User {

    private Integer id;
    private String password;
    private String userRole;
}
