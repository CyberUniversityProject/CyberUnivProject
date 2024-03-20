package com.cyber.university.repository.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.cyber.university.utils.DateUtil;
import com.cyber.university.utils.TimestampUtil;

import lombok.Data;

@Data
public class Comment {

	private Integer id;

	private Integer communityId;

	private String content;

	private Timestamp createDate;
	private Timestamp updateDate;

	private String userId;

	private String role;

	public String appDateFormat() {
		return TimestampUtil.dateTimeToString(createDate);
	}

	public String appUpdateFormat() {
		return TimestampUtil.dateTimeToString(updateDate);
	}

}
