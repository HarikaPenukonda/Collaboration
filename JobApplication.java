package com.niit.collaboration.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="C_JOB_APPLIED")
@Component
public class JobApplication extends BaseDomain {
	
	@Id
	private int id;
	
	@Column(name = "UserID")
	private String User_id;
	
	@Column(name = "JobID")
	private String Job_id;
	
	@Column(name = "date_applied")
	private Date dateApplied;
	private String remarks;
	private char status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getJob_id() {
		return Job_id;
	}
	public void setJob_id(String job_id) {
		Job_id = job_id;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
	
	

}
