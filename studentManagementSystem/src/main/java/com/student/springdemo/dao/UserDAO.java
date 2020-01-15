package com.student.springdemo.dao;

import java.util.List;

import com.student.springdemo.entity.CrmUser;

public interface UserDAO {
	
	public List<CrmUser> getUsers();
	
	public CrmUser getUserByUsername(String username);

	public void saveUser(CrmUser theUser);

	public void deleteUser(CrmUser theUser);

}
