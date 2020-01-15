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
import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.UserService;
import com.student.springdemo.service.DepartmentService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//need to inject our student service
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<CrmUser> theUsers = null;
		
		
		theUsers = userService.getUsers();
	
		//add the students to the model
		theModel.addAttribute("users" , theUsers);
						
		return "list-users";
		
	}
	
//	@GetMapping("/listByDep/{dep}")
//	public String listStudentsByDep(Model theModel, @PathVariable("dep") int dep) {
//		
//		// get student from the service
//		List<Student> theStudents = studentService.getStudentsByDep(dep);
//		
//		
//		//add the students to the model
//		theModel.addAttribute("students" , theStudents);
//				
//		return "list-students";
//		
//	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		CrmUser theUser = new CrmUser();
		
		theModel.addAttribute("username",theUser);
		theModel.addAttribute("isUpdate", "0");
		
		return "user-form";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("users") CrmUser theUser) {
		
		//save the student using our service
		
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode(theUser.getPassword());

        // prepend the encoding algorithm id
        theUser.setPassword("{bcrypt}" + encodedPassword);
		
        userService.saveUser(theUser);
		
		return "redirect:/user/list";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("users") CrmUser theUser) {
		
		if(theUser.getPassword().startsWith("{bcrypt}")) {
			
		}
		else {
			final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			String encodedPassword = passwordEncoder.encode(theUser.getPassword());

	        // prepend the encoding algorithm id
	        theUser.setPassword("{bcrypt}" + encodedPassword);
		}
		
		userService.saveUser(theUser);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("username") String username, Model theModel) {
		
		//get the student from  our service
		CrmUser theUser = userService.getUserByUsername(username);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("user",theUser);
		theModel.addAttribute("isUpdate", "1");
		
		//send over to our form
		return "user-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("username") String username) {
		
		//delete the student
		userService.deleteUser(userService.getUserByUsername(username));
		
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model theModel) {
								
		return "access-denied";
		
	}
	
}
