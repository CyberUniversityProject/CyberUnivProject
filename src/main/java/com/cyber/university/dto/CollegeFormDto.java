package com.cyber.university.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollegeFormDto {
	
	@NotBlank
	private String name;

}
