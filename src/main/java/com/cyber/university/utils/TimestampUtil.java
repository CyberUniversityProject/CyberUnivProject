package com.cyber.university.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @FileName : TimestampUtil.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : timestamp 관련 유틸
 */
public class TimestampUtil {

	
	/**
	 * @author 이준혁
	 * Timestamp 타입을 날짜 + 시간으로 변환함
	 */
	public static String dateTimeToString(Timestamp timestamp) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(timestamp);
	}
	
	/**
	 * Timestamp 타입을 날짜로 변경함
	 */
	public static String dateToString(Timestamp timestamp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(timestamp);
	}

}
