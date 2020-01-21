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
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Limit;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.DateRangeService;
import com.student.springdemo.service.DepartmentService;
import com.student.springdemo.service.LimitService;

@Controller
@RequestMapping("/limit")
public class LimitController {
	
	//need to inject our student service
	@Autowired
	private LimitService limitService;
	

	
	@GetMapping("/list")
	public String listLimit(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<Limit> theLimit = null;
		
		
		theLimit = limitService.getLimit();
		//add the students to the model
		theModel.addAttribute("limit" , theLimit);
						
		return "list-limit";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Limit theLimit = new Limit();
		
		theModel.addAttribute("applicationLimit",theLimit);
		theModel.addAttribute("isUpdate", "0");
		
		return "limit-form";
	}

	
	
	
	


	@PostMapping("/saveLimit")
	public String saveStudent(@ModelAttribute("applicationLimit") Limit theLimit) {
		
	
		
		limitService.saveLimit(theLimit);
		
		return "redirect:/limit/list";
	}
	

	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("limitId") int theId, Model theModel) {
		
		//get the student from  our service
		Limit theLimit = limitService.getLimitById(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("applicationLimit",theLimit);
		theModel.addAttribute("isUpdate", "1");
		
		//send over to our form
		return "limit-form";
	}
	
	@GetMapping("/delete")
	public String deleteLimit(@RequestParam("limitId") int theId) {
		
		//delete the student
		limitService.deleteLimit(theId);
		
		
		return "redirect:/limit/list";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model theModel) {
								
		return "access-denied";
		
	}
	
}
