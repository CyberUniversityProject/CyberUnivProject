package com.cyber.university.controller;

import com.cyber.university.dto.CreateProfessorDto;
import com.cyber.university.dto.CreateStaffDto;
import com.cyber.university.dto.CreateStudentDto;
import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.dto.StudentListForm;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Professor;
import com.cyber.university.repository.model.Student;
import com.cyber.university.service.ProfessorService;
import com.cyber.university.service.StudentService;
import com.cyber.university.service.UserService;

import com.cyber.university.utils.Define;
import jakarta.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * packageName    : com.cyber.university.controller
 * fileName       : UserController
 * author         : 이준혁
 * date           : 2024/03/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ProfessorService professorService;

    /**
     * @return staff 입력 페이지
     */
    @GetMapping("/staff")
    public String createStaff() {

        return "/user/createStaff";
    }
    
    /**
	 * staff 생성 post 처리
	 * 
	 * @param createStaffDto
	 * @return "redirect:/user/staff"
	 */
	@PostMapping("/staff")
	public String createStaffProc(@Valid CreateStaffDto createStaffDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		userService.createStaffToStaffAndUser(createStaffDto);

		return "redirect:/user/staff";
	}
	
	
	
    
    
    /**
     * 
      * @Method Name : showStudentList
      * @작성일 : 2024. 3. 11.
      * @작성자 : 이준혁
      * @변경이력 : 
      * @Method 설명 : 학생조회
     */
    @GetMapping("/studentList")
	public String showStudentList(Model model, @RequestParam(name = "studentId", required = false) Integer studentId,
			@RequestParam(name = "deptId", required = false) Integer deptId) {

		StudentListForm studentListForm = new StudentListForm();
		studentListForm.setPage(0);
		if (studentId != null) {
			studentListForm.setStudentId(studentId);
		} else if (deptId != null) {
			studentListForm.setDeptId(deptId);
		}
		Integer amount = studentService.readStudentAmount(studentListForm);
		if (studentId != null) {
			amount = 1;
		}
		List<Student> list = studentService.readStudentList(studentListForm);

		// 페이징처리
		model.addAttribute("listCount", Math.ceil(amount / 20.0));
		// 학생 리스트
		model.addAttribute("studentList", list);
		// 학과 id
		model.addAttribute("deptId", deptId);
		// 첫 페이지를 1페이지로 표시
		model.addAttribute("page", 1);

		return "/user/studentList";
	}
    
    /**
     * 
      * @Method Name : showStudentListByPage
      * @작성일 : 2024. 3. 11.
      * @작성자 : 이준혁
      * @변경이력 : 
      * @Method 설명 : 학생조회 페이징 처리
     */
    @GetMapping("/studentList/{page}")
	public String showStudentListByPage(Model model, @PathVariable(name = "page", required = false) Integer page,
			@RequestParam(name = "deptId", required = false) Integer deptId) {

		StudentListForm studentListForm = new StudentListForm();
		if (deptId != null) {
			studentListForm.setDeptId(deptId);
		}
		studentListForm.setPage((page - 1) * 20);
		Integer amount = studentService.readStudentAmount(studentListForm);
		List<Student> list = studentService.readStudentList(studentListForm);

		model.addAttribute("listCount", Math.ceil(amount / 20.0));
		model.addAttribute("studentList", list);
		model.addAttribute("page", page);

		return "/user/studentList";
	}
    
    
    /**
	 * 교수 조회
	 * 
	 * @param model
	 * @return 교수 조회 페이지
	 */
	@GetMapping("/professorList")
	public String showProfessorList(Model model, @RequestParam(name = "professorId", required = false) Integer professorId,
			@RequestParam(name = "deptId", required = false) Integer deptId) {

		ProfessorListForm professorListForm = new ProfessorListForm();
		professorListForm.setPage(0);
		if (professorId != null) {
			professorListForm.setProfessorId(professorId);
		} else if (deptId != null) {
			professorListForm.setDeptId(deptId);
		}
		Integer amount = professorService.readProfessorAmount(professorListForm);
		if (professorId != null) {
			amount = 1;
		}
		List<Professor> list = professorService.readProfessorList(professorListForm);

		model.addAttribute("listCount", Math.ceil(amount / 20.0));
		model.addAttribute("professorList", list);
		model.addAttribute("deptId", deptId);
		/**
		 * @author 1페이지가 선택되어 있음을 보여주기 위함
		 */
		model.addAttribute("page", 1);

		return "/user/professorList";
	}

	/**
	 * 교수 조회
	 * 
	 * @param model
	 * @return 교수 조회 페이지
	 */
	@GetMapping("/professorList/{page}")
	public String showProfessorListByPage(Model model, @PathVariable(name = "page", required = false) Integer page,
			@RequestParam(name= "deptId" ,required = false) Integer deptId) {

		ProfessorListForm professorListForm = new ProfessorListForm();
		if (deptId != null) {
			professorListForm.setDeptId(deptId);
		}
		professorListForm.setPage((page - 1) * 20);
		Integer amount = professorService.readProfessorAmount(professorListForm);
		List<Professor> list = professorService.readProfessorList(professorListForm);

		model.addAttribute("listCount", Math.ceil(amount / 20.0));
		model.addAttribute("professorList", list);
		model.addAttribute("page", page);

		return "/user/professorList";
	}
	
	/**
	 * @return 교수 입력 페이지
	 */
	@GetMapping("/professor")
	public String createProfessor() {

		return "/user/createProfessor";
	}

	/**
	 * 교수등록처리
	 * 
	 * @param createProfessorDto
	 * @return "redirect:/user/professor"
	 */
	@PostMapping("/professor")
	public String createProfessorProc(@Valid CreateProfessorDto createProfessorDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		userService.createProfessorToProfessorAndUser(createProfessorDto);

		return "redirect:/user/professor";
	}
	
	
	
	/**
	 * @return student 입력 페이지
	 */
	@GetMapping("/student")
	public String createStudent() {

		return "/user/createStudent";
	}

	/**
	 * 학생 입력 post 처리
	 * 
	 * @param createStudentDto
	 * @return "redirect:/user/student"
	 */
	@PostMapping("/student")
	public String createStudentProc(@Valid CreateStudentDto createStudentDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error -> {
				sb.append(error.getDefaultMessage()).append("\\n");
			});
			throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
		}

		// 파일업로드 처리
		MultipartFile file = createStudentDto.getProfilImage();
		if (file.isEmpty() == false) {
			if (file.getSize() > Define.MAX_FILE_SIZE) {
				throw new CustomRestfullException("파일 크기는 20MB 이상 클 수 없습니다.", HttpStatus.BAD_REQUEST);
			}

			String saveDirectory = Define.UPLOAD_DIRECTORY;
			File dir = new File(saveDirectory);
			if (dir.exists()) {
				dir.mkdir();
			}

			// 파일이름
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			System.out.println("filename : " + fileName);

			String uploadPath = Define.UPLOAD_DIRECTORY + File.separator + fileName;
			File destination = new File(uploadPath);

			try {
				file.transferTo(destination);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			createStudentDto.setOriginFileName(file.getOriginalFilename());
			createStudentDto.setUploadFileName(fileName);
		}

		userService.createStudentToStudentAndUser(createStudentDto);

		return "redirect:/user/student";
	}
	
	
	/**
	 * 학생의 학년, 학기 업데이트
	 * 
	 * @return 학생 리스트 조회 페이지
	 */
	@GetMapping("/student/update")
	public String updateStudentGradeAndSemester() {
		studentService.updateStudentGradeAndSemester();
		return "redirect:/user/studentList";
	}
	


}
