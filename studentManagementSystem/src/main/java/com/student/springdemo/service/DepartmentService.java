package com.student.springdemo.service;

import com.student.springdemo.entity.CrmUser;
import com.student.springdemo.entity.Department;

public interface DepartmentService {
	public Department getUserByUsername(String username);
}
