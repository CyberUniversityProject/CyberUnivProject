package com.cyber.university.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.repository.model.Report;

/**
 * @FileName : ReportRepository
 * @Project : CyberUniversity
 * @Date : 2024. 3. 20. 
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 신고하기 repository
 */
@Mapper
public interface ReportRepository {

	// 신고 처리중0 처리완료1
	// 하루에 3번까지만 신고가능
	// 신고 3번이상당하면 관리자측에서 글삭제
	// 허위 신고 시 관리자 측에서 신고 건 삭제
	
	// 신고 insert 
	public int insertReport(Report report); 
	// 신고횟수
	public int incrementReportCount(@Param("userId") Integer userId); 
	// 신고 세기
	public int getUserReportCount(Integer userId);
	
	// 신고 건 전체 불러오기
	public List<Report> selectAllReports(); 
	
	// 특정 사용자가 신고한 목록 조회 
	public int findByReporterId(Integer reportedUserId);
	
	// 특정 사용자 대한 신고당한 목록 조회
	public int findByReportedUserId(Integer reportedCommunityId);
	
	// 신고건 삭제
	public int deleteByReportId(Integer reportId);
	
	
	
}
