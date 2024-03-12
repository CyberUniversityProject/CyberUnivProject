package com.cyber.university.controller;

import com.cyber.university.dto.ChangePasswordDto;

import com.cyber.university.dto.FindIdFormDto;
import com.cyber.university.dto.FindPasswordFormDto;
import com.cyber.university.dto.LoginDto;
import com.cyber.university.dto.professor.ProfessorInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.StudentInfoDto;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : PersonalController
 * author         : 이준혁
 * date           : 2024/03/09
 * description    : 일반 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/9/24        이준혁       최초 생성
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
     * @author 이준혁
     */
    @GetMapping("/")
    public String home(Model model) {
        PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);

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
     * @Author 이준혁
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }


    /**
     * 로그인 처리
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
		// TODO: 변경 전 비밀번호와 사용자의 현재 비밀번호를 비교하여 일치하지 않으면 CustomRestfullException을 발생시켜 오류를 알려줌
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




}
