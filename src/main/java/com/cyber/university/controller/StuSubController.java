package com.cyber.university.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyber.university.dto.CurrentSemesterSubjectSearchFormDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.StuSubAppDto;
import com.cyber.university.dto.response.SubjectDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Break;
import com.cyber.university.repository.model.Department;
import com.cyber.university.repository.model.PreStuSub;
import com.cyber.university.repository.model.StuStat;
import com.cyber.university.repository.model.StuSub;
import com.cyber.university.repository.model.Student;
import com.cyber.university.service.ApplySubjectService;
import com.cyber.university.service.BreakService;
import com.cyber.university.service.CollegeService;
import com.cyber.university.service.PreStuSubService;
import com.cyber.university.service.StuStatService;
import com.cyber.university.service.StuSubService;
import com.cyber.university.service.SubjectService;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;
import com.cyber.university.utils.StuStatUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sugang")
@RequiredArgsConstructor
public class StuSubController {
	
	@Autowired
	private final HttpSession session;

	@Autowired
	private final SubjectService subjectService;

	@Autowired
	private final CollegeService collegeService;

	@Autowired
	private final PreStuSubService preStuSubService;

	@Autowired
	private final StuSubService stuSubService;

	@Autowired
	private final StuStatService stuStatService;

	@Autowired
	private final BreakService breakAppService;

	@Autowired
	private final UserService userService;
	
	
	
	
		// 예비 수강신청 기간 : 0, 수강신청 기간 : 1, 수강신청 기간 종료 : 2
		public static int SUGANG_PERIOD = 2;

		// 예비 수강신청 기간에서 수강신청 기간으로 변경하는 페이지 (교직원용)
		@GetMapping("/period")
		public String updatePeriod() {

			return "/stuSub/updatePeriod";
		}

		// 예비 수강 신청 기간 -> 수강 신청 기간
		@GetMapping("/updatePeriod1")
		public String updatePeriodProc1() {
			SUGANG_PERIOD = 1;

			stuSubService.createStuSubByPreStuSub();

			return "/stuSub/updatePeriod";
		}

		// 수강 신청 기간 -> 종료
		@GetMapping("/updatePeriod2")
		public String updatePeriodProc2() {
			SUGANG_PERIOD = 2;

			return "/stuSub/updatePeriod";
		}

	// 종료 -> 예비 수강 신청 기간
	@GetMapping("/updatePeriod0")
	public String updatePeriodProc0() {
		SUGANG_PERIOD = 0;

		return "/stuSub/updatePeriod";
	}

		// 과목 조회 (현재 학기)
		@GetMapping("/subjectList/{page}")
		public String readSubjectList(Model model, @PathVariable(name = "page") Integer page) {
			// 현재 학기의 강의 리스트 조회
			List<SubjectDto> subjectList = subjectService.readSubjectListByCurrentSemester();

			// 전체 강의 수
			int subjectCount = subjectList.size();
			model.addAttribute("subjectCount", subjectCount);

			// 총 페이지 수
			int pageSize = 20;
			int pageCount = (int) Math.ceil(subjectCount / (double) pageSize);
			model.addAttribute("pageCount", pageCount);

			// 현재 페이지
			model.addAttribute("page", page);

			// 현재 페이지에 해당하는 강의 리스트 조회
			int startIndex = (page - 1) * pageSize;
			List<SubjectDto> subjectListLimit = subjectList.subList(startIndex, Math.min(startIndex + pageSize, subjectCount));
			model.addAttribute("subjectList", subjectListLimit);

			// 전체 학과 정보 조회
			List<Department> deptList = collegeService.readDeptAll();
			model.addAttribute("deptList", deptList);

			// 중복을 제거한 강의 이름 리스트
			List<String> subNameList = subjectList.stream()
					.map(SubjectDto::getName)
					.distinct()
					.collect(Collectors.toList());
			model.addAttribute("subNameList", subNameList);

			return "/stuSub/subList";
		}

		// 과목 조회 (현재 학기)에서 필터링
		@GetMapping("/subjectList/search")
		public String readSubjectListSearch(Model model, @Validated CurrentSemesterSubjectSearchFormDto searchForm) {
			// 검색 조건에 따라 현재 학기의 강의 리스트 조회
			List<SubjectDto> subjectList = subjectService.readSubjectListSearchByCurrentSemester(searchForm);
			model.addAttribute("subjectList", subjectList);

			// 검색 결과 강의 수
			int subjectCount = subjectList.size();
			model.addAttribute("subjectCount", subjectCount);

			// 필터에 사용할 전체 학과 정보 조회
			List<Department> deptList = collegeService.readDeptAll();
			model.addAttribute("deptList", deptList);

			// 필터에 사용할 강의 이름 정보 (중복 값 제거)
			List<String> subNameList = subjectList.stream()
					.map(SubjectDto::getName)
					.distinct()
					.collect(Collectors.toList());
			model.addAttribute("subNameList", subNameList);

			return "/stuSub/subList";
		}

	/**
		 * @return 예비 수강 신청
		 */
	@GetMapping("/pre/{page}")
	public String preStuSubApplication(Model model, @PathVariable(name = "page") Integer page) {
		// 예비 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 0) {
			throw new CustomRestfullException("예비 수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Student studentInfo = userService.readStudent(principal.getId());

		// 현재 학생의 상태 확인
		StuStat stuStatEntity = stuStatService.readCurrentStatus(studentInfo.getId());
		List<Break> breakAppList = breakAppService.readByStudentId(studentInfo.getId());

		// 학생 상태 확인
		StuStatUtil.checkStuStat("수강신청", stuStatEntity, breakAppList);

		// 현재 학기의 강의 리스트 조회
		List<SubjectDto> subjectList = subjectService.readSubjectListByCurrentSemester();

		// 전체 강의 수 및 페이지 수 설정
		int subjectCount = subjectList.size();
		int pageSize = 20;
		int pageCount = (int) Math.ceil(subjectCount / (double) pageSize);
		model.addAttribute("subjectCount", subjectCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("page", page);

		// 현재 페이지에 해당하는 강의 리스트 조회
		int startIndex = (page - 1) * pageSize;
		List<SubjectDto> subjectListLimit = subjectList.subList(startIndex, Math.min(startIndex + pageSize, subjectCount));

		// 각 강의의 예비 수강 신청 여부 설정
		for (SubjectDto sub : subjectListLimit) {
			PreStuSub preStuSub = preStuSubService.readPreStuSub(principal.getId(), sub.getId());
			sub.setStatus(preStuSub != null);
		}
		model.addAttribute("subjectList", subjectListLimit);

		// 필터에 사용할 전체 학과 정보 조회
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거)
		List<String> subNameList = subjectList.stream()
				.map(SubjectDto::getName)
				.distinct()
				.collect(Collectors.toList());
		model.addAttribute("subNameList", subNameList);

		return "/stuSub/preApplication";
	}

		/**
		 * 예비 수강 신청 처리 (신청)
		 */

		@PostMapping("/pre/{subjectId}")
		public String insertPreStuSubAppProc(@PathVariable(name="subjectId") Integer subjectId) {
			// 예비 수강 신청 기간이 아니라면 예외 처리
			if (SUGANG_PERIOD != 0) {
				throw new CustomRestfullException("예비 수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
			}

			// 현재 로그인한 사용자 정보 확인
			PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
			Integer studentId = principal.getId();

			// 예비 수강 신청 처리
			preStuSubService.createPreStuSub(studentId, subjectId);

			// 강의 검색 페이지로 리다이렉트
			return "redirect:/sugang/pre/1";
		}


	/**
		 * 예비 수강 신청 처리 (취소)
		 */

	@DeleteMapping("/pre/{subjectId}")
	public String deletePreStuSubAppProc(@PathVariable(name="subjectId") Integer subjectId, @RequestParam(name ="type") Integer type) {
		// 예비 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 0) {
			throw new CustomRestfullException("예비 수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer studentId = principal.getId();

		// 예비 수강 신청 취소 처리
		preStuSubService.deletePreStuSub(studentId, subjectId);

		// 취소 후 리다이렉트할 페이지 결정
		return (type == 0) ? "redirect:/sugang/pre/1" : "redirect:/sugang/preAppList?type=0";
	}


	// 예비 수강 신청 강의 목록에서 필터링
	@GetMapping("/pre/search")
	public String preStuSubApplicationSearch(Model model, @Validated CurrentSemesterSubjectSearchFormDto searchForm) {
		// 예비 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 0) {
			throw new CustomRestfullException("예비 수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

		// 검색 조건에 따라 현재 학기의 강의 리스트 조회
		List<SubjectDto> subjectList = subjectService.readSubjectListSearchByCurrentSemester(searchForm);

		// 각 강의의 예비 수강 신청 여부 설정
		for (SubjectDto sub : subjectList) {
			PreStuSub preStuSub = preStuSubService.readPreStuSub(principal.getId(), sub.getId());
			sub.setStatus(preStuSub != null);
		}

		// 전체 강의 수 설정 및 모델에 추가
		int subjectCount = subjectList.size();
		model.addAttribute("subjectCount", subjectCount);
		model.addAttribute("subjectList", subjectList);

		// 필터에 사용할 전체 학과 정보 조회 및 모델에 추가
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거) 설정 및 모델에 추가
		List<String> subNameList = subjectList.stream()
				.map(SubjectDto::getName)
				.distinct()
				.collect(Collectors.toList());
		model.addAttribute("subNameList", subNameList);

		return "/stuSub/preApplication";
	}


		/**
		 * @return 수강 신청
		 */
	@GetMapping("/application/{page}")
	public String stuSubApplication(Model model, @PathVariable(name = "page") Integer page) {
		// 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 1) {
			throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Student studentInfo = userService.readStudent(principal.getId());

		// 학생 상태 확인
		StuStat stuStatEntity = stuStatService.readCurrentStatus(studentInfo.getId());
		List<Break> breakAppList = breakAppService.readByStudentId(studentInfo.getId());
		StuStatUtil.checkStuStat("수강신청", stuStatEntity, breakAppList);

		// 현재 학기의 강의 리스트 조회
		List<SubjectDto> subjectList = subjectService.readSubjectListByCurrentSemester();

		// 전체 강의 수 및 페이지 수 설정
		int subjectCount = subjectList.size();
		int pageSize = 20;
		int pageCount = (int) Math.ceil(subjectCount / (double) pageSize);
		model.addAttribute("subjectCount", subjectCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("page", page);

		// 현재 페이지에 해당하는 강의 리스트 조회
		int startIndex = (page - 1) * pageSize;
		List<SubjectDto> subjectListLimit = subjectList.subList(startIndex, Math.min(startIndex + pageSize, subjectCount));

		// 각 강의의 수강 여부 설정
		for (SubjectDto sub : subjectListLimit) {
			StuSub stuSub = stuSubService.readStuSub(principal.getId(), sub.getId());
			sub.setStatus(stuSub != null);
		}
		model.addAttribute("subjectList", subjectListLimit);

		// 필터에 사용할 전체 학과 정보 조회 및 모델에 추가
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거) 설정 및 모델에 추가
		List<String> subNameList = subjectList.stream()
				.map(SubjectDto::getName)
				.distinct()
				.collect(Collectors.toList());
		model.addAttribute("subNameList", subNameList);

		return "stuSub/application";
	}

	// 수강 신청 강의 목록에서 필터링
	@GetMapping("/application/search")
	public String stuSubApplicationSearch(Model model, @Validated CurrentSemesterSubjectSearchFormDto searchForm) {
		// 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 1) {
			throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

		// 검색 조건에 따라 현재 학기의 강의 리스트 조회
		List<SubjectDto> subjectList = subjectService.readSubjectListSearchByCurrentSemester(searchForm);

		// 각 강의의 수강 여부 설정
		for (SubjectDto sub : subjectList) {
			StuSub stuSub = stuSubService.readStuSub(principal.getId(), sub.getId());
			sub.setStatus(stuSub != null);
		}

		// 전체 강의 수 설정 및 모델에 추가
		int subjectCount = subjectList.size();
		model.addAttribute("subjectCount", subjectCount);
		model.addAttribute("subjectList", subjectList);

		// 필터에 사용할 전체 학과 정보 조회 및 모델에 추가
		List<Department> deptList = collegeService.readDeptAll();
		model.addAttribute("deptList", deptList);

		// 필터에 사용할 강의 이름 정보 (중복 값 제거) 설정 및 모델에 추가
		List<String> subNameList = subjectList.stream()
				.map(SubjectDto::getName)
				.distinct()
				.collect(Collectors.toList());
		model.addAttribute("subNameList", subNameList);

		return "/stuSub/application";
	}


	/**
		 * 수강 신청 처리 (신청)
		 */
	@PostMapping("/insertApp/{subjectId}")
	public String insertStuSubAppProc(@PathVariable(name = "subjectId") Integer subjectId, @RequestParam(name = "type") Integer type) {
		// 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 1) {
			throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer studentId = principal.getId();

		// 수강 신청 처리
		stuSubService.createStuSub(studentId, subjectId);

		// 신청 후 리다이렉트할 페이지 결정
		return (type == 0) ? "redirect:/sugang/application/1" : "redirect:/sugang/preAppList?type=1";
	}


	/**
		 * 수강 신청 처리 (취소)
		 */
	@DeleteMapping("/deleteApp/{subjectId}")
	public String deleteStuSubAppProc(@PathVariable(name = "subjectId") Integer subjectId, @RequestParam(name = "type") Integer type) {
		// 수강 신청 기간이 아니라면 예외 처리
		if (SUGANG_PERIOD != 1) {
			throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
		}

		// 현재 로그인한 사용자 정보 확인
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Integer studentId = principal.getId();

		// 수강 신청 취소 처리
		stuSubService.deleteStuSub(studentId, subjectId);

		// 취소 후 리다이렉트할 페이지 결정
		return (type == 0) ? "redirect:/sugang/application/1" : "redirect:/sugang/preAppList?type=1";
	}


		/**
		 * @return 예비 수강 신청 내역
		 */
		@GetMapping("/preAppList")
		public String preStuSubAppList(Model model, @RequestParam(name = "type") Integer type) {
			// 현재 로그인한 사용자 정보 확인
			PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
			Student studentInfo = userService.readStudent(principal.getId());

			// 학생 상태 확인
			StuStat stuStatEntity = stuStatService.readCurrentStatus(studentInfo.getId());
			List<Break> breakAppList = breakAppService.readByStudentId(studentInfo.getId());
			StuStatUtil.checkStuStat("수강신청", stuStatEntity, breakAppList);

			model.addAttribute("type", type);

			// 예비 수강 신청 기간에 조회 시
			if (type == 0) {
				List<StuSubAppDto> preStuSubList = preStuSubService.readPreStuSubList(principal.getId());
				model.addAttribute("stuSubList", preStuSubList);
				int sumGrades = preStuSubList.stream().mapToInt(StuSubAppDto::getGrades).sum();
				model.addAttribute("sumGrades", sumGrades);
				return "/stuSub/preAppList";
			}

			// 수강 신청 기간에 조회 시
			if (SUGANG_PERIOD != 1) {
				throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
			}

			// 수강 신청이 완료되지 않은 예비 수강 신청 내역
			List<StuSubAppDto> preStuSubList1 = stuSubService.readPreStuSubByStuSub(principal.getId());
			model.addAttribute("stuSubList", preStuSubList1);

			// 수강 신청 내역
			List<StuSubAppDto> stuSubList = stuSubService.readStuSubList(principal.getId());
			model.addAttribute("stuSubListC", stuSubList);
			int sumGrades = stuSubList.stream().mapToInt(StuSubAppDto::getGrades).sum();
			model.addAttribute("sumGrades", sumGrades);

			return "/stuSub/preAppList";
		}

		/**
		 * @return 수강 신청 내역
		 */
		@GetMapping("/list")
		public String stuSubAppList(Model model) {
			// 예비 수강 신청 기간이 아니라면 예외 처리
			if (SUGANG_PERIOD == 0) {
				throw new CustomRestfullException("수강 신청 기간이 아닙니다.", HttpStatus.BAD_REQUEST);
			}

			// 현재 로그인한 사용자 정보 확인
			PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
			Student studentInfo = userService.readStudent(principal.getId());

			// 학생 상태 확인
			StuStat stuStatEntity = stuStatService.readCurrentStatus(studentInfo.getId());
			List<Break> breakAppList = breakAppService.readByStudentId(studentInfo.getId());
			StuStatUtil.checkStuStat("수강신청", stuStatEntity, breakAppList);

			// 학생의 수강 신청 내역 조회
			List<StuSubAppDto> stuSubList = stuSubService.readStuSubList(principal.getId());
			model.addAttribute("stuSubList", stuSubList);

			// 총 성적 계산
			int sumGrades = stuSubList.stream().mapToInt(StuSubAppDto::getGrades).sum();
			model.addAttribute("sumGrades", sumGrades);

			return "/stuSub/appList";
		}


		/**
		  * @Method Name : timeTableProc
		  * @작성일 : 2024. 3. 21.
		  * @작성자 : 박경진
		  * @변경이력 : 
		  * @Method 설명 : 강의 시간표 조회 테이블
		  */
		@GetMapping("/timeTable")
		private String timeTableProc(Model model) {

			PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
			Integer userId = principal.getId();

			if (userId == null) {
				throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			// student 정보
			
			List<StuSubAppDto> stuSubList = stuSubService.readStuSubList(userId);
			model.addAttribute("stuSubList",stuSubList);
			
			return "/stuSub/timeTable";
		}


}
