package com.student.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.Department;

@Repository
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Application getApplicationById(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<Application> theQuery = currentSession.createQuery("from Application WHERE id = :id" , 
				Application.class);
		
		theQuery.setParameter("id", id);
		
		//return the results
		return theQuery.getSingleResult();
	}

	@Override
	public List<Application> getApplicationsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
