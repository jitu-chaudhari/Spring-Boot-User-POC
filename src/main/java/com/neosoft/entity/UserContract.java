package com.neosoft.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "User_contract")
public class UserContract {

	public Integer getUserContractId() {
		return userContractId;
	}

	public void setUserContractId(Integer userContractId) {
		this.userContractId = userContractId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
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

	public UserMasterEntity getUserMasterEntity() {
		return userMasterEntity;
	}

	public void setUserMasterEntity(UserMasterEntity userMasterEntity) {
		this.userMasterEntity = userMasterEntity;
	}

	@Id
	@Column(name = "userContractId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userEdu_id_generator")
	@SequenceGenerator(name = "UserEdu_id_generator", initialValue = 1, allocationSize = 1, sequenceName = "userEdu_id_seq")
	private Integer userContractId;

	@Column(name = "companyName")
	@NotNull
	private String companyName;

	@Column(name = "duration")
	@NotNull
	private String duration;

	@Column(name = "exp")
	@NotNull
	private String exp;

	@Column(name = "createDate", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreateDate;

	@Column(name = "updateDate")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@OneToOne
	@JoinColumn(name = "urMaster_id", nullable = false)
	private UserMasterEntity userMasterEntity;

}
