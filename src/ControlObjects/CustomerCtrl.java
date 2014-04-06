package ControlObjects;

import java.util.ArrayList;
import java.util.Date;

import UserManagement.Customer;
import UserManagement.User;

import SystemOperations.Error;

public class CustomerCtrl {
	public Customer createCustomer(Customer customer){
		Error.setLastError(Error.ERROR_USERNAME_ALREADY_EXIT);
		return null;
	}
	public boolean updateCustomer(Customer customer){
		return false;
	}
	public boolean deleteCustomer(int customerId){
		return false;
	}
	public Customer getCustomerByPhone(String phone){
		Customer dummy = dummyCustomer();
		dummy.phone = phone;
		return dummy;
	}
	public Customer getCustomerByUsername(String username){
		if("customer".compareTo(username)!=0 && "club".compareTo(username)!=0){
			Error.setLastError(Error.ERROR_GENERAL);
			return null;
		}
		
		Customer dummy = dummyCustomer();
		dummy.username = username;
		if("customer".compareTo(username)==0){
			dummy.isClubMember = false;
			dummy.phone = "1357924680";
		}
		if("club".compareTo(username)==0){
			dummy.isClubMember = true;
			dummy.phone = "2468013579";
		}
		return dummy;
	}
	public ArrayList<Customer> searchCustomer(Customer customer){
		return null;
	}
	public boolean payClubMemberFee(Customer customer, int Years){
		return false;
	}
	
	private Customer dummyCustomer(){
		Customer dummy = new Customer();
		dummy.username = "username";
		dummy.password = "password";	
		dummy.type = User.TYPE.CUSTOMER;
		dummy.phone = "1234567890";
		dummy.address = "homeless";
		dummy.fistName = "first name";
		dummy.middleName = "middle name";
		dummy.lastName = "last name";
		dummy.eMail = "dummy@nowhere.com";
		dummy.driverLicenseNumber = "13579";
		//dummy.creditCards = null;
		//dummy.paymentHistory = null;
		dummy.isClubMember = false;
		dummy.point = 0;
		dummy.membershipExpiry = new Date();
		return dummy;
	}
}
