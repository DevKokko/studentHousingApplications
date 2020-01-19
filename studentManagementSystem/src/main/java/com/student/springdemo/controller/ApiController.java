package com.student.springdemo.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.springdemo.dao.DepartmentDAO;
import com.student.springdemo.dao.UserDAO;
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.ApplicationService;
import com.student.springdemo.service.LimitService;
import com.student.springdemo.service.StudentService;
import com.student.springdemo.service.UserService;

@RestController
@Transactional
public class ApiController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	LimitService limitService;
	
	@Autowired
	ApplicationService applicationService;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
						
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public @ResponseBody String checkCredentials(HttpServletRequest req, @RequestParam("username") String username, @RequestParam String password) throws FileNotFoundException, IOException {
		//Check credentials

		Student student = studentService.getStudentByUsername(username);
		
		if (student != null){
        	final BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        	String dbPassword = (student.getPassword());
        	dbPassword =  dbPassword.substring(8,dbPassword.length());
            if (pwEncoder.matches(password, dbPassword)) {
            	if(student.getEnabled() == 1) {
    				return "1";
    			}
                return "-1";
            }
            else{
                return "0";
            }
        }
        else {
        	return "0";
        }
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/submitApplication", method = RequestMethod.POST)
	public @ResponseBody String submitApplication(HttpServletRequest req, @RequestParam("username") String username, @RequestParam("studentIncome") int studentIncome, @RequestParam("parents") int parents, @RequestParam("familyIncome") int familyIncome, @RequestParam("siblings") int siblings, @RequestParam("fromAnotherCity") int fromAnotherCity, @RequestParam("years") int years, @RequestParam("year") int year)  {

		try {
			Session session = sessionFactory.getCurrentSession();
			
			Student student = studentService.getStudentByUsername(username);
			
			Application application = new Application(0, student.getId(), studentIncome, familyIncome, parents, siblings, fromAnotherCity, 0, 0, year);
			
			session.save(application);
		}
		catch(Exception e) {
			return "0";
		}
		return "1";
	}
	
	@RequestMapping(value = "/api/generateStudents", method = RequestMethod.GET)
	public @ResponseBody String generateStudents() throws FileNotFoundException, IOException {
		
		if(1==1)
			return "Students already generated";
		
		String output = "";
		String[] names = {"Johnnie Dejarnette","Keiko Delawder","Drucilla Nottingham","Julieann Neumann","Madlyn Merlin","Chong Duet","Brooks Sitz","Kathline Shiflet","Devorah Clinkscales","Alba Hinnant","Dante Hynd","Elicia Bennetts","Leandro Fenstermaker","Shea Niblett","Morton Mathers","Lynne Clover","Julie Mcnaught","Jamee Westlake","Tyrone Choice","Barb Averitt","Neva Kreidler","Dwayne Vizcarra","Erna Ludlum","Therese Shellman","Tonette Dunworth","Marlen Mccloskey","Suellen Peltz","Birgit Zamzow","Ta Dube","January Dodrill","Vickey Holdridge","Kaleigh Calandra","Elisa Hohlt","Jo Wilburn","Raisa Vanderzee","Rosann Buras","Richie Wyman","Tish Valois","Yoko Lisk","Neida Nappi","Karine Lombardi","Fonda Pulliam","Marni Alatorre","Brittanie Carstens","Nelson Sabin","Jeanmarie Corella","Erika Spieker","Emelina Lavalle","Elenor Gudino","Angella White"};

		Session session = sessionFactory.getCurrentSession();
		
		for(int i = 0; i<names.length; i++){
			String firstName = names[i].split(" ")[0];
			String lastName = names[i].split(" ")[1];
			String email = names[i].replace(" ", "").toLowerCase() + "@hua.gr";
			
			String numbers = "0123456789";
			String phoneNumber = "69";
			int length = 8;
			
			for (int j = 0, n = numbers.length(); j < length; ++j) {
				phoneNumber += numbers.charAt((int)Math.floor(Math.random() * n));
		    }
			
			int department = ((int)(Math.random()*((3-1)+1))+1);
			int registrationYear = (int)((Math.random()*((2019-2014)+1))+2014);
			int semester = (registrationYear-2014+1)*2;
			int enabled = 1;
			String username = names[i].replace(" ", "").toLowerCase();
			
			String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()_+-=[]?";
			String password = "";
			length = 12;
			
			for (int j = 0, n = chars.length(); j < length; ++j) {
				password += chars.charAt((int)Math.floor(Math.random() * n));
		    }
			
			String encodedPassword = passwordEncoder.encode(password);

	        // prepend the encoding algorithm id
	        password = "{bcrypt}" + encodedPassword;
	        
	        Student newStudent = new Student(0, firstName, lastName, email, phoneNumber, department, registrationYear, semester, username, password, enabled);
	        
	        session.save(newStudent);
	        
		  output += firstName +  ", " + lastName +  ", " + email +  ", " + phoneNumber +  ", " + department +  ", " + registrationYear +  ", " + 
				  semester +  ", " + enabled +  ", " + username +  ", " + password + "<br>";
		}
		
		return output;
	}
	
	@RequestMapping(value = "/api/getStatus", method = RequestMethod.POST)
	public @ResponseBody String getStatus(@RequestParam("username") String username) throws FileNotFoundException, IOException {
		
		Student student = studentService.getStudentByUsername(username);
		int student_id = student.getId();
		
		List<Application> applications = applicationService.getApplicationsByDep(student.getDepartment());
		Collections.sort(applications, Collections.reverseOrder());
		
		int rank = 0;
		int approved = 0;
		boolean found = false;
		
		for(int i = 0; i<applications.size(); i++) {
			if(applications.get(i).getApproved() == 1) {
				rank++;
			}
			if(applications.get(i).getStudent_id() == student_id) {
				approved = applications.get(i).getApproved();
				found = true;
				break;
			}
		}
		if(!found || approved == -1) {
			rank = 0;
		}
		
		
		int getsHousing = 0;
		if(rank <= limitService.getLimitByDepartmentId(student.getDepartment()).getApplication_limit() && rank>0) {
			getsHousing = 1;
		}
		String data = "{\"rank\":\""+rank+"\",\"approved\":\""+approved+"\",\"getsHousing\":\""+getsHousing+"\"}";
		
		return data;
	}
	
}
 