package com.student.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.springdemo.dao.UserDAO;
import com.student.springdemo.entity.CrmUser;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	@Transactional
	public CrmUser getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	@Override
	@Transactional
	public List<CrmUser> getUsers(){
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(CrmUser theUser) {
		userDAO.saveUser(theUser);
		
	}

	@Override
	@Transactional
	public void deleteUser(CrmUser theUser) {
		userDAO.deleteUser(theUser);
		
	}
}
