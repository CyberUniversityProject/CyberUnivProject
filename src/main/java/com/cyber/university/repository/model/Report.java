package com.cyber.university.repository.model;

import lombok.Data;

/**
 * @FileName : Report.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 20. 
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 신고하기
 */

@Data
public class Report {
	
	private Integer id;
	private Integer communityId;
	private Integer reportedCount;
	private Integer userId;
	private Integer reportLimit;
	private Integer reportReason;
	private String reportStatus; // from cu_process_status table

}
