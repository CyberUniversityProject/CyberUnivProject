package com.cyber.university.dto;
/**
 * @FileName : ReportDto.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 20. 
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 신고하기
 */

import lombok.Data;


@Data
public class ReportDto {

	private Integer id; //신고발생 id
	private Integer communityId; // 신고당한 글 id 
	private Integer reportedCount; // 신고당한 횟수
	private Integer userId; // 신고한 사람
	private Integer reportLimit; // 신고한 횟수 3번 이상 글삭제
	private Integer reportReason; // 신고사유 alert 창으로 신고자가 직접작성
	private String reportStatus; // from cu_process_status table
}
