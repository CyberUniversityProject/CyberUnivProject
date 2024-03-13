package com.cyber.university.handler;

import com.cyber.university.handler.exception.CustomPathException;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.handler.exception.UnAuthorizedException;
import com.cyber.university.handler.exception.UnAuthorizedExceptionForMainPage;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * packageName    : com.cyber.university.handler
 * fileName       : MyRestFullExceptionHandler
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : RestFull 에러 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@RestControllerAdvice
public class MyRestFullExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void exception(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println(e.getMessage());
    }

    // 사용자 정의 예외 클래스 활용
    @ExceptionHandler(CustomRestfullException.class)
    public String basicException(CustomRestfullException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+ e.getMessage() +"');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthorizedException(UnAuthorizedException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+ e.getMessage() +"');");
        sb.append("location.href='" + e.getPath() + "';");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(UnAuthorizedExceptionForMainPage.class)
    public String unAuthorizedException(UnAuthorizedExceptionForMainPage e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("location.href='" + e.getPath() + "';");
        sb.append("</script>");
        return sb.toString();
    }

    /**
     * @author 이준혁
     * 경로를 지정해서 던지는 예외 클래스 활용하기
     */
    @ExceptionHandler(CustomPathException.class)
    public String customPathException(CustomPathException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+ e.getMessage() +"');");
        sb.append("location.href='" + e.getPath() + "';");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFoundException(NoHandlerFoundException e) {
        System.out.println(e.getMessage());
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("location.href='/error/';");
        sb.append("</script>");
        return sb.toString();
    }
}
