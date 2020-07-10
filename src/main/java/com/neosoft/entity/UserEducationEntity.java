package com.neosoft.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="User_Education")
@NoArgsConstructor
public class UserEducationEntity {

	@Id
	@Column(name="ureduid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userEdu_id_generator")
	@SequenceGenerator(name="UserEdu_id_generator",
	                   initialValue = 1,allocationSize = 1,
	                   sequenceName = "userEdu_id_seq")
	private Integer userEduId;
	
	public Integer getUserEduId() {
		return userEduId;
	}

	public void setUserEduId(Integer userEduId) {
		this.userEduId = userEduId;
	}

	public String getUserScName() {
		return userScName;
	}

	public void setUserScName(String userScName) {
		this.userScName = userScName;
	}

	public String getUserScCityName() {
		return userScCityName;
	}

	public void setUserScCityName(String userScCityName) {
		this.userScCityName = userScCityName;
	}

	public String getUserClgName() {
		return userClgName;
	}

	public void setUserClgName(String userClgName) {
		this.userClgName = userClgName;
	}

	public String getUserBranchName() {
		return userBranchName;
	}

	public void setUserBranchName(String userBranchName) {
		this.userBranchName = userBranchName;
	}

	public String getUserClgCityName() {
		return UserClgCityName;
	}

	public void setUserClgCityName(String userClgCityName) {
		UserClgCityName = userClgCityName;
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

	@Column(name="userScName")
	@NotNull
	private String userScName;
	
	@Column(name="userScCityName")
	@NotNull
	private String userScCityName;
	
	@Column(name="userClgName")
	@NotNull
	private String userClgName;
	
	@Column(name="userBranchName")
	@NotNull
	private String userBranchName;
	
	@Column(name="UserClgCityNam")
	@NotNull
	private String UserClgCityName;
	
	@Column(name="createDate",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreateDate;
	
	@Column(name="updateDate")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	
}
