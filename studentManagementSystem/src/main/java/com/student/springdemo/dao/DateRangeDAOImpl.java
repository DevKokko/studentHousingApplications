package com.student.springdemo.dao;

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
import com.student.springdemo.entity.Student;

@Repository
@Transactional
public class DateRangeDAOImpl implements DateRangeDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DateRange getDateRangeById(int theid) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		
		Query<DateRange> theQuery = currentSession.createQuery("from DateRange WHERE id = :id" , 
				DateRange.class);
		
		theQuery.setParameter("id", theid);
		
		//return the results
		return theQuery.getSingleResult();
	}



	@Override
	public void saveDateRange(DateRange theId) {
		//get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//save/update the student
				currentSession.saveOrUpdate(theId);
	}

	@Override
	public void deleteDateRange(int theId) {
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//delete object with primary key
				Query theQuery = currentSession.createQuery("delete from DateRange where id=:dateRangeId");
				
				theQuery.setParameter("dateRangeId", theId);
				theQuery.executeUpdate();
		
	}



	@Override
	public List<DateRange> getDateRange() {

		
		
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//create a query ... sort by last name
				
				Query<DateRange> theQuery = currentSession.createQuery("from DateRange " , 
																	DateRange.class);
							
				//execute query and get result list
				List<DateRange> dateRange = theQuery.getResultList();
				
				//return the results
				return dateRange;
	}
}
