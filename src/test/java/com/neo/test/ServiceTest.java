package com.neo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neosoft.constants.AppConstants;
import com.neosoft.dao.UserMasterRepository;
import com.neosoft.dao.UserRepository;
import com.neosoft.entity.ResponseEntityMessage;
import com.neosoft.entity.UserAddrsEntity;
import com.neosoft.entity.UserEducationEntity;
import com.neosoft.entity.UserEntity;
import com.neosoft.entity.UserMasterEntity;
import com.neosoft.service.UserService;
import com.neosoft.service.UserServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	@Mock
	private static UserRepository userRepo;

	// userMaster Repository instance
	@Mock
	private UserMasterRepository userMasterRepo;

	@InjectMocks
	private static UserService serviceImpl = new UserServiceImpl();

	UserEntity entity = null;
	UserEntity failEntity = null;
	ResponseEntityMessage resMsg = null;
	ResponseEntityMessage failResMsg = null;
	UserMasterEntity uMentity = null;
	UserMasterEntity ufailMentity = null;

	String fname = "Ram";
	String lname = "Kumar";
	List<UserEntity> list = null;

	@BeforeEach
	public void setUpForTestin() {

		Set<UserAddrsEntity> uAset = new HashSet();
		uAset.add(new UserAddrsEntity());

		Set<UserEducationEntity> uEset = new HashSet();
		uEset.add(new UserEducationEntity());

		uMentity = new UserMasterEntity();
		uMentity.setUserMasterId(201);
		uMentity.setUserName("Jitu");
		uMentity.setPassword("78Am");
		uMentity.setUsersAddrs(uAset);
		uMentity.setUsersEducation(uEset);

		ufailMentity = new UserMasterEntity();
		ufailMentity.setUserMasterId(null);
		ufailMentity.setUserName("Jitu");
		ufailMentity.setPassword("78Am");
		entity = new UserEntity();
		entity.setUserid(2);
		entity.setFirstName("Ram");
		entity.setLastName("Bhai");
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
	 * This methode is used to getAllUsersTest
	 * 
	 * @return void
	 */
	@Test
	public void getAllUsers() {
		Mockito.when(userRepo.findAll()).thenReturn(list);
		assertEquals(list, serviceImpl.fetchAllUsers());
	}

	/**
	 * This methode is used to saveUserMaster Details
	 * 
	 * @return void
	 */
	@Test
	public void saveUserMasterDtls() {
		resMsg.setUserId(201);
		Mockito.when(userMasterRepo.save(uMentity)).thenReturn(uMentity);
		System.out.println(resMsg + " ===" + serviceImpl.saveUserMaster(uMentity));
		assertEquals(resMsg, serviceImpl.saveUserMaster(uMentity));
	}

	/**
	 * This methode is used to saveUserMaster if failed
	 * 
	 * @return void
	 */

	@Test
	public void saveUserMasterDtlsFailed() {
		Mockito.when(userMasterRepo.save(ufailMentity)).thenReturn(ufailMentity);
		assertEquals(failResMsg, serviceImpl.saveUserMaster(ufailMentity));
	}

	/**
	 * This methode is used to saveuserDtlls is response Success
	 * 
	 * @return void
	 */
	@Test
	public void saveUserTestIfSucces() {

		Mockito.when(userRepo.save(entity)).thenReturn(entity);
		assertEquals(resMsg, serviceImpl.saveUser(entity));
	}

	@Test
	public void saveUserTestIfFailed() {
		Mockito.when(userRepo.save(failEntity)).thenReturn(failEntity);
		assertEquals(failResMsg, serviceImpl.saveUser(failEntity));
	}

/*	@Test
	public void modifyUserDetailsIfSuccess() throws Exception {
		resMsg.setMessage(AppConstants.SUCCESSUPDATEMESSAGE);
		entity.setUserMasterEntity(uMentity);
	
		assertEquals(resMsg,serviceImpl.modifyUserDetails(entity, 2));
		
		// if it's List in your case
        Optional<UserEntity> objectList = Optional.of(entity);              
       ((UserEntity) given(userRepo.findById(Mockito.anyLong()))).willReturn(objectList); 
    	Mockito.when(userRepo.save(entity)).thenReturn(entity);
        Object returnedObject = objectServiceImpl.getById(1L);
        Mockito.verify(objectDao).findById(Mockito.anyLong());
        assertNotNull(returnedObject);

	}
*/
	private Object given(Optional<UserEntity> findById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void modifyUserDetailsIfFailed() throws Exception {
		failResMsg.setMessage(AppConstants.FAILUPDATEMESSAGE);
		failResMsg.setStatusCode(304);
		// Mockito.when(userRepo.save(failEntity)).thenReturn(failEntity);
		assertEquals(failResMsg, serviceImpl.modifyUserDetails(failEntity, failEntity.getUserid()));

	}

	@Test
	public void findUserByFnameAndLnameIfSuccess() throws Exception {

		Mockito.when(userRepo.findByFnameOrLastName(fname, lname)).thenReturn(list);
		assertEquals(list, serviceImpl.findUserByFnameAndLname(fname, lname));

	}

	/*
	 * @Test public void sortingUsers()throws Exception{
	 * Mockito.when(userRepo.sortUserByDobAndDoj()).thenReturn(list);
	 * assertEquals(list,serviceImpl.findUserByFnameAndLname(fname, lname));
	 * 
	 * }
	 */

	@Test
	public void deleteUserDetailsByIdIfSuccess() throws Exception {
		resMsg.setMessage(AppConstants.SUCCESSDELETEMESSAGE);
		resMsg.setUserId(null);
		doNothing().when(userRepo).softDelete(entity.getUserid());
		assertEquals(resMsg, serviceImpl.deleteUserDetailsById(entity.getUserid()));

	}

	@Test
	public void deleteUserDetailsByIdIfFailed() throws Exception {
		failResMsg.setMessage(AppConstants.FAILDELETEMESSAGE);
		failResMsg.setStatusCode(204);

		// doNothing().when(userRepo).softDelete(entity.getUserid());
		assertEquals(failResMsg, serviceImpl.deleteUserDetailsById(failEntity.getUserid()));

	}

}
