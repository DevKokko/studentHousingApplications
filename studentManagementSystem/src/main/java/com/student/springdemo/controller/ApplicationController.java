package com.student.springdemo.controller;

import java.util.ArrayList;
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
	private StudentService studentService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/list")
	public String listApplications(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List theApplications = null;
		List<Student> theStudents = null;
		
		List<Application> finalApplications = new ArrayList<Application>();
		
	/*	if(departmentService.getUserByUsername(username).getDepartment() != 0) {
			theApplications = applicationService.getApplicationsByDep(departmentService.getUserByUsername(username).getDepartment());
		}
		else {
			theApplications = applicationService.getApplications();
		}*/
		
		theApplications = applicationService.getApplications();
		
		theStudents = studentService.getStudents();
		
		for(int i = 0; i<theApplications.size(); i++) {
			if((((Student)findStudentById(theStudents,((Application)theApplications.get(i)).getStudent_id())).getDepartment() == departmentService.getUserByUsername(username).getDepartment()) || departmentService.getUserByUsername(username).getDepartment() == 0){
				if(((Application)theApplications.get(i)).getGotFreeHousing() == 0)
					finalApplications.add((Application)theApplications.get(i));
			}
		}
		
		//add the students to the model
		theModel.addAttribute("applications" , finalApplications);
		theModel.addAttribute("students" , theStudents);
						
		return "list-applications";
		
	}
	
	private Student findStudentById(List<Student> students, int id) {
		for(int j = 0; j<students.size(); j++) {
			if(((Student)students.get(j)).getId() == id){
				return ((Student)students.get(j));
			}
		}
		return null;
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
	
	@GetMapping("/approve")
	public String approveApplication(@RequestParam("applicationId") int theId) {
				
		Application application = applicationService.getApplication(theId);
		
		application.setApproved(1);
		application.setScore(CalculateScore(application));
		
		applicationService.saveApplication(application);
		
		return "redirect:/application/list";
	}
	
	private int CalculateScore(Application application) {
		
		int score = 0;
				
		if(application.getFamily_income() < 10000){
			score+=100;
		}
		else if(application.getFamily_income() < 15000){
			score+=30;
		}
		
		score += application.getStudying_siblings()*20;
		
		if(application.getIs_from_another_city() == 1){
			score+=50;
		}
		
		score -= applicationService.howManyYearsFreeHousing(application.getStudent_id())*10;
							
		if(application.getStudent_income()==1 && application.getUnemployeed_parents()==1){
			score = 10000000;
		}
		if(studentService.getStudent(application.getStudent_id()).getSemester()>8){
			score = -10000000;
		}
		
		return score;
	}
	
	@GetMapping("/reject")
	public String rejectApplication(@RequestParam("applicationId") int theId) {
		
		Application application = applicationService.getApplication(theId);
		
		application.setApproved(-1);
		application.setScore(0);
		
		applicationService.saveApplication(application);
		
		
		return "redirect:/application/list";
	}
	
	@GetMapping("/pending")
	public String pendingApplication(@RequestParam("applicationId") int theId) {
		
		Application application = applicationService.getApplication(theId);
		
		application.setApproved(0);
		application.setScore(0);
		
		applicationService.saveApplication(application);
		
		
		return "redirect:/application/list";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model theModel) {
								
		return "access-denied";
		
	}
	
}


