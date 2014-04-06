package UserManagement;

public class Staff extends User {
	
	public enum STATUS {
		ACTIVE, 
		DISACTIVE
	};
	public enum TYPE {
		CLERK,
		MANAGER,
		ADMIN
	};
	
	public int staffId;
	//public int branch;
	public String fistName;
	public String middleName;
	public String lastName;
	public String eMail;
	public String phone;
	//public Date onJobDate;
	public STATUS status;
	public TYPE type;
	
}
