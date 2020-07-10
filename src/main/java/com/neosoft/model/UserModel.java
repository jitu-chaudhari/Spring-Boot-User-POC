package com.neosoft.model;

import java.util.Date;

import com.neosoft.entity.UserMasterEntity;

import lombok.Data;

/**
 * THIS IS USERENTITY Class
 * 
 * @author MANOJ BIRLA DATE 16-JUNE-2020
 */
@Data

public class UserModel {

	private Integer userid;
	
	private String firstName;

	private String lastName;

	private String email;

	private long mobileNo;

	private Date dob;

	private Date doj;

	private Date CreateDate;

	private Date updateDate;

	private boolean isActive = true;

	private UserMasterEntity userMasterEntity;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public UserMasterEntity getUserMasterEntity() {
		return userMasterEntity;
	}

	public void setUserMasterEntity(UserMasterEntity userMasterEntity) {
		this.userMasterEntity = userMasterEntity;
	}


}
