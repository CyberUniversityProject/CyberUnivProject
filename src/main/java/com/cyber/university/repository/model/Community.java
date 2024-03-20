package com.cyber.university.repository.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.cyber.university.utils.DateUtil;
import com.cyber.university.utils.TimestampUtil;

import lombok.Data;

@Data
public class Community {
private Integer id;
    
    private String title;
    
    private String content;
    
    private String userName;
    
    private Timestamp createDate;
    
    private Timestamp updateDate;
    
    private List<Comment> comments; // 게시글에 대한 댓글 리스트
    
    
    
    public String appDateFormat() {
		return TimestampUtil.dateTimeToString(createDate);
	}
    
    
    public String appUpdateFormat() {
		return TimestampUtil.dateTimeToString(updateDate);
	}

}
