package com.cyber.university.handler;

import com.cyber.university.handler.exception.UnAuthorizedException;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * packageName    : com.cyber.university.handler
 * fileName       : AuthIntercepter
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 권한 인터셉터, 로그인되었는지 세션 검사
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Component
public class AuthIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(Define.PRINCIPAL) == null) {
            throw new UnAuthorizedException("접근 권한이 없습니다. 로그인이 필요합니다.", HttpStatus.UNAUTHORIZED, "/login");
            // return false;
        }
        return true;
    }

}
