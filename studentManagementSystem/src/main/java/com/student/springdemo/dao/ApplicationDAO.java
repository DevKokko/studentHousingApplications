package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.Department;

public interface ApplicationDAO {
	public Application getApplicationById(int id);
	public List<Application> getApplicationsByUsername(String username);
}
