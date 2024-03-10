package com.cyber.university.handler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.cyber.university.handler.exception
 * fileName       : CustomPathException
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 경로 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Getter
public class CustomPathException extends RuntimeException {

    private HttpStatus status;
    private String path;

    public CustomPathException(String message, HttpStatus status, String path) {
        super(message);
        this.status = status;
        this.path = path;
    }
}
