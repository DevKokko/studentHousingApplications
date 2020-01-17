package com.student.springdemo.service;

import java.util.List;

import com.student.springdemo.entity.DateRange;


public interface DateRangeService {
	
	public List<DateRange> getDateRange();
	
	public DateRange getDateRangeById(int theId);

	public void saveDateRange(DateRange theDateRange);

	

	public void deleteDateRange(int theId);
}
