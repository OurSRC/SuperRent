package ControlObjects;

import java.util.ArrayList;

import SystemOperations.Error;
import UserManagement.Staff;

public class StaffCtrl {
	public Staff createStaff(Staff staff){
		Error.setLastError(Error.ERROR_USERNAME_ALREADY_EXIT);
		return null;
	}
	public boolean updateStaff(Staff staff){
		return false;
	}
	public boolean deleteStaff(int staffId){
		return false;
	}
	public Staff getStaffById(int staffId){
		Staff dummy = dummyStaff();
		return dummy;
	}
	public Staff getStaffByUsername(String username){
		if("admin".compareTo(username)!=0 && "clerk".compareTo(username)!=0 && "manager".compareTo(username)!=0){
			Error.setLastError(Error.ERROR_GENERAL);
			return null;
		}
		
		Staff dummy = dummyStaff();
		dummy.username = username;
		if("admin".compareTo(username)==0){
			dummy.type = Staff.TYPE.ADMIN;
			dummy.staffId = 1001;
		}
		if("clerk".compareTo(username)==0){
			dummy.type = Staff.TYPE.CLERK;
			dummy.staffId = 1002;
		}
		if("manager".compareTo(username)==0){
			dummy.type = Staff.TYPE.MANAGER;
			dummy.staffId = 1003;
		}
		return dummy;
	}
	public ArrayList<Staff> searchStaff(Staff staff){
		return null;
	}
	
	private Staff dummyStaff(){
		Staff dummy = new Staff();
		dummy.username = "username";
		dummy.password = "password";	
		dummy.staffId = 100;
		dummy.fistName = "first name";
		dummy.middleName = "middle name";
		dummy.lastName = "last name";
		dummy.eMail = "dummy@nowhere.com";
		dummy.phone = "123456789";
		dummy.status = Staff.STATUS.ACTIVE;
		dummy.type = Staff.TYPE.CLERK;
		return dummy;
	}
}
