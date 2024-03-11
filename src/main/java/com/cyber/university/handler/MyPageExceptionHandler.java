package com.cyber.university.handler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.cyber.university.handler
 * fileName       : MyPageExceptionHandler
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 마이페이지 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@ControllerAdvice
public class MyPageExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        // status = 404
        if(status != null && Integer.parseInt(status.toString()) == HttpStatus.NOT_FOUND.value()) {
        	
            return "/error/errorPage";
        }
        return "/";
    }
}
