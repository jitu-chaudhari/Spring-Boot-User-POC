package com.neosoft.entity;

import lombok.Data;

@Data
public class ResponseEntityMessage {
    public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private Integer statusCode;
    private Integer userId;
    private  String message;
}
