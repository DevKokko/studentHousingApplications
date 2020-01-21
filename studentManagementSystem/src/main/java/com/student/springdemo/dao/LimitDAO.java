package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Department;
import com.student.springdemo.entity.Limit;
import com.student.springdemo.entity.Student;

public interface LimitDAO {


	public List<Limit> getLimit();
	public Limit getLimitByDepartmentId(int theId);
	public Limit getLimitById(int theId);
	public int saveLimit(Limit limit);
	public void updateLimit(Limit limit);
	public void deleteLimit(int theId);
}