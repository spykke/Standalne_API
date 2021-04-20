package com.spykke.internal.commondb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity(name="sys_role")
public class Role {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="role_name")
	@OrderColumn(name="role_name")
	private String name;
	
	@Column
	private Boolean isDeleted; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
