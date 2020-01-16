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
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.ApplicationService;
import com.student.springdemo.service.DepartmentService;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	//need to inject our student service
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/list")
	public String listApplications(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Application> theApplications = null;
		
		
		theApplications = applicationService.getApplicationByUsername(username);
		
		//add the students to the model
		theModel.addAttribute("applications" , theApplications);
						
		return "list-applications";
		
	}
	
	

	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Application theApplication = new Application();
		
		theModel.addAttribute("applications",theApplication);
		theModel.addAttribute("isUpdate", "0");
		
		return "application-form";
	}

	@PostMapping("/saveApplication")
	public String saveApplication(@ModelAttribute("applications") Application theApplication) {
//		
//		//save the student using our service
//		
//		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		
//		String encodedPassword = passwordEncoder.encode(theApplication.getPassword());
//
//        // prepend the encoding algorithm id
//        theApplication.setPassword("{bcrypt}" + encodedPassword);
		
		applicationService.saveApplication(theApplication);
		
		return "redirect:/application/list";
	}
	
	@PostMapping("/updateApplication")
	public String updateStudent(@ModelAttribute("applications") Application theApplication) {
		
		
		applicationService.saveApplication(theApplication);
		
		return "redirect:/application/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("applicationId") int theId, Model theModel) {
		
		//get the student from  our service
		Application theApplication = applicationService.getApplication(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("applications",theApplication);
		theModel.addAttribute("isUpdate", "1");
		
		//send over to our form
		return "application-form";
	}
	
	@GetMapping("/delete")
	public String deleteApplication(@RequestParam("applicationId") int theId) {
		
		//delete the student
		applicationService.deleteApplication(theId);
		
		
		return "redirect:/application/list";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model theModel) {
								
		return "access-denied";
		
	}
	
}


