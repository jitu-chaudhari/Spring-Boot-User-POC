package com.neosoft.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.entity.UserAddrsEntity;

public interface UserAddressRepository extends CrudRepository<UserAddrsEntity, Serializable> {

}
