package com.student.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="applicationLimit")
public class Limit{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="department_id")
	private int department_id;
	
	@Column(name="application_limit")
	private int application_limit;
	
	@Column(name="year")
	private int year;

	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDepartment_id() {
		return department_id;
	}


	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}


	public int getApplication_limit() {
		return application_limit;
	}


	public void setApplication_limit(int application_limit) {
		this.application_limit = application_limit;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	
	
	
	public Limit(int id, int department_id, int application_limit, int year) {
		super();
		this.id = id;
		this.department_id = department_id;
		this.application_limit = application_limit;
		this.year = year;
	}


	public Limit(){}
	
}