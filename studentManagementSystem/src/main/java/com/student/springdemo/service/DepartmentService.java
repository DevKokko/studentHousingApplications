package com.student.springdemo.service;

import com.student.springdemo.entity.Department;
import com.student.springdemo.user.CrmUser;

public interface DepartmentService {
	public Department getUserByUsername(String username);
}
