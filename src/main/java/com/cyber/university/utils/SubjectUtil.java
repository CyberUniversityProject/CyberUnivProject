package com.cyber.university.utils;

import java.util.List;

import com.cyber.university.dto.SubjectFormDto;
import com.cyber.university.repository.model.Subject;

/**
 * 
  * @FileName : SubjectUtil.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의 입력 관련 유틸
 */
public class SubjectUtil {
	
	

    /**
     * 강의 시간 중복 여부를 계산하는 메서드
     * @param subjectFormDto 강의 등록 정보를 담은 DTO
     * @param subjectList 이미 등록된 강의 목록
     * @return 중복되는 강의 시간이 없으면 true, 있으면 false를 반환합니다.
     */
    public boolean calculate(SubjectFormDto subjectFormDto, List<Subject> subjectList) {
        for (int i = 0; i < subjectList.size(); i++) {
            // 현재 검사 중인 강의의 시작 시간과 종료 시간이 이미 등록된 강의의 시간 범위와 중첩되는지 확인
            if ((subjectList.get(i).getStartTime() <= subjectFormDto.getStartTime()
                    && subjectFormDto.getStartTime() < subjectList.get(i).getEndTime())
                    || (subjectList.get(i).getStartTime() < subjectFormDto.getEndTime()
                            && subjectFormDto.getEndTime() <= subjectList.get(i).getEndTime())) {
                // 중첩되는 경우 중복이 발생하므로 false를 반환합니다.
                return false;
            }
        }
        // 중첩되는 강의가 없는 경우에는 true를 반환합니다.
        return true;
    }

}
