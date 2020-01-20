package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.DepartmentDAO;
import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	//need to inject student dao
	@Autowired
	private DepartmentDAO departmentDAO;
	
	
	@Override
	@Transactional
	public Department getUserByUsername(String username) {
		return departmentDAO.getUserByUsername(username);
	}
	
	@Override
	@Transactional
	public void deleteByUsername(String username) {
		departmentDAO.deleteByUsername(username);
	}
}
