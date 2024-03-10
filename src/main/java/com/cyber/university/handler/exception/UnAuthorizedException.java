package com.cyber.university.handler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.cyber.university.handler.exception
 * fileName       : UnAuthorizedException
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 권한 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Getter
public class UnAuthorizedException extends RuntimeException {

    private HttpStatus status;
    private String path;

    // 매개변수로 이동할 주소를 받으면 각 호출마다 다른 경로로 보낼 수 있게 설계도 가능
    public UnAuthorizedException(String message, HttpStatus status, String path) {

        super(message);
        this.status = status;
        this.path = path;

    }
}
