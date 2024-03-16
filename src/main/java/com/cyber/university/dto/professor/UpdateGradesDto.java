package com.cyber.university.dto.professor;

import lombok.Data;

@Data
public class UpdateGradesDto {
	private Integer studentId;
	private Integer subjectId;
	private String grade;
	private Integer grades;
}
