package com.neosoft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.constants.AppConstants;
import com.neosoft.entity.DynamicSeachEntity;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;
import com.neosoft.entity.UserPhoneNoOnlyUpdate;
import com.neosoft.filter.CheckValidation;
import com.neosoft.model.UserDtlsModel;
import com.neosoft.service.UserService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

/**
 * RESTCONTROLLER
 * 
 */
@RestController
public class UserRegController extends CheckValidation {

	private static final Logger logger = LoggerFactory.getLogger(UserRegController.class);

	// UserDetails Service
	
	@Autowired
	private  UserService service;
	/**
	 * This method will retrieve all the data from user master table  
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers() {
		logger.debug("GetAllUser Method Executed", AppConstants.GETSORTEDDATA);
			return service.fetchAllUsers();
	}

	/**
	 * This method will insert/post the data in to user master table
	 * 
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@PostMapping(value = "/userMasterRegistration", produces = "Application/json")
	public ResponseEntity<?> insertUserMaster(@Valid @RequestBody UserMasterEntity entity) {
		logger.debug("Save Methode Execution Started");
		ResponseEntity<Object> response = null;
		System.out.println("My " + entity);
		// ResponseEntityMessage response = new ResponseEntityMessage();
		try {

			// response = service.saveUserMaster(entity);
			response = validationForUserMaster(entity) ? responseBuilder(this.service.saveUserMaster(entity)) : null;
			System.out.println(response);
			return response;
		} catch (Exception e) {
			logger.info("Error Message getRepresentativeDetailsByID:" + e.getMessage());
			logger.debug("Save Methode Execution Completed");
			return responseBuilder(new Exception("somthing went wrong"));
		}
	}

	/**
	 * This method is used to updateUserDetailsById
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@PostMapping(value = "/userdtlsregister", produces = { "application/json" })
	public ResponseEntity<?> registerUserDtls(@Valid @RequestBody UserEntity entity) {
		ResponseEntity<Object> response = null;
		logger.debug("Save Methode Execution Started");
		System.out.println("userdtlsregister");
		
		// ResponseEntityMessage response = new ResponseEntityMessage();
		try {
			if (validationForUserDtls(entity)) {
				response = responseBuilder(service.saveUser(entity));
			}
		} catch (Exception e) {
			logger.info("Error Message getRepresentativeDetailsByID:" + e.getMessage());
			logger.debug("Save Methode Execution Completed");
			response = responseBuilder(new Exception("somthing went wrong"));
		}
		return response;
	}

	/**
	 * This method is used to updateUserDetailsById
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@PutMapping(value = "/updateUserDetailsById", produces = { "application/json" })
	public ResponseEntity<?> updateUserDetailsById(@Valid @RequestBody UserEntity entity,
			@RequestParam("id") Integer userId) {
		logger.debug("UpdateUserDetailsById Method Execution Started");

		ResponseEntity<Object> response = null;

		try {
			logger.info("This MR_Id " + response);
			logger.debug("UpdateRepresentativeDetailsById Method Execution Completed");
			response = validationForUserDtlsUpdate(userId) ? responseBuilder(service.modifyUserDetails(entity, userId))
					: null;
			if (response == null) {
				throw new Exception("please put ID");
			}
			return response;
		} catch (Exception e) {
			logger.error("Error Message");
			logger.debug("UpdateUserDetailsById Method Execution Completed");
			response = responseBuilder(new Exception());
			return response;
		}

	}

	/**
	 * This method is used to dynamic search users
	 * 
	 * @param entity
	 * @param mrId
	 * @return users list
	 */
	@PostMapping(value = "/searchByName", produces = { "application/json" })
	public List<?> dySearchUser(@RequestBody DynamicSeachEntity dySentity) {
		logger.debug("searchUser Method Execution Started");
		//UserEntity urentity = new UserEntity();
		UserDtlsModel userDtls=new UserDtlsModel();
		List<UserEntity> userEntityList=new ArrayList<>();
		List<UserDtlsModel> usermodelList=new ArrayList<>();
		try {
			userEntityList = service.getUserByDySearch(dySentity);
			if(userEntityList!=null) {
			System.out.println(userEntityList);
			userEntityList.forEach(s-> 
			{         userDtls.setFirstName(s.getFirstName());
					  userDtls.setLastName(s.getLastName());
					  userDtls.setMobileNo(s.getMobileNo());
					  userDtls.setUserMasterEntity(s.getUserMasterEntity());
			});
			usermodelList.add(userDtls);
			}else {
				throw new Exception();
			}
			return usermodelList;
		} catch (Exception e) {
			logger.error("Error Message");
			logger.debug("dySearchUser Method Execution Completed");
			System.out.println("exception");
			userDtls.setMessage("list is empty please put right key word");
			usermodelList.add(userDtls);
			return usermodelList;
		}
	}
	/**
	 * This method is used to sorting users list
	 * 
	 * @param entity
	 * @param mrId
	 * @return users list
	 */
	@GetMapping(value = "/sortingByDates", produces ={ "application/json" })
	public List<UserEntity> sortingUsers() {
		logger.debug("sortingUsers Method Execution Started");
		List<UserEntity> list = new ArrayList<>();
		try {
			list = service.sortingUsersByDate();
			return list;
		} catch (Exception e) {
			logger.debug("sortUserDetailsByDates Method Execution Completed");
			System.out.println("exception");
			return list;
		}
	}

	/**
	 * This method is used to deleteRepresentativeById
	 * 
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@PutMapping(value = "/deleteUserById", produces = { "application/json" })
	public ResponseEntity<?> deleteUserById(@Valid @RequestParam("id") Integer userId) {
		logger.debug("DeleteRepresentativeById Method Execution Started");
		ResponseEntity<Object> response = null;
		try {
			response = validationForUserDtlsUpdate(userId) ? responseBuilder(service.deleteUserDetailsById(userId)): null;
			if (response == null) {
				throw new Exception("please put ID");
			}
			return response;
		} catch (Exception e) {
			logger.error("Error Message deleteUserById:" + e.getMessage());
			logger.debug("DeleteUserById Method Execution Started");
			response=responseBuilder(e);
			return response;
			}
	}

}
