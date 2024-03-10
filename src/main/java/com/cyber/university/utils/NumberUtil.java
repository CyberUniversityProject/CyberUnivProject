package com.cyber.university.utils;

import java.text.DecimalFormat;

/**
 * packageName    : com.cyber.university.utils
 * fileName       : NumberUtil
 * author         : 이준
 * date           : 3/10/24
 * description    : 숫자 관련 유틸
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/10/24        이준혁       최초 생성
 */
public class NumberUtil {


    /**
     * 숫자를 1,000,000와 같은 형태로 변환함
     * @param number
     * @return
     */

    public static String numberFormat(Long number) {

        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }

    public static String numberFormat(Integer number) {

        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }
}
