package com.cyber.university.handler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.cyber.university.handler.exception
 * fileName       : UnAuthorizedExceptionForMainPage
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 메인 페이지 권한 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Getter
public class UnAuthorizedExceptionForMainPage  extends  RuntimeException{

    private HttpStatus status;
    private String path;

    public UnAuthorizedExceptionForMainPage(HttpStatus status, String path) {

        this.status = status;
        this.path = path;

    }
}
