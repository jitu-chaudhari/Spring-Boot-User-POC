package com.neosoft.service;

import java.util.List;

import com.neosoft.entity.DynamicSeachEntity;
import com.neosoft.entity.ResponseEntityMessage;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;


public interface UserService {

	public List<UserEntity> fetchAllUsers();
	
	public ResponseEntityMessage saveUser(UserEntity entity);
	
	public ResponseEntityMessage modifyUserDetails(UserEntity entity, Integer userId)throws Exception ;
    
	public List<UserEntity> findUserByFnameAndLname(String fname,String lname)throws Exception; 
    
	public List<UserEntity> sortingUsers()throws Exception; 
    
	public ResponseEntityMessage deleteUserDetailsById(Integer userId) throws Exception;
    
	public ResponseEntityMessage saveUserMaster(UserMasterEntity entity);
    
	public List<UserEntity> getUserByDySearch(DynamicSeachEntity dySEntity);
	
	public List<UserEntity> sortingUsersByDate();
}
