package Operations;

import java.util.ArrayList;


public class Return {
	public int returnId;
	public int rentId;
	public int staffId;
	
	public int fuel;
	public int odometer;
	public int returnTime;
	public int damage;
	public int receiptNumber;
	public String comment;
	
	public boolean create(Rent rent){
		return false;
	}
	public boolean update(){
		return false;
	}
	public boolean cancel(){
		return false;
	}
	public boolean delete(){
		return false;
	}
	public ArrayList<Return> search(){	//construct with UNKNOWs, then search in database
		return null;
	}
	public Return getByRentId(){
		return null;
	}
	public Return getByReturnId(){
		return null;
	}
}
