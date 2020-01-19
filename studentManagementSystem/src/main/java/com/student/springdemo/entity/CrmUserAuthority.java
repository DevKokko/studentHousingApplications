package com.student.springdemo.entity;

public class CrmUserAuthority {
	String authority;
	String username;
	String password;
	int enabled;
	public CrmUserAuthority(String authority, String username, String password, int enabled) {
		this.authority = authority;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	public CrmUserAuthority() {
		// TODO Auto-generated constructor stub
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
}
