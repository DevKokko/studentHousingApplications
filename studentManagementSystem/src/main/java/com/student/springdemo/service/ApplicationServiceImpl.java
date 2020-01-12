package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.ApplicationDAO;
import com.student.springdemo.entity.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	@Autowired
	private ApplicationDAO applicationDAO;
	
	
	@Override
	@Transactional
	public Application getApplicationById(int id) {
		return applicationDAO.getApplicationById(id);
	}
	
	@Override
	@Transactional
	public List<Application> getApplicationsByUsername(String username){
		return applicationDAO.getApplicationsByUsername(username);
	}
}
