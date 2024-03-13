package com.cyber.university.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentFormDto {
	private Integer id;
	@NotNull
	@NotBlank
	@NotEmpty
	private String name;
	@NotNull
	private Integer collegeId;
	private String collegeName;
}
