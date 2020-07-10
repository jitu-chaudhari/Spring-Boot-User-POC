package com.neosoft.model;

import java.util.Date;


import lombok.Data;


@Data

public class UserAddrsModel {

	private Integer userAddrsId;

	public Integer getUserAddrsId() {
		return userAddrsId;
	}

	public void setUserAddrsId(Integer userAddrsId) {
		this.userAddrsId = userAddrsId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetName() {
		return StreetName;
	}

	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
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

	private String houseNo;

	private String StreetName;

	private String cityName;

	private Integer pinCode;

	private Date CreateDate;

	private Date updateDate;

}
