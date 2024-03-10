package com.cyber.university.handler;

import com.cyber.university.handler.exception.UnAuthorizedException;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.cyber.university.dto.response.PrincipalDto;

/**
 * packageName    : com.cyber.university.handler
 * fileName       : UserRoleAuthIntercepterForStaff
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 교직원 권한 인터셉터, 세션값 검사해서 userRole이 staff인지 확인하는 인터셉터
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Component
public class UserRoleAuthIntercepterForStaff implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
        if (!"staff".equals(principal.getUserRole())) {
            throw new UnAuthorizedException("접근 권한이 없습니다. 관리자 전용 페이지", HttpStatus.UNAUTHORIZED, "/");
        }
        return true;
    }

}