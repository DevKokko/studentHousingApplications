package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.DateRange;
import com.student.springdemo.entity.Department;
import com.student.springdemo.entity.Student;

public interface DateRangeDAO {


	public List<DateRange> getDateRange();
	public DateRange getDateRangeById(int theId);
	public void saveDateRange(DateRange theId);
	public void deleteDateRange(int theId);
}
