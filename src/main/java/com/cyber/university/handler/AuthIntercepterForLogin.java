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
 * fileName       : AuthIntercepterForLogin
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 로그인 인터셉터, 로그인창에서 로그인되어있으면 돌려보내는 인터셉터임
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Component
public class AuthIntercepterForLogin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute(Define.PRINCIPAL) != null) {
            throw new UnAuthorizedException("이미 로그인되어있습니다.", HttpStatus.UNAUTHORIZED, "/");
        }
        return true;
    }
}
