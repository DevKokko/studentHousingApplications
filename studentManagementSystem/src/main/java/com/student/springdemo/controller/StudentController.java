package com.student.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.springdemo.dao.StudentDAO;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.DepartmentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	//need to inject our student service
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Student> theStudents = null;
		
		if(departmentService.getUserByUsername(username).getDepartment() != 0) {
			theStudents = studentService.getStudentsByDep(departmentService.getUserByUsername(username).getDepartment());
		}
		else {
			theStudents = studentService.getStudents();
		}
		//add the students to the model
		theModel.addAttribute("students" , theStudents);
						
		return "list-students";
		
	}
	
	@GetMapping("/listByDep/{dep}")
	public String listStudentsByDep(Model theModel, @PathVariable("dep") int dep) {
		
		// get student from the service
		List<Student> theStudents = studentService.getStudentsByDep(dep);
		
		
		//add the students to the model
		theModel.addAttribute("students" , theStudents);
				
		return "list-students";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Student theStudent = new Student();
		
		theModel.addAttribute("student",theStudent);
		theModel.addAttribute("isUpdate", "0");
		
		return "student-form";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		
		//save the student using our service
		
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode(theStudent.getPassword());

        // prepend the encoding algorithm id
        theStudent.setPassword("{bcrypt}" + encodedPassword);
		
		studentService.saveStudent(theStudent);
		
		return "redirect:/student/list";
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("student") Student theStudent) {
		
		if(theStudent.getPassword().startsWith("{bcrypt}")) {
			
		}
		else {
			final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			String encodedPassword = passwordEncoder.encode(theStudent.getPassword());

	        // prepend the encoding algorithm id
	        theStudent.setPassword("{bcrypt}" + encodedPassword);
		}
		
		studentService.saveStudent(theStudent);
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		//get the student from  our service
		Student theStudent = studentService.getStudent(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("student",theStudent);
		theModel.addAttribute("isUpdate", "1");
		
		//send over to our form
		return "student-form";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int theId) {
		
		//delete the student
		studentService.deleteStudent(theId);
		
		
		return "redirect:/student/list";
	}
	
}
