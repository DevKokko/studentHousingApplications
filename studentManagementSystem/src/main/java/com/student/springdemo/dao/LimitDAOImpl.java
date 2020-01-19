package com.student.springdemo.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Department;
import com.student.springdemo.entity.Limit;
import com.student.springdemo.entity.Student;

@Repository
@Transactional
public class LimitDAOImpl implements LimitDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Limit getLimitByDepartmentId(int theid) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<Limit> theQuery = currentSession.createQuery("from Limit WHERE department_id = :id AND year = :year" , 
				Limit.class);
		
		theQuery.setParameter("year", Calendar.getInstance().get(Calendar.YEAR));
		
		theQuery.setParameter("id", theid);
		
		//return the results
		return theQuery.getSingleResult();
	}

	@Override
	public Limit getLimitById(int theid) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<Limit> theQuery = currentSession.createQuery("from Limit WHERE id = :id" , 
				Limit.class);
		
		
		theQuery.setParameter("id", theid);
		
		//return the results
		return theQuery.getSingleResult();
	}


	@Override
	public void saveLimit(Limit theId) {
		//get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//save/update the student
				currentSession.saveOrUpdate(theId);
	}

	@Override
	public void deleteLimit(int theId) {
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//delete object with primary key
				Query theQuery = currentSession.createQuery("delete from Limit where id=:limitId");
				
				theQuery.setParameter("limitId", theId);
				theQuery.executeUpdate();
		
	}



	@Override
	public List<Limit> getLimit() {

		
		
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//create a query ... sort by last name
				
				Query<Limit> theQuery = currentSession.createQuery("from Limit " , 
																	Limit.class);
							
				//execute query and get result list
				List<Limit> limit = theQuery.getResultList();
				
				//return the results
				return limit;
	}



	

	
}