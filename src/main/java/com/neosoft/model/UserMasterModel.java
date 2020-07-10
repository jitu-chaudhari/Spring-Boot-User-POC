package com.neosoft.model;

import java.util.Date;
import java.util.Set;

import com.neosoft.entity.UserAddrsEntity;
import com.neosoft.entity.UserEducationEntity;

import lombok.Data;

@Data

public class UserMasterModel {
	
	private String userName;

	private String password;

	private Boolean isActive = true;

	private Date CreateDate;

	private Date updateDate;

	private Set<UserAddrsEntity> usersAddrs;

	private Set<UserEducationEntity> usersEducation;

	private Integer userMasterId;

	public Integer getUserMasterId() {
		return userMasterId;
	}

	public void setUserMasterId(Integer userMasterId) {
		this.userMasterId = userMasterId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public Set<UserAddrsEntity> getUsersAddrs() {
		return usersAddrs;
	}

	public void setUsersAddrs(Set<UserAddrsEntity> usersAddrs) {
		this.usersAddrs = usersAddrs;
	}

	public Set<UserEducationEntity> getUsersEducation() {
		return usersEducation;
	}

	public void setUsersEducation(Set<UserEducationEntity> usersEducation) {
		this.usersEducation = usersEducation;
	}

	

}
