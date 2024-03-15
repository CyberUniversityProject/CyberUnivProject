package com.cyber.university.dto.professor;

import lombok.Data;

@Data
public class MysubjectDetailDto {
	
	private Integer subjectId;
	private Integer studentId;
	private Integer absent;
	private Integer lateness;
	private Integer homework;
	private Integer midExam;
	private Integer finalExam;
	private Integer convertedMark;
	private String studentName;
	private String departmentName;
	private String subjectName;
}
