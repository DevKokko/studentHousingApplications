package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.ApplicationDAO;
import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.Student;

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
	public List<Application> getApplications(){
		return applicationDAO.getApplications();
	}
	
	@Override
	@Transactional
	public List getApplicationsByDep(int dep){
		return applicationDAO.getApplicationsByDep(dep);
	}

	@Override
	@Transactional
	public void saveApplication(Application theId) {
		applicationDAO.saveApplication(theId);
		
	}

	@Override
	@Transactional
	public Application getApplication(int theId) {
		return applicationDAO.getApplication(theId);
	}

	@Override
	@Transactional
	public void deleteApplication(int theId) {
		applicationDAO.deleteApplication(theId);
		
	}
	
	@Override
	@Transactional
	public int howManyYearsFreeHousing(int theId) {
		return applicationDAO.howManyYearsFreeHousing(theId);
	}
}
