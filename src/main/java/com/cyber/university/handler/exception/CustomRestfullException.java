package com.cyber.university.handler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.cyber.university.handler.exception
 * fileName       : CustomRestfullException
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : Restfull 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Getter
public class CustomRestfullException extends RuntimeException {

    private HttpStatus status;

    public CustomRestfullException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
