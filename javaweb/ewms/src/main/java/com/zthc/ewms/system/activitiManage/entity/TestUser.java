package com.zthc.ewms.system.activitiManage.entity;

import java.io.Serializable;

public class TestUser implements Serializable{
	
	private String id;
	private String userName;
	private String employeeCode;
	
	
	
	public TestUser(String id, String userName, String employeeCode) {
		super();
		this.id = id;
		this.userName = userName;
		this.employeeCode = employeeCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	
	
	
	
}
