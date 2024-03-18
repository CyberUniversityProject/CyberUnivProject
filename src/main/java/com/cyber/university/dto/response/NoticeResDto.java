package com.cyber.university.dto.response;

import com.cyber.university.utils.TimestampUtil;
import lombok.Data;

import java.sql.Timestamp;

/**
 * packageName    : com.cyber.university.dto.response
 * fileName       : NoticeResDto
 * author         : 이준혁
 * date           : 2024/03/17
 * description    : 메인 공지사항 조회 응답 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/17          이준혁       최초 생성
 */

@Data
public class NoticeResDto {

    private Integer id;
    private String category;
    private String title;
    private Timestamp createdTime;

    public String getCreatedTimeAsString() {
        return TimestampUtil.dateTimeToString(createdTime);
    }

}
