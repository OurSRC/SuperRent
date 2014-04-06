package VehicleManagement;

import java.util.ArrayList;
import java.util.Date;


public class Vehicle extends Asset {
	
	public enum BRAND {
		BENZ, BMW, FORD, TOYOTA
	};
	
	public enum TYPE {
		//for car
		ECONOMY,COMPACT,MIDSIZE,STANDARD,FULLSIZE,PREMIUM,LUXURY,SUV,VAN,
		//for truck
		FOOT24,FOOT15,FOOT12,BOXTRUCKS,CARGOVAN
	}
	
	public enum STATUS {
		idle, rent, refill, maintain, sold
	}
	
	public enum INSURANCE {
		standard, full, third
	}

	public String plateNumber;
	public BRAND brand;
	public String model;
	public TYPE type;
	public Date buyDate;
	public String feature;	//ask Ali why "Each type has different features,..."
	public ArrayList<Integer> equipments;
	public float insuranceDaily;
	public float insuranceWeekly;
	public float insuranceHourly;
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
	public ArrayList<Vehicle> search(){	//construct with UNKNOWs, then search in database
		return null;
	}
}
