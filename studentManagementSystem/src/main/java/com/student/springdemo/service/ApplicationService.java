package com.student.springdemo.service;

import java.util.List;

import com.student.springdemo.entity.Application;
import com.student.springdemo.entity.Student;

public interface ApplicationService {
	public Application getApplicationByStudentId(int id);
	//public List<Application> getApplicationsById(String id);
	

	public void saveApplication(Application theId);

	public Application getApplication(int theId);

	public void deleteApplication(int theId);
	public List<Application> getApplications();
	public List getApplicationsByDep(int dep);
	public int howManyYearsFreeHousing(int theId);
	public String fileUrl(String url);
}
