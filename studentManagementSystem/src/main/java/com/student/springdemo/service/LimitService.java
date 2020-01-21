package com.student.springdemo.service;

import java.util.List;

import com.student.springdemo.entity.Limit;


public interface LimitService {
	
	public List<Limit> getLimit();
	
	public Limit getLimitByDepartmentId(int theId);
	public Limit getLimitById(int theId);

	public int saveLimit(Limit limit);

	public void updateLimit(Limit limit);

	public void deleteLimit(int theId);
}
