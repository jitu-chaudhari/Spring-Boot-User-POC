package com.neosoft.entity;

import lombok.Data;

@Data
public class UserPhoneNoOnlyUpdate {

	private long mobileNo;

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
}
