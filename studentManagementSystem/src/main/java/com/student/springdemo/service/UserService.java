package com.student.springdemo.service;

import java.util.List;

import com.student.springdemo.user.CrmUser;

public interface UserService {
	
	public List<CrmUser> getUsers();
	
	public CrmUser getUserByUsername(String username);
}
