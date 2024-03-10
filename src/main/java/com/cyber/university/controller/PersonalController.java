package com.cyber.university.controller;

import com.cyber.university.dto.LoginDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : PersonalController
 * author         : 이준혁
 * date           : 2024/03/09
 * description    : 일반 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/9/24        이준혁       최초 생성
 */


@Controller
public class PersonalController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 메인 홈페이지
     * @author 이준혁
     */
    @GetMapping("/")
    public String home() {
        return "main";
    }


    /**
     * 로그인 페이지
     * @Author 이준혁
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }


    /**
     * 로그인 처리
     * @Author 이준혁
     * @param loginDto
     * @param bindingResult
     * @param response
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String signInProc(@Valid LoginDto loginDto, BindingResult bindingResult, HttpServletResponse response,
                             HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                sb.append(error.getDefaultMessage()).append("\\n");
            });
            throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        PrincipalDto principal = userService.login(loginDto);
        if ("on".equals(loginDto.getRememberId())) {
            Cookie cookie = new Cookie("id", loginDto.getId() + "");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("id")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                        break;
                    }
                }
            }
        }
        session.setAttribute(Define.PRINCIPAL, principal);

        return "redirect:/";
    }


    /**
     * 로그아웃
     *
     * @return 로그인 페이지
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }


    /**
     * @return 에러페이지
     */
    @GetMapping("/error")
    public String handleError() {
        return "/error/errorPage";
    }





}
