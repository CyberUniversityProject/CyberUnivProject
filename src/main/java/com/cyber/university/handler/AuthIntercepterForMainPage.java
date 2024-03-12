package com.cyber.university.handler;

import com.cyber.university.handler.exception.UnAuthorizedExceptionForMainPage;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * packageName    : com.cyber.university.handler
 * fileName       : AuthIntercepterForMainPage
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 메인 페이지 권한 인터셉터 로그인 되어있는지 세션 검사하는 인터셉터
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Component
public class AuthIntercepterForMainPage implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(Define.PRINCIPAL) == null) {
            throw new UnAuthorizedExceptionForMainPage(HttpStatus.UNAUTHORIZED, "/login");
            // return false;
        }
        return true;
    }
}
