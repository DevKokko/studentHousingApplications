package com.student.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Student;
import com.student.springdemo.service.DepartmentService;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private DepartmentService departmentService;

	@Override
	public List<CrmUser> getUsers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<CrmUser> theQuery = currentSession.createQuery("from CrmUser order by username " , 
															CrmUser.class);
					
		//execute query and get result list
		List<CrmUser> users = theQuery.getResultList();
		
		//return the results
		return users;
	}
	
	@Override
	public CrmUser getUserByUsername(String username) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<CrmUser> theQuery = currentSession.createQuery("from CrmUser WHERE username =:u order by username" , 
															CrmUser.class);
		
		theQuery.setParameter("u", username);
			
		CrmUser user = null;
		
		//execute query and get result list
		if(theQuery.getResultList().size() != 0)
			user = theQuery.getSingleResult();
		
		//return the results
		return user;
	}

	@Override
	public void saveUser(CrmUser theUser) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update the student
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void deleteUser(CrmUser user) {
		
	//	Session currentSession = sessionFactory.getCurrentSession();
				
		userDetailsManager.deleteUser(user.getUsername());
		departmentService.deleteByUsername(user.getUsername());
		
		//save/update the student
		//currentSession.delete(user);
		
		//get the current hibernate session
			/*	Session currentSession = sessionFactory.getCurrentSession();
				
				//delete object with primary key
				Query theQuery = currentSession.createQuery("delete from CrmUser where username=:username");
				
				theQuery.setParameter("username", username);
				theQuery.executeUpdate();*/
		
	}

}
