package com.cyber.university.controller;

import com.cyber.university.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : NoticeRestFulController
 * author         : 이준혁
 * date           : 2024/03/17
 * description    : 메인 공지사항 조회 restful controller
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/17          이준혁       최초 생성
 */

@RestController
@RequestMapping("/api/notice")
public class NoticeRestFulController {

    @Autowired
    private NoticeService noticeService;


    // 공지사항 조회
    @GetMapping("/list")
    public ResponseEntity<?> readNoticeList() {
        return ResponseEntity.ok(noticeService.readMainNotice());
    }

    // 골지사항 상세조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> readNoticeDetail(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(noticeService.readNoticeById(id));
    }
}
