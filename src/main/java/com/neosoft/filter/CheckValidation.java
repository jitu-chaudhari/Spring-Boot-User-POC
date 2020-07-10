package com.neosoft.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neosoft.constants.AppConstants;
import com.neosoft.entity.ResponseEntityMessage;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;
import com.neosoft.model.UserDtlsModel;
import com.neosoft.service.UserService;

@Service
public abstract class CheckValidation {
	private UserMasterEntity sendUMEntity;
	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	private Integer userId;
	

	@Autowired
	private UserService service;

	/**
	 * This method is used to validation for UserMaster table
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public boolean validationForUserMaster(UserMasterEntity entity) {
		this.userId = entity.getUserMasterId();
		if (entity.getUserName() != null && entity.getPassword() != null && entity.getUsersAddrs() != null
				&& entity.getUsersEducation() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to validation for UserDtls table
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public boolean validationForUserDtls(UserEntity entity) {
		Matcher matcher = EMAIL_PATTERN.matcher(entity.getEmail());
		// matcher.matches();

		this.userId = entity.getUserid();
		if (entity.getFirstName() != null && entity.getLastName() != null && entity.getEmail() != null
				&& entity.getDob() != null && entity.getDoj() != null) {

			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to validation for UserDtls table
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public boolean validationForUserDtlsUpdate(Integer userId) {
		this.userId = userId;
		if (userId != null && userId != 0) {
			System.out.println(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to giving positive response
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public ResponseEntity<Object> responseBuilder(Object user) throws Exception {
		ResponseEntityMessage resEntMsg = new ResponseEntityMessage();
		resEntMsg.setMessage(AppConstants.SUCCESSINSERTMESSAGE);
		resEntMsg.setStatusCode(200);
		resEntMsg.setUserId(userId);
		return new ResponseEntity<Object>(resEntMsg, HttpStatus.OK);
	}

	/**
	 * This method is used to Negative response
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public ResponseEntity<Object> responseBuilder(Exception exc) {
		ResponseEntityMessage resEntMsg = new ResponseEntityMessage();
		resEntMsg.setMessage(AppConstants.FAILINSERTMESSAGE);
		resEntMsg.setStatusCode(409);
		resEntMsg.setUserId(userId);
		return new ResponseEntity<Object>(resEntMsg, HttpStatus.CONFLICT);

	}

	/**
	 * This method is used to giving response to getAllUsers
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	public List<?> responseInList(String msg) throws Exception {
		UserDtlsModel userDtls = new UserDtlsModel();
		List<UserEntity> userEntityList = new ArrayList<>();
		List<UserDtlsModel> usermodelList = new ArrayList<>();
		System.out.println(msg);
		if (msg == AppConstants.GETSORTEDDATA){
			
			userEntityList = service.sortingUsers();
		} else {
			userEntityList = service.fetchAllUsers();
		}
		userEntityList.forEach(s -> {
			userDtls.setFirstName(s.getFirstName());
			userDtls.setLastName(s.getLastName());
			userDtls.setMobileNo(s.getMobileNo());
			userDtls.setUserMasterEntity(s.getUserMasterEntity());
		});

		usermodelList.add(userDtls);
		return usermodelList;
	}

}
