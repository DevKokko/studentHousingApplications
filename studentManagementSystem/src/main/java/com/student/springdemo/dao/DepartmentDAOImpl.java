package com.student.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.entity.Student;
import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Department;

@Repository
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Department getUserByUsername(String username) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<Department> theQuery = currentSession.createQuery("from Department WHERE username = :username" , 
				Department.class);
		
		theQuery.setParameter("username", username);
		
		//return the results
		return theQuery.getSingleResult();
	}
	
	@Override
	public void deleteByUsername(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Department where username=:username");
		
		theQuery.setParameter("username", username);
		theQuery.executeUpdate();
	}
}
