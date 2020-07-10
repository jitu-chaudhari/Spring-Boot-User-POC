package com.neosoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neosoft.constants.AppConstants;
import com.neosoft.controller.UserRegController;
import com.neosoft.dao.UserMasterRepository;
import com.neosoft.dao.UserRepository;
import com.neosoft.entity.DynamicSeachEntity;
import com.neosoft.entity.ResponseEntityMessage;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserRegController.class);

	//UserDtls Repository instance
	@Autowired
	private UserRepository repo;
	
	//userMaster Repository instance
	@Autowired
	private UserMasterRepository userMasterRepo;
	
	//UserMasterEntity instance
	private UserMasterEntity urMasterEntity;
	
	/**
	 * This method is used to getAllUsers
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public List<UserEntity> fetchAllUsers(){
		logger.debug("GetAlluser Methode Execution Started");
		List<UserEntity> usersList=(List<UserEntity>) repo.findAll();
		logger.debug("GetAlluser Methode Execution ended"+usersList);
		System.out.println(usersList);
		return usersList;
	} 
	
	/**
	 * This method is used to register User Master
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	 public ResponseEntityMessage saveUserMaster(UserMasterEntity entity) {
		logger.debug("saveUser Methode Execution Started"+entity);
		urMasterEntity = new UserMasterEntity();
		ResponseEntityMessage response=new ResponseEntityMessage();
		if (entity != null) {
			
			urMasterEntity = userMasterRepo.save(entity);
			Integer userId = urMasterEntity.getUserMasterId();
			if (userId != null) {
				response.setMessage(AppConstants.SUCCESSINSERTMESSAGE);
				response.setStatusCode(200);
				response.setUserId(userId);
				
				return response;
			}
			response.setMessage(AppConstants.FAILINSERTMESSAGE);
			response.setStatusCode(400);
			logger.debug("saveUser Methode Execution ended");
			return response;
		}
		response.setMessage(AppConstants.FAILINSERTMESSAGE);
		response.setStatusCode(400);
		return response;
	}
	

	/**
	 * This method is used to register User details
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public ResponseEntityMessage saveUser(UserEntity entity){
		logger.debug("saveUser Methode Execution Started");
		UserEntity urEntity = new UserEntity();
		ResponseEntityMessage response=new ResponseEntityMessage();
		if (entity != null) {
			entity.setUserMasterEntity(urMasterEntity);
		    urEntity = repo.save(entity);
			Integer userId = urEntity.getUserid();
			if (userId != null) {
				response.setMessage(AppConstants.SUCCESSINSERTMESSAGE);
				response.setStatusCode(200);
				response.setUserId(userId);
				
				return response;
			}
			response.setMessage(AppConstants.FAILINSERTMESSAGE);
			response.setStatusCode(400);
			logger.debug("saveUser Methode Execution ended");
			return response;
		}
		response.setMessage(AppConstants.FAILINSERTMESSAGE);
		response.setStatusCode(400);
		return response;
	}
	
	/**
	 * This methode is used to modify Representative Data.
	 * 
	 * @param Representative Details entity and Integer as Representative Id.
	 * @return message
	 */
	public ResponseEntityMessage modifyUserDetails(UserEntity entity, Integer userId)
			throws Exception {
		logger.debug("Update Methode Execution Started");
		UserEntity updateEntity = new UserEntity();
		Optional<UserEntity> updateEntity2 =null;
		updateEntity2=repo.findById(userId);
		ResponseEntityMessage response=new ResponseEntityMessage();
		updateEntity=updateEntity2.get();
		System.out.println(" jj ----"+updateEntity);
		urMasterEntity=updateEntity.getUserMasterEntity();
		if(userId!=null) {
		entity.setUserid(userId);
		entity.setUserMasterEntity(urMasterEntity);//entity set
		logger.info("Before Update " + entity);
		updateEntity = repo.save(entity);
		logger.info("After Update " + updateEntity);
		logger.debug("Update Methode Execution Completed");
		response.setMessage(AppConstants.SUCCESSUPDATEMESSAGE);
		response.setUserId(updateEntity.getUserid());
		response.setStatusCode(200);
		return response;
		}
		response.setMessage(AppConstants.FAILUPDATEMESSAGE);
		response.setStatusCode(304);
		return response;
	}
	
	
	/**
	 * This methode is used to find users by fname and lname
	 * 
	 * @param 
	 * @return list of Users
	 */
	@Override
	public List<UserEntity> findUserByFnameAndLname(String fname,String lname) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("findUserByFnameAndLname Methode Execution Started");
		UserEntity getUser=new UserEntity();
		List<UserEntity> list=new ArrayList<>();

			list=repo.findByFnameOrLastName(fname, lname);
		
		System.out.println(getUser);
		logger.debug("findUserByFnameAndLname Methode Execution ended");
		return list;
	}
	
	
	/**
	 * This methode is used to sorting users list by dob and doj
	 * 
	 * @param 
	 * @return list of Users
	 */
	@Override
	public List<UserEntity> sortingUsers() throws Exception {
		
		return repo.sortUserByDobAndDoj();
	}
	
	/**
	 * This methode is used to delete representative details by id
	 * 
	 * @param Integer Representative Id
	 * @return message
	 */
	public ResponseEntityMessage deleteUserDetailsById(Integer userId) throws Exception {
		logger.debug("Delete Methode Execution Started");
		ResponseEntityMessage response=new ResponseEntityMessage();
		if (userId != null) {
			response.setMessage(AppConstants.SUCCESSDELETEMESSAGE);
			response.setStatusCode(200);
			repo.deleteById(userId);
			repo.softDelete(userId);
			return response;
		}
		response.setMessage(AppConstants.FAILDELETEMESSAGE);
		response.setStatusCode(204);
		logger.debug("Delete Methode Execution Completed");
		return response;
	}
	
	/**
	 * This method is used to dynamic Searching
	 * 
	 * @param 
	 * @return list of Users
	 */
	public List<UserEntity> getUserByDySearch(DynamicSeachEntity dySEntity){
		logger.debug("getUserByDySearch Methode Execution Started");
		String fname=dySEntity.getFirstName();
		String lname=dySEntity.getLastName();
		String email=dySEntity.getEmail();
		List<UserEntity> usersList=repo.findByFnameOrLastNameAndEmail(fname, lname, email);
		logger.debug("getUserByDySearch Methode Execution Started");
		if(usersList.size()>0)
		{
			return usersList;				
		}
		return usersList;
		
	}

	@Override
	public List<UserEntity> sortingUsersByDate() {
		return repo.sortUserByDobAndDoj();
		
	}
	
}
