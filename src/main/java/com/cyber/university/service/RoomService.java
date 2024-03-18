package com.cyber.university.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cyber.university.dto.PageRequestDto;
import com.cyber.university.dto.PageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.RoomDto;
import com.cyber.university.dto.RoomWithCollegeDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.RoomRepository;
import com.cyber.university.repository.model.Room;

/**
  * @FileName : RoomService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 13. 
  * @작성자 : 김수현
  * @변경이력 :
  * @프로그램 설명 : 강의실 service
  */
@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Transactional
	public void insert(RoomDto roomDto) {
		Room room = new Room();
		
		room.setId(roomDto.getId());
		room.setCollegeId(roomDto.getCollegeId());
		
		int result = roomRepository.insertById(room);
		if (result != 1) {
			throw new CustomRestfullException("등록 생성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

		// 강의실 전체 리스트 불러오기
		public List<Room> roomList() {
			List<Room> roomList = roomRepository.findAll();
			return roomRepository.findAll();
		}
		// 강의실 삭제
		public String deleteById(String id) {
			return roomRepository.deleteById(id);
		}
		// 강의실 상세 조회
		public Room findById(String id) {
			return roomRepository.findById(id);

		}
		// 강의실 수정
		@Transactional
		public void updateById(Room room) {
		 roomRepository.updateById(room);
		}
		
		
		@Transactional
		public List<RoomWithCollegeDto> findAll(){
			
			return roomRepository.findAllCol();
		}

	/**
	 * @FileName : RoomService.java
	 * @Project : CyberUniversity
	 * @Date : 2024. 3. 13.
	 * @작성자 : 김수현
	 * @변경이력 :
	 * @프로그램 설명 : 강의실 List 페이징
	 */

	// 총 강의실 수 조회
	public int getTotoalRoomCount() {
		return roomRepository.getAllPgCount();
	}


	// 페이징 된 강의실 목록 조회
	public PageRequestDto<RoomDto>  getfindwithallpaging(PageRequestDto pageRequestDto, String id) {
		int page = pageRequestDto.getPage();
		int size = pageRequestDto.getSize();
		int offset = (page - 1) * size; // offset 계산

		// 총 데이터 개수 조회
		long totalElements = roomRepository.getAllPgCount();

		// 페이징 된 목록 조회
		List<Room> roomList = roomRepository.findAllwithPasing(offset, size, id);

		// 페이징 결과 객체 생성
		List<RoomDto> roomDtoList = roomList.stream()
											.map(this::convertToDto)
											.collect(Collectors.toList());
		/*PageResponseDto<RoomDto> pageRes = new PageResponseDto<>(roomDtoList ,page, totalElements, size);
*/

		return null;
	}

	private RoomDto convertToDto(Room room) {
		RoomDto roomDto = new RoomDto();
		roomDto.setId(room.getId());
		return roomDto;
	}


}

	
	

