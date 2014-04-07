package Operations;

import java.util.ArrayList;


public class Rent {
	public int rentId;
	public int reserveId;
	public int staffId;

	public String contractNumber;
	public long rentTime;
	
	public int vehicleId;
	//remove this: public Vector<Integer> equipmentIds;

	public int fuel;
	public int odometer;
	
	public boolean create(Reserve reserve){
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
	public ArrayList<Rent> search(){	//construct with UNKNOWs, then search in database
		return null;
	}
	public Rent getByContractNumber(){
		return null;
	}
	public Rent getByRentId(){
		return null;
	}
	public Rent getByReserveId(){
		return null;
	}
	public Rent getByVehicleId(){
		return null;
	}
}
