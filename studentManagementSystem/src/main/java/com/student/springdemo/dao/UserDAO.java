package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.user.CrmUser;

public interface UserDAO {
	
	public List<CrmUser> getUsers();
	
	public CrmUser getUserByUsername(String username);

}
