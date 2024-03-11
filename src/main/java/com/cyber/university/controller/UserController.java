package com.cyber.university.controller;

import com.cyber.university.dto.StudentListForm;


import com.cyber.university.repository.model.Student;
import com.cyber.university.service.StudentService;
import com.cyber.university.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * @return staff 입력 페이지
     */
    @GetMapping("/staff")
    public String createStaff() {

        return "/user/createStaff";
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


}
