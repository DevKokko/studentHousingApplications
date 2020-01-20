package com.student.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="applications")
public class Application implements Comparable<Application> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="student_id")
	private int student_id;
	
	@Column(name="student_income")
	private int student_income;
	
	@Column(name="family_income")
	private int family_income;
	
	@Column(name="unemployeed_parents")
	private int unemployeed_parents;
	
	@Column(name="studying_siblings")
	private int studying_siblings;
	
	@Column(name="is_from_another_city")
	private int is_from_another_city;
	
	@Column(name="score")
	private int score;
	
	@Column(name="approved")
	private int approved; //0 = pending, 1 = approved, -1 = rejected
	
	@Column(name="year")
	private int year;
	
	@Column(name="gotFreeHousing")
	private int gotFreeHousing;
	
	@Column(name="fileUrl")
	private String fileUrl;
	
	
	public int getGotFreeHousing() {
		return gotFreeHousing;
	}



	public void setGotFreeHousing(int gotFreeHousing) {
		this.gotFreeHousing = gotFreeHousing;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getStudent_id() {
		return student_id;
	}



	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}



	public int getStudent_income() {
		return student_income;
	}



	public void setStudent_income(int student_income) {
		this.student_income = student_income;
	}



	public int getFamily_income() {
		return family_income;
	}



	public void setFamily_income(int family_income) {
		this.family_income = family_income;
	}



	public int getUnemployeed_parents() {
		return unemployeed_parents;
	}



	public void setUnemployeed_parents(int unemployeed_parents) {
		this.unemployeed_parents = unemployeed_parents;
	}



	public int getStudying_siblings() {
		return studying_siblings;
	}



	public void setStudying_siblings(int studying_siblings) {
		this.studying_siblings = studying_siblings;
	}



	public int getIs_from_another_city() {
		return is_from_another_city;
	}



	public void setIs_from_another_city(int is_from_another_city) {
		this.is_from_another_city = is_from_another_city;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public int getApproved() {
		return approved;
	}



	public void setApproved(int approved) {
		this.approved = approved;
	}



	

	public String getFileUrl() {
		return fileUrl;
	}



	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	

	public Application(int id, int student_id, int student_income, int family_income, int unemployeed_parents,
			int studying_siblings, int is_from_another_city, int score, int approved, int year, int gotFreeHousing,
			String fileUrl) {
		this.id = id;
		this.student_id = student_id;
		this.student_income = student_income;
		this.family_income = family_income;
		this.unemployeed_parents = unemployeed_parents;
		this.studying_siblings = studying_siblings;
		this.is_from_another_city = is_from_another_city;
		this.score = score;
		this.approved = approved;
		this.year = year;
		this.gotFreeHousing = gotFreeHousing;
		this.fileUrl = fileUrl;
	}



	public Application(){}
	
	@Override
    public int compareTo(Application a) {
        return Integer.compare(this.getScore(), a.getScore());
    }
	
}
