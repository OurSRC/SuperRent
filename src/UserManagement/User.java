package UserManagement;

public class User {
	public enum TYPE {
		STAFF,
		CUSTOMER,
		ERROR
	};
	
	public String username;
	public String password;	//if password is null, can not login
	public TYPE type;
}
