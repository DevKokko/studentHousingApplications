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
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.DateRangeService;
import com.student.springdemo.service.DepartmentService;

@Controller
@RequestMapping("/daterange")
public class DateRangeController {
	
	//need to inject our student service
	@Autowired
	private DateRangeService dateRangeService;
	

	
	@GetMapping("/list")
	public String listDateRange(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<DateRange> theDateRange = null;
		
		
		theDateRange = dateRangeService.getDateRange();
		//add the students to the model
		theModel.addAttribute("applicationDateRange" , theDateRange);
						
		return "list-daterange";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		DateRange theDateRange = new DateRange();
		
		theModel.addAttribute("applicationDateRange",theDateRange);
		theModel.addAttribute("isUpdate", "0");
		
		return "daterange-form";
	}

	
	


	@PostMapping("/saveDateRange")
	public String saveDateRange(@ModelAttribute("applicationDateRange") DateRange theDateRange) {
		dateRangeService.saveDateRange(theDateRange);
		
		return "redirect:/daterange/list";
	}
	
	

	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("dateRangeId") int theId, Model theModel) {
		
		//get the student from  our service
		DateRange theDateRange = dateRangeService.getDateRangeById(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("applicationDateRange",theDateRange);
		theModel.addAttribute("isUpdate", "1");
		
		//send over to our form
		return "daterange-form";
	}
	
	@GetMapping("/delete")
	public String deleteDateRange(@RequestParam("dateRangeId") int theId) {
		
		//delete the student
		dateRangeService.deleteDateRange(theId);
		
		
		return "redirect:/daterange/list";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model theModel) {
								
		return "access-denied";
		
	}
	
}


