package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.repository.interfaces.ReportRepository;
import com.cyber.university.repository.model.Report;

/**
 * @FileName : ReportRepository
 * @Project : CyberUniversity
 * @Date : 2024. 3. 20. 
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 신고하기 repository
 */
@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Transactional
	public int insertReport(Report report) {
		reportRepository.insertReport(report);
		return report.getId();
	}

	public boolean incrementReportCount(Integer userId) {
		int reportCount = reportRepository.getUserReportCount(userId);
		return reportCount >= 3;
	}
}
