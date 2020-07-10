package com.neosoft.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.entity.UserMasterEntity;


public interface UserMasterRepository  extends CrudRepository<UserMasterEntity, Serializable>{

}
