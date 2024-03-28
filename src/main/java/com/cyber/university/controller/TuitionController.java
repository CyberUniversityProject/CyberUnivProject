package com.cyber.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Break;
import com.cyber.university.repository.model.StuStat;
import com.cyber.university.repository.model.Student;
import com.cyber.university.repository.model.Tuition;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.BreakService;
import com.cyber.university.service.CollegeService;
import com.cyber.university.service.StuStatService;
import com.cyber.university.service.TuitionService;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;
import com.cyber.university.utils.StuStatUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 
  * @FileName : TuitionController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 15. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 등록금, 장학 관련 컨트롤러
 */
@Controller
@RequestMapping("/tuition")
@RequiredArgsConstructor
public class TuitionController {
	
	@Autowired
	private final HttpSession session;
	
    @Autowired
    private final TuitionService tuitionService;
	
	@Autowired
	private final StuStatService stuStatService;
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final BreakService breakService;
	
	@Autowired
	private final CollegeService collegeService;
	
	
	/**
	 * @return 납부된 등록금 내역 조회 페이지
	 */
	@GetMapping("/list")
	public String tuitionList(Model model) {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

		List<Tuition> tuitionList = tuitionService.readTuitionListByStatus(principal.getId(), true);

		model.addAttribute("tuitionList", tuitionList);

		return "/tuition/tuiList";
	}

	/**
	 * @return 등록금 납부 고지서 조회 페이지
	 * 
	 *         해당 학기 (2024-1)에 등록금을 납부한 기록이 있다면 납부하기 버튼 제거
	 */
	@GetMapping("/payment")
	public String tuitionPayment(Model model) {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Student studentInfo = userService.readStudent(principal.getId());
		model.addAttribute("student", studentInfo);

		// 등록금 납부 대상이 아니라면 진입 불가
		// 해당 학생의 학적 상태가 '졸업' 또는 '자퇴'라면 X
		// 해당 학생이 이번 학기 휴학을 승인받은 상태라면 X

		StuStat stuStatEntity = stuStatService.readCurrentStatus(studentInfo.getId());
		List<Break> breakAppList = breakService.readByStudentId(studentInfo.getId()); // 최근 순으로 정렬되어 있음

		StuStatUtil.checkStuStat("등록금", stuStatEntity, breakAppList);

		// 학과 이름
		String deptName = collegeService.readDeptById(studentInfo.getDeptId()).getName();
		model.addAttribute("deptName", deptName);

		// 단과대 이름
		String collName = collegeService
				.readCollById(collegeService.readDeptById(studentInfo.getDeptId()).getCollegeId()).getName();
		model.addAttribute("collName", collName);

		// principal.getId()를 매개변수로
		Tuition tuitionEntity = tuitionService.readByStudentIdAndSemester(principal.getId(), Define.CURRENT_YEAR,
				Define.CURRENT_SEMESTER);

		// 등록금 고지서가 생성되어 있지 않다면 들어올 수 없음
		if (tuitionEntity == null) {
			throw new CustomRestfullException("등록금 납부 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		model.addAttribute("tuition", tuitionEntity);

		return "/tuition/payment";
	}

	/**
	 * 등록금 납부
	 * 
	 * @return 등록금 납부 페이지로 다시 돌아가서 납부 완료됨을 보여주기
	 */
	@PostMapping("/payment")
	public String tuitionPaymentProc() {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		tuitionService.updateStatus(principal.getId());

		return "redirect:/tuition/payment";
	}

	/**
	 * 장학금 유형 설정 + 등록금 납부 고지서 생성 페이지
	 */
	@GetMapping("/bill")
	public String createPayment(Model model) {

		return "/tuition/createPayment";
	}

	/**
	 * 등록금 납부 고지서 생성
	 */
	@GetMapping("/create")
	public String createTuiProc(Model model) {

		// 전체 학생 목록
		List<Integer> studentIdList = stuStatService.readIdList();

		// 고지서 생성 개수 반환
		int insertCount = 0;

		// 모든 학생에 대해 일괄 생성 (고지서 생성 대상인지는 서비스에서 확인)
		for (Integer studentId : studentIdList) {
			// 생성될 때마다 +1됨
			insertCount += tuitionService.createTuition(studentId);
		}

		
		// jsp로 생성 개수 보내기
		model.addAttribute("insertCount", insertCount);
		System.out.println(insertCount);

		return "/tuition/createPayment";
	}


}
