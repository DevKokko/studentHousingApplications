package com.student.springdemo.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.springdemo.dao.StudentDAO;
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.CrmUserAuthority;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.UserService;
import com.student.springdemo.service.DepartmentService;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	//need to inject our student service
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
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
		
		theModel.addAttribute("user",theUser);
		theModel.addAttribute("isUpdate", "0");
		
		return "user-form";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") CrmUser theCrmUser, 
			@RequestParam("authority") String authority,
			BindingResult theBindingResult, 
			Model theModel) {
		
		//save the student using our service
		
		CrmUser findUser = userService.getUserByUsername(theCrmUser.getUsername());
		if(findUser != null) {
			return "redirect:/user/list?alreadyExists=1";
		}
		
		final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());

        // prepend the encoding algorithm id
        //theUser.setPassword("{bcrypt}" + encodedPassword);
        
       // CrmUser crmUser = new CrmUser(theCrmUser.getUsername(), "{bcrypt}" + encodedPassword, theCrmUser.getEnabled());
		
       // userService.saveUser(crmUser);
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authority);

        // create user object (from Spring Security framework)
        User tempUser = new User(theCrmUser.getUsername(), "{bcrypt}" + encodedPassword, theCrmUser.getEnabled()==1?true:false, true, true, true, authorities);

        // save user in the database
        userDetailsManager.createUser(tempUser);
        
		
		return "redirect:/user/list";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("user") CrmUser theUser, 
			@RequestParam("authority") String authority,
			BindingResult theBindingResult, 
			Model theModel) {
				
		//theUser = userService.getUserByUsername(theUser.getUsername());
		
		if(theUser.getPassword().startsWith("{bcrypt}")) {
			
		}
		else {
			final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			String encodedPassword = passwordEncoder.encode(theUser.getPassword());

	        // prepend the encoding algorithm id
			theUser.setPassword("{bcrypt}" + encodedPassword);
		}
		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authority);

        // create user object (from Spring Security framework)
        User tempUser = new User(theUser.getUsername(), theUser.getPassword(), theUser.getEnabled()==1?true:false, true, true, true, authorities);

        // save user in the database
        userDetailsManager.updateUser(tempUser);

		
		//userService.saveUser(theUser);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("username") String username, Model theModel) {
		
		//get the student from  our service
		CrmUser theUser = userService.getUserByUsername(username);
		
		Session currentSession = sessionFactory.getCurrentSession();
						
		UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
				
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("user",theUser);
		theModel.addAttribute("authority",userDetails.getAuthorities().toArray()[0].toString());
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
