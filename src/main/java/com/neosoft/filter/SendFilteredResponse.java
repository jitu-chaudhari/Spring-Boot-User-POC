package com.neosoft.filter;

import org.springframework.stereotype.Service;

import com.neosoft.entity.UserMasterEntity;


public abstract class SendFilteredResponse{

	private UserMasterEntity sendUMEntity;
	/**
	 * This method is used to send Response for UserMasterRegistration
	 * 
	 * @param Entity class Object
	 * @return message
	 */
	
	public UserMasterEntity resForUserMasterRegister(UserMasterEntity entity) {
		sendUMEntity.setUserName(entity.getUserName());
		//sendUMEntity.setUsersAddrs(set<entity.getUsersAddrs().forEach(s->s.getCityName())>);
		return null;
	}

	
}
