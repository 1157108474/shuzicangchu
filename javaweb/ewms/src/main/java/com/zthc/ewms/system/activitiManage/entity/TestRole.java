package com.zthc.ewms.system.activitiManage.entity;

import java.io.Serializable;

public class TestRole implements Serializable{
	
	private String id;
	
	private String name;
	

	public TestRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestRole(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
