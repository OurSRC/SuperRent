package finance;

import java.util.ArrayList;


public class Payment {
	public int payId;
	public float amount;
	public long time;
	public String description;
	public ArrayList<Item> items;
	public int customerId;
	public int creditCardId;
	
	public enum ITEM_TYPE {
		VEHICLE, EQUIPMENT, DELAY, MILE, DAMAGE, MEMBERSHIP, TICKET, OTHER
	}
	
	public class Item{
		public ITEM_TYPE type;
		public int relatedId;	//related to vehicle id or equipment id
		public String name;
		public float price;
		public int quantity;
	}
	
	public boolean create(){
		return false;
	}
	public boolean update(){
		return false;
	}
	public boolean delete(){
		return false;
	}
	public boolean addItem(){
		return false;
	}
	public boolean removeItem(){
		return false;
	}
	public Payment getByPaymentId(){
		return null;
	}
}
