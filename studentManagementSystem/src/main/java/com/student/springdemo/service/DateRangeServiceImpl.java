package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.ApplicationDAO;
import com.student.springdemo.dao.DateRangeDAO;
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Student;

@Service
public class DateRangeServiceImpl implements DateRangeService{
	@Autowired
	private DateRangeDAO dateRangeDAO;
	
	
	
	@Override
	@Transactional
	public List<DateRange> getDateRange() {
		return dateRangeDAO.getDateRange();
		
	}
	@Override
	@Transactional
	public DateRange getDateRangeById(int theId) {
		return dateRangeDAO.getDateRangeById(theId);
		
	}

	@Override
	@Transactional
	public void saveDateRange(DateRange theId) {
		dateRangeDAO.saveDateRange(theId);
		
	}


	@Override
	@Transactional
	public void deleteDateRange(int theId) {
		dateRangeDAO.deleteDateRange(theId);
		
	}


}
