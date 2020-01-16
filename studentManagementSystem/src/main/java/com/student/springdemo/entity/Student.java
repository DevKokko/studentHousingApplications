package com.student.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="department")
	private int department;
	
	@Column(name="registration_year")
	private int registrationYear;
	
	@Column(name="semester")
	private int semester;
		
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled = 0;	
	

	public Student() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(int registrationYear) {
		this.registrationYear = registrationYear;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
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
	
	

	public Student(int id, String firstName, String lastName, String email, String phoneNumber, int department,
			int registrationYear, int semester, String username, String password, int enabled) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.registrationYear = registrationYear;
		this.semester = semester;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", department=" + department + ", registrationYear="
				+ registrationYear + ", semester=" + semester + "]";
	}
	

}
