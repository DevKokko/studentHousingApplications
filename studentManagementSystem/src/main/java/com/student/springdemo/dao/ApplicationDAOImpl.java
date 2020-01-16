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
import com.student.springdemo.entity.Student;

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
	public List<Application> getApplications() {

		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
		
				
				Query<Application> theQuery = currentSession.createQuery("from Application " , 
																	Application.class);
				
					
				//execute query and get result list
				List<Application> applications = theQuery.getResultList();
				
				//return the results
				return applications;
	}
	
	@Override
	public List getApplicationsByDep(int dep) {

		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
		
				Query theQuery = currentSession.createQuery("from Application");
				
					
				//execute query and get result list
				List applications = theQuery.getResultList();
				
				//return the results
				return applications;
	}

	@Override
	public void saveApplication(Application theId) {
		//get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//save/update the student
				currentSession.saveOrUpdate(theId);
				
		
	}

	@Override
	public Application getApplication(int theId) {
		//get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from database using the primary key
		Application theApplication = currentSession.get(Application.class, theId);
		
		return theApplication;
	}
	
	@Override
	public int howManyYearsFreeHousing(int theId) {
		//get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from database using the primary key
		Query<Application> theQuery = currentSession.createQuery("from Application WHERE student_id = :student_id AND gotFreeHousing=1" , 
				Application.class);
		
		theQuery.setParameter("student_id", theId);
		
		//return the results
		return theQuery.getResultList().size();
	}

	@Override
	public void deleteApplication(int theId) {
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//delete object with primary key
				Query theQuery = currentSession.createQuery("delete from Application where id=:applicationId");
				
				theQuery.setParameter("applicationId", theId);
				theQuery.executeUpdate();
		
	}
}
