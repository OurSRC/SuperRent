package ControlObjects;

import UserManagement.User;
import SystemOperations.Error;

public class LoginCtrl {
	
	public LoginCtrl() {
		super();
		// Initialize database connection ...
	}
	
	public User.TYPE loginCheck(String username, String password){
		if( "admin".compareTo(username)==0 ){
			if("admin".compareTo(password)==0)
				return User.TYPE.STAFF;
			else
				Error.setLastError(Error.ERROR_WRONG_PASSWORD);
		}
		if( "clerk".compareTo(username)==0 ){
			if("clerk".compareTo(password)==0)
				return User.TYPE.STAFF;
			else
				Error.setLastError(Error.ERROR_WRONG_PASSWORD);
		}
		if( "manager".compareTo(username)==0 ){
			if("manager".compareTo(password)==0)
				return User.TYPE.STAFF;
			else
				Error.setLastError(Error.ERROR_WRONG_PASSWORD);
		}

		if( "customer".compareTo(username)==0 ){
			if("customer".compareTo(password)==0)
				return User.TYPE.CUSTOMER;
			else
				Error.setLastError(Error.ERROR_WRONG_PASSWORD);
		}
		if( "club".compareTo(username)==0 ){
			if("club".compareTo(password)==0)
				return User.TYPE.CUSTOMER;
			else
				Error.setLastError(Error.ERROR_WRONG_PASSWORD);
		}
		return User.TYPE.ERROR;
		
		//search user table by username & password
		//if there is a match, get user type
		//then, use username to check target type table (staff or customer)
		//get all detail info
	}

}
