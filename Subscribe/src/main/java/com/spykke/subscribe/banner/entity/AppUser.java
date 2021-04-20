package com.spykke.subscribe.banner.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "tb_app_user")
public class AppUser {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String nickname;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private int sex;
	
	@Column
	private Date birthday;
	
	@Column
	private Integer leaseFrequency;
	
	@Transient
	private String ageGroup;
	
	@Transient
	private String gender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAgeGroup() {
		Date today = new Date();
		try {
		long age = today.getYear()-birthday.getYear();
		if(age < 18) {
			return "Below 18";
		}
		if(age > 30) {
			return "Below 18";
		}
		return "18 to 30";
		}catch (Exception e) {
			return "";
		}
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getGender() {
		switch (this.sex) {
		case 0:
			gender = "Female";
			break;
		case 1:
			gender = "Male";
			break;
		case 2:
			gender = "Others";
			break;
		case 3:
			gender = "Not Set";
			break;
		default:
			gender = "";
			break;
		}
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
