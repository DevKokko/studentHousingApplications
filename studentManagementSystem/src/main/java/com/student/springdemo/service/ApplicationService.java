package com.student.springdemo.service;

import java.util.List;

import com.student.springdemo.entity.Application;

public interface ApplicationService {
	public Application getApplicationById(int id);
	public List<Application> getApplicationsByUsername(String username);
}
