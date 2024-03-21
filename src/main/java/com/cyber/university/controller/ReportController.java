package com.cyber.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @FileName : ReportController.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 20.
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 신고하기 controller
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    @GetMapping("/reportProcess")
    public String reportPage() {
        return "/report/reportProcess";
    }
}
