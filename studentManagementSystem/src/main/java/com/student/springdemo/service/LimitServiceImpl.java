package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.ApplicationDAO;
import com.student.springdemo.dao.DateRangeDAO;
import com.student.springdemo.dao.LimitDAO;
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Limit;
import com.student.springdemo.entity.Student;

@Service
public class LimitServiceImpl implements LimitService{
	@Autowired
	private LimitDAO limitDAO;
	
	
	
	@Override
	@Transactional
	public List<Limit> getLimit() {
		return limitDAO.getLimit();
		
	}
	@Override
	@Transactional
	public Limit getLimitByDepartmentId(int theId) {
		return limitDAO.getLimitByDepartmentId(theId);
		
	}
	@Override
	@Transactional
	public Limit getLimitById(int theId) {
		return limitDAO.getLimitById(theId);
		
	}

	@Override
	@Transactional
	public int saveLimit(Limit limit) {
		return limitDAO.saveLimit(limit);
	}
	@Override
	@Transactional
	public void updateLimit(Limit limit) {
		limitDAO.updateLimit(limit);
	}


	@Override
	@Transactional
	public void deleteLimit(int theId) {
		limitDAO.deleteLimit(theId);
		
	}
	
	


}