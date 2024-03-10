package com.cyber.university.controller;

import com.cyber.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : UserController
 * author         : 이준혁
 * date           : 2024/03/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return staff 입력 페이지
     */
    @GetMapping("/staff")
    public String createStaff() {

        return "/user/createStaff";
    }

}
