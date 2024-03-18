package com.cyber.university.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PageReq {
	
	// 현재 페이지 번호 : page == number
	private int page;
	
	// 페이지당 출력할 데이터 개수
	private int size;

	public PageReq(int page, int size) {
		this.page = page;
		this.size = size;
	}
}
