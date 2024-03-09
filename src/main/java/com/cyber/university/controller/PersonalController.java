package com.cyber.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    /**
     * 메인 홈페이지
     * @author 이준혁
     */
    @GetMapping("")
    public String home() {
        return "main";
    }

}
