package com.cyber.university.repository.interfaces;

import java.util.List;

import com.cyber.university.dto.RoomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyber.university.dto.RoomWithCollegeDto;
import com.cyber.university.repository.model.Room;
import com.cyber.university.repository.model.Student;

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

	
	
	// id별 강의실 조회
	public Integer selectRoomAmountByRoomId(String id);
	
	
	// 전체 강의실 개수
	public Integer selectRoomAmount();
	
	// 페이지별 강의실 조회
	
	public Room selectRoomList(RoomDto roomDto);
	
	public List<Room> selectByRoomId(RoomDto roomDto);
	
	
	
	

}
