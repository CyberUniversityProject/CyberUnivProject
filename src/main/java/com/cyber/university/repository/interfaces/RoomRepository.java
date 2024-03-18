package com.cyber.university.repository.interfaces;

import java.util.List;

import com.cyber.university.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.RoomWithCollegeDto;
import com.cyber.university.repository.model.Room;

/**
 * 
 * @FileName : RoomRepository.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 13.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 강의실 repository
 */
@Mapper
public interface RoomRepository {

	// 강의실 등록
	public int insertById(Room room);

	// 강의실 등록 리스트
	public List<RoomWithCollegeDto> findAllCol();

	public List<Room> findAll();

	// 강의실 삭제
	public String deleteById(String id);

	// 강의실 수정
	public void updateById(Room room);

	// 강의실 조회(글 상세보기와는 달리 수정을 위한 기능)
	public Room findById(String id);

	// 전체 페이지 불러오기
	public List<Room> findAllwithPasing(@Param("offset") int offset, @Param("limit") int limit,
										@Param("id") String id);

	// 전체 게시물개수 계산
	public int getAllPgCount();

}
