package com.neosoft.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.entity.UserAddrsEntity;
import com.neosoft.entity.UserContract;

public interface UserContractRepository extends CrudRepository<UserContract, Serializable> {

}
