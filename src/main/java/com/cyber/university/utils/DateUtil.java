package com.cyber.university.utils;

import java.sql.Date;

/**
 * packageName    : com.cyber.university.utils
 * fileName       : DateUtil
 * author         : 이준혁
 * date           : 3/10/24
 * description    : 날짜형 타입 관련 유틸
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/10/24        이준혁       최초 생성
 */
public class DateUtil {

    /**
     * 날짜형식 변환
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {

        String result = String.format("%tY년 %tm월 %td일", date, date, date);

        return result;
    }
}
