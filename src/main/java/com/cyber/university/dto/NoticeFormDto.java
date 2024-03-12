package com.cyber.university.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.cyber.university.utils.TimestampUtil;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NoticeFormDto {
	
	private Integer id;
	private Integer noticeId;
	private String category;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private Integer views;
	private Timestamp createdTime;
	private MultipartFile file;	
	private String originFilename;
	private String uuidFilename;
	
	// 공지 시간 처리 (날짜 시간)
	public String timeFormat() {
		TimestampUtil timestampUtil = new TimestampUtil();
		return timestampUtil.dateTimeToString(createdTime);
	}
	
	// 공지 시간 처리 (날짜)
	public String dateFormat() {
		TimestampUtil timestampUtil = new TimestampUtil();
		return timestampUtil.dateToString(createdTime);
	}

}
