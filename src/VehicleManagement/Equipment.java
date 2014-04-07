package VehicleManagement;

import java.util.ArrayList;


public class Equipment extends Asset {
	
	public enum TYPE{
		//for car & SUV
		SKIRACK, CHILDSAFETYSEAT,
		//for truck
		LIFTGATE, TOWING
	};
	
	public enum STATUS {
		idle, rent, scrap
	};
	
	public String name;
	public TYPE type;
	public STATUS status;
	
	public boolean create(){
		return false;
	}
	public boolean update(){
		return false;
	}
	public boolean delete(){
		return false;
	}
	public ArrayList<Equipment> search(){	//construct with UNKNOWs, then search in database
		return null;
	}

}
