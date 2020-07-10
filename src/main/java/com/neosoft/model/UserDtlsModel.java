package com.neosoft.model;



import com.neosoft.entity.UserMasterEntity;

import lombok.Data;

@Data
public class UserDtlsModel {

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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserMasterEntity getUserMasterEntity() {
		return userMasterEntity;
	}
	public void setUserMasterEntity(UserMasterEntity userMasterEntity) {
		this.userMasterEntity = userMasterEntity;
	}
	private String firstName;
    private String lastName;
    private String email;
    private long mobileNo;
    private String message;
    private UserMasterEntity userMasterEntity;

}
