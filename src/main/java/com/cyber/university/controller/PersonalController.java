package com.cyber.university.controller;

import com.cyber.university.dto.ChangePasswordDto;


import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;
import com.cyber.university.dto.LoginDto;
import com.cyber.university.dto.UserUpdateDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.dto.response.StudentInfoDto;
import com.cyber.university.dto.response.UserInfoForUpdateDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Staff;
import com.cyber.university.repository.model.StuStat;
import com.cyber.university.service.UserService;
import com.cyber.university.utils.Define;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.cyber.university.controller.StuSubController.SUGANG_PERIOD;


/**
 * 
  * @FileName : PersonalController.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 일반 컨트롤러
 */

@Controller
public class PersonalController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 메인 홈페이지
	 * 
	 * @author 이준혁
	 */
	@GetMapping("/")
	public String home(Model model) {
		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

		Integer period = SUGANG_PERIOD;
		model.addAttribute("periodNumber", period);
		System.out.println("period : " + period);

		// Principal 객체가 null이 아닌지 확인
		if (principal != null) {
			if (principal.getUserRole().equals("student")) {
				// 학생인 경우
				StudentInfoDto studentInfo = userService.readStudentInfo(principal.getId());
				model.addAttribute("userInfo", studentInfo);
			} else if (principal.getUserRole().equals("staff")) {
				// 직원인 경우
				Staff staffInfo = userService.readStaff(principal.getId());
				model.addAttribute("userInfo", staffInfo);
			} else {
				// 교수인 경우
				ProfessorInfoDto professorInfo = userService.readProfessorInfo(principal.getId());
				model.addAttribute("userInfo", professorInfo);
			}
		} else {
			// Principal 객체가 null인 경우에 대한 처리
			// 로그인되지 않은 사용자에게 보여줄 화면 등을 설정
			// 예: 로그인 화면으로 리다이렉트 또는 메시지 표시 등
			return "main";
		}

		return "main";
	}

	/**
	 * 로그인 페이지
	 * 
	 * @Author 이준혁
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}

	/**
	 * 로그인 처리
	 * 
	 * @Author 이준혁
	 * @param loginDto
	 * @param bindingResult
	 * @param response
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public String signInProc(@Valid LoginDto loginDto, BindingResult bindingResult, HttpServletResponse response,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		PrincipalDto principal = userService.login(loginDto);
		if ("on".equals(loginDto.getRememberId())) {
			Cookie cookie = new Cookie("id", loginDto.getId() + "");
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("id")) {
						c.setMaxAge(0);
						response.addCookie(c);
						break;
					}
				}
			}
		}
		session.setAttribute(Define.PRINCIPAL, principal);

		return "redirect:/";
	}

	/**
	 * 로그아웃
	 *
	 * @return 로그인 페이지
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();

		return "redirect:/login";
	}

	/**
	 * @return 에러페이지
	 */
	@GetMapping("/error")
	public String handleError() {
		return "/error/errorPage";
	}

	@GetMapping("/guide")
	public String pop() {

		return "/user/passwordPop";
	}

	/**
	 * 
	 * @Method Name : readStaffInfo
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 교직원 마이페이지
	 */
	@GetMapping("/info/staff")
	public String readStaffInfo(Model model) {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		Staff staff = userService.readStaff(principal.getId());
		model.addAttribute("staff", staff);

		return "/user/staffInfo";
	}

	/**
	 * 
	 * @Method Name : updatePassword
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비밀번호 변경 페이지
	 */
	@GetMapping("/password")
	public String updatePassword() {

		return "/user/updatePassword";
	}

	/**
	 * 
	 * @Method Name : updatePasswordProc
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비밀번호 수정
	 */
	@PutMapping("/password")
	// TODO :: @Valid 유효성 검사를 수행, BindingResult는 유효성 검사 결과를 저장
	public String updatePasswordProc(@Valid ChangePasswordDto changePasswordDto, BindingResult bindingResult) {

		// TODO : 만약 유효성 검사에서 오류가 발생하면?
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			// TODO : 오류 결과를 bindingResult에 저장
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			// TODO: CustomRestfullException 발생하여 클라이언트에게 알림
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		// TODO: 변경 전 비밀번호와 사용자의 현재 비밀번호를 비교하여 일치하지 않으면 CustomRestfullException을 발생시켜
		// 오류를 알려줌
		if (!passwordEncoder.matches(changePasswordDto.getBeforePassword(), principal.getPassword())) {
			throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		// TODO: 변경 후 비밀번호와 비밀번호 확인이 일치하지 않으면 CustomRestfullException을 발생시켜 오류를 알려줌
		if (!changePasswordDto.getAfterPassword().equals(changePasswordDto.getPasswordCheck())) {
			throw new CustomRestfullException("변경할 비밀번호와 비밀번호 확인은 같아야합니다.", HttpStatus.BAD_REQUEST);
		}
		changePasswordDto.setId(principal.getId());
		// TODO : 변경 후 비밀번호를 인코딩하여 저장
		changePasswordDto.setAfterPassword(passwordEncoder.encode(changePasswordDto.getAfterPassword()));
		// TODO : userService에 있는 updatePassword를 이용하여 비번 업데이트
		userService.updatePassword(changePasswordDto);

		return "redirect:/password";
	}

	/**
	 * 
	 * @Method Name : findId
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 아이디 찾기 페이지
	 */
	@GetMapping("/find/id")
	public String findId() {

		return "/user/findId";
	}

	/**
	 * 
	 * @Method Name : findIdProc
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 아이디 찾기 결과 페이지
	 */
	@PostMapping("/find/id")
	public String findIdProc(Model model, @Valid FindIdFormDto findIdFormDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		Integer findId = userService.readIdByNameAndEmail(findIdFormDto);
		model.addAttribute("id", findId);
		model.addAttribute("name", findIdFormDto.getName());

		return "/user/findIdComplete";
	}

	/**
	 * 
	 * @Method Name : findPassword
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비번찾기페이지
	 */
	@GetMapping("/find/password")
	public String findPassword() {

		return "/user/findPassword";
	}

	/**
	 * 
	 * @Method Name : findPasswordProc
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 비밀번호 찾기 결과
	 */
	@PostMapping("/find/password")
	public String findPasswordProc(Model model, @Valid FindPasswordFormDto findPasswordFormDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		String password = userService.updateTempPassword(findPasswordFormDto);
		model.addAttribute("name", findPasswordFormDto.getName());
		model.addAttribute("password", password);

		return "/user/findPasswordComplete";
	}

	/**
	 * 
	 * @Method Name : updateUser
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 개인정보수정페이지
	 */
	@GetMapping("/update")
	public String updateUser(Model model) {

		PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
		UserInfoForUpdateDto userInfoForUpdateDto = null;
		if ("staff".equals(principal.getUserRole())) {
			userInfoForUpdateDto = userService.readStaffInfoForUpdate(principal.getId());
		}
		if ("student".equals(principal.getUserRole())) {
			userInfoForUpdateDto = userService.readStudentInfoForUpdate(principal.getId());
		}
		if ("professor".equals(principal.getUserRole())) {
			userInfoForUpdateDto = userService.readProfessorInfoForUpdate(principal.getId());
		}
		model.addAttribute("userInfo", userInfoForUpdateDto);

		return "/user/updateUser";
	}

	/**
	 * 
	 * @Method Name : updateUserProc
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 이준혁
	 * @변경이력 :
	 * @Method 설명 : 회원정보 수정
	 */
	@PutMapping("/update")
	public String updateUserProc(@Valid UserInfoForUpdateDto userInfoForUpdateDto, BindingResult bindingResult,
	        @RequestParam(name = "password", required = false) String password) {

	    // 입력 유효성 검사
	    if (bindingResult.hasErrors()) {
	        // 입력이 유효하지 않을 경우 예외를 발생시킵니다.
	        // 발생한 예외는 클라이언트에게 HTTP 상태 코드 400 (잘못된 요청)과 함께 전달됩니다.
	        throw new CustomRestfullException(getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
	    }

	    // 세션에서 현재 사용자의 정보를 가져옵니다.
	    PrincipalDto principal = getPrincipalFromSession();

	    // 입력한 패스워드가 현재 사용자의 패스워드와 일치하는지 확인합니다.
	    if (!passwordEncoder.matches(password, principal.getPassword())) {
	        // 패스워드가 일치하지 않을 경우 예외를 발생시킵니다.
	        // 발생한 예외는 클라이언트에게 HTTP 상태 코드 400 (잘못된 요청)과 함께 전달됩니다.
	        throw new CustomRestfullException(Define.WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
	    }

	    // 사용자 업데이트 정보 설정
	    UserUpdateDto updateDto = new UserUpdateDto();
	    updateDto.setUserId(principal.getId());
	    updateDto.setAddress(userInfoForUpdateDto.getAddress());
	    updateDto.setEmail(userInfoForUpdateDto.getEmail());
	    updateDto.setTel(userInfoForUpdateDto.getTel());

	    // 사용자 역할에 따라 업데이트 메서드 호출
	    String redirectPath = "";
	    switch (principal.getUserRole()) {
	        case "staff":
	            // 교직원 정보를 업데이트합니다.
	            userService.updateStaff(updateDto);
	            redirectPath = "/info/staff";
	            break;
	        case "student":
	            // 학생 정보를 업데이트합니다.
	            userService.updateStudent(updateDto);
	            redirectPath = "/info/student";
	            break;
	        case "professor":
	            // 교수 정보를 업데이트합니다.
	            userService.updateProfessor(updateDto);
	            redirectPath = "/professor/info";
	            break;
	        default:
	            redirectPath = "/";
	            break;
	    }

	    // 사용자 업데이트 후 해당 역할 페이지로 리다이렉트합니다.
	    return "redirect:" + redirectPath;
	}

	// 세션에서 현재 사용자의 정보를 가져오는 메서드입니다.
	private PrincipalDto getPrincipalFromSession() {
	    return (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
	}

	// 입력 유효성 검사에서 발생한 오류 메시지를 문자열로 반환하는 메서드입니다.
	private String getValidationErrors(BindingResult bindingResult) {
	    return bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
	            .collect(Collectors.joining("\\n"));
	}


}
