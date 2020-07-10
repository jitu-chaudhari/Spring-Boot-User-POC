package com.neo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neosoft.constants.AppConstants;
import com.neosoft.controller.UserRegController;
import com.neosoft.entity.DynamicSeachEntity;
import com.neosoft.entity.ResponseEntityMessage;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;
import com.neosoft.service.UserService;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {

	@Mock
	private static UserService userService;

	@InjectMocks
	private static UserRegController regController = new UserRegController();

	UserEntity entity = null;
	UserEntity failEntity = null;
	UserMasterEntity uMentity = null, ufailMentity = null;
	UserMasterEntity uMfailentity = null;
	ResponseEntityMessage resMsg = null;
	ResponseEntityMessage failResMsg = null;
	ResponseEntity<ResponseEntityMessage> resEntity = new ResponseEntity<ResponseEntityMessage>(resMsg, HttpStatus.OK);

	String fname = "Ram";
	String lname = "Kumar";
	List<UserEntity> list = null;

	@BeforeEach
	public void setUpForTestin() {
		uMentity = new UserMasterEntity();
		uMentity.setUserMasterId(201);
		uMentity.setUserName("Amit");
		uMentity.setPassword("78Am");

		ufailMentity = new UserMasterEntity();
		ufailMentity.setUserMasterId(null);
		ufailMentity.setUserName("Amit");
		ufailMentity.setPassword("78Am");

		entity = new UserEntity();
		entity.setUserid(2);
		entity.setFirstName("Ram");
		entity.setLastName("kumar");
		entity.setMobileNo(787878);

		resMsg = new ResponseEntityMessage();
		resMsg.setMessage(AppConstants.SUCCESSINSERTMESSAGE);
		resMsg.setStatusCode(200);
		resMsg.setUserId(entity.getUserid());

		failEntity = new UserEntity();
		failEntity.setUserid(null);
		failResMsg = new ResponseEntityMessage();
		failResMsg.setMessage(AppConstants.FAILINSERTMESSAGE);
		failResMsg.setStatusCode(400);
		failResMsg.setUserId(null);

		list = new ArrayList<>();
		list.add(entity);
	}
	
	/**
	 * This method is used to getAllUsersTest
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	
	@Test
	public void getAllUsers() {
		Mockito.when(userService.fetchAllUsers()).thenReturn(list);
		assertEquals(list,regController.getAllUsers());
	}

	/**
	 * This method is used to UserMasterTesting If response is success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void saveUserMasterTestIfSucces() {
		Mockito.when(userService.saveUserMaster(uMentity)).thenReturn(resMsg);
		// System.out.println(resEntity+"==="+regController.insertUserMaster(uMentity));
		assertEquals(new ResponseEntity<ResponseEntityMessage>(resMsg, HttpStatus.OK),
				regController.insertUserMaster(uMentity));
	}

	/**
	 * This method is used to UserMasterTesting If response is Failed
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void saveUserMasterTestIfFailed() {
		Mockito.when(userService.saveUserMaster(ufailMentity)).thenReturn(failResMsg);
		// System.out.println(resEntity+"==="+regController.insertUserMaster(uMentity));
		assertEquals(new ResponseEntity<ResponseEntityMessage>(HttpStatus.CONFLICT),
				regController.insertUserMaster(uMentity));
	}

	/**
	 * This methode is used to UserDetailsTesting If response is Success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void saveUserDtlsTestIfSucces() {

		Mockito.when(userService.saveUser(entity)).thenReturn(resMsg);
		assertEquals(new ResponseEntity<ResponseEntityMessage>(resMsg, HttpStatus.OK),
				regController.registerUserDtls(entity));
	}

	/**
	 * This method is used to UserDetailsTesting If response is Success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void saveUserDtlsTestIfFailed() {
		Mockito.when(userService.saveUser(entity)).thenReturn(resMsg);
		assertEquals(new ResponseEntity<ResponseEntityMessage>(HttpStatus.CONFLICT),
				regController.registerUserDtls(failEntity));

	}

	/**
	 * This method is used to UserDetailsUpdate If response is Success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 * @throws Exception
	 */
	@Test
	public void saveUserUpdateTestIfSuccess() throws Exception {
		resMsg.setMessage(AppConstants.SUCCESSUPDATEMESSAGE);
		Mockito.when(userService.modifyUserDetails(entity, 10)).thenReturn(resMsg);
		assertEquals(new ResponseEntity<ResponseEntityMessage>(resMsg, HttpStatus.OK),
				regController.updateUserDetailsById(entity, 10));

	}

	/**
	 * This method is used to UserDetailsUpdate If response is failed
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 * @throws Exception
	 */
	@Test
	public void saveUserUpdateTestIfFailed() throws Exception {
		failResMsg.setMessage(AppConstants.FAILUPDATEMESSAGE);
		failResMsg.setStatusCode(304);
		Mockito.when(userService.modifyUserDetails(failEntity, 10)).thenReturn(failResMsg);
		assertEquals(new ResponseEntity<ResponseEntityMessage>(failResMsg, HttpStatus.NOT_MODIFIED),
				regController.updateUserDetailsById(entity, null));

	}

	/**
	 * This method is used to searchUser If response is Success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void SearchUserTestIfSucces() {

		DynamicSeachEntity dy = new DynamicSeachEntity("Manoj", "", 221, "");
		Mockito.when(userService.getUserByDySearch(dy)).thenReturn(list);
		System.out.println(list + "========" + regController.dySearchUser(dy));
		assertEquals(list, regController.dySearchUser(dy));
	}
	
	/**
	 * This methode is used to searchUser If response is failed
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 */
	@Test
	public void SearchUserTestIfFailed() {
		list=new ArrayList<UserEntity>();
		DynamicSeachEntity dy = new DynamicSeachEntity("", "", 221, "");
		Mockito.when(userService.getUserByDySearch(dy)).thenReturn(list);
		System.out.println(list + "========" + regController.dySearchUser(dy));
		assertEquals(list, regController.dySearchUser(dy));
	}

	

	/**
	 * This method is used to deleteUsers If response is Success
	 * 
	 * @param entity
	 * @param mrId
	 * @return ResponseEntity<String> message
	 * @throws Exception
	 */
	@Test
	public void deleteUserTestIfSuccess() throws Exception {
		resMsg.setMessage(AppConstants.SUCCESSDELETEMESSAGE);
		Mockito.when(userService.deleteUserDetailsById(10)).thenReturn(resMsg);
		assertEquals(new ResponseEntity<ResponseEntityMessage>(resMsg, HttpStatus.OK),
				regController.deleteUserById(10));

	}
	

}
