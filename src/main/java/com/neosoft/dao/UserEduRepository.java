package com.neosoft.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.entity.UserEducationEntity;

public interface UserEduRepository extends CrudRepository<UserEducationEntity, Serializable> {

}
