package com.cyber.university.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @FileName : PageRequestDto.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 16.
 * @작성자 : 김수현
 * @변경이력 :
 * @프로그램 설명 : 페이지네이션, 페이지 요청 Dto
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageRequestDto<Room> {

        // 현재 페이지 번호 : page == number
        private int page;

        // 페이지당 출력할 데이터 개수
        private int size;

        public PageRequestDto(int page, int size) {
            this.page = page;
            this.size = size;
        }

    }

