package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.entity.Department;

public interface DepartmentDAO {
	public Department getUserByUsername(String username);
	public void deleteByUsername(String username);
}
