package com.cyber.university.dto.response;

import lombok.Data;

/**
 * packageName    : com.cyber.university.dto.response
 * fileName       : SubjectListDto
 * author         : 이준혁
 * date           : 2024/03/22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/22          이준혁       최초 생성
 */

@Data
public class SubjectListDto {

    private Integer id;
    private String name;

    private String professorName;


    private Integer professorId;
    private String roomId;
    private Integer deptId;
    private String type;			// 강의구분
    private Integer subYear;		// 개설연도
    private Integer semester;		// 개설학기
    private String subDay;			// 요일
    private Integer startTime;		// 강의 시작시간
    private Integer endTime;		// 강의 종료시간
    private Integer grades;			// 이수학점
    private Integer capacity;		// 수강 정원
    private Integer numOfStudent;	// 현재 신청 인원
}
