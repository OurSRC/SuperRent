package UserManagement;

import java.util.Date;

public class Customer extends User {
	//public int customerId;
	public String phone;
	public String address;
	public String fistName;
	public String middleName;
	public String lastName;
	public String eMail;
	public String driverLicenseNumber;
	//public ArrayList<CreditCard> creditCards;
	//public ArrayList<Payment> paymentHistory;
	
	public boolean isClubMember;
	public int point;				//for club member only
	public Date membershipExpiry;	//for club member only
}
