package com.cyber.university.repository.model;

import com.cyber.university.utils.NumberUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * @FileName : Tuition.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 :등록금
  */
@Data
@NoArgsConstructor
public class Tuition {
	
	private Integer studentId; 	// 학생 id
	private Integer tuiYear;	// 등록 연도
	private Integer semester; 	// 등록학기
	private Integer tuiAmount;	// 등록금
	private Integer schType;	// 장학금 유형
	private Integer schAmount;	// 장학금
	private Integer status;		// 납부여부
	
	
	
	/**
	 * @return 금액 형식으로 변환한 등록금
	 */
	public String tuiFormat() {
		return NumberUtil.numberFormat(tuiAmount);
	}
	
	/**
	 * @return 금액 형식으로 변환한 장학금
	 */
	public String schFormat() {
		return NumberUtil.numberFormat(schAmount);
	}
	
	/**
	 * @return 금액 형식으로 변환한 납부금
	 */
	public String paymentFormat() {
		Integer payAmount = tuiAmount - schAmount;
		return NumberUtil.numberFormat(payAmount);
	}

	public Tuition(Integer studentId, Integer tuiYear, Integer semester, Integer tuiAmount, Integer schType, Integer schAmount) {
		super();
		this.studentId = studentId;
		this.tuiYear = tuiYear;
		this.semester = semester;
		this.tuiAmount = tuiAmount;
		this.schType = schType;
		this.schAmount = schAmount;
	}
	

}
