package Operations;

import java.util.ArrayList;

import VehicleManagement.Vehicle;
import java.util.Date;

public class Reserve {
	public int reserveId;
	public int customerId;
	public int staffId;
	
	public int branch;
	public /*Vehicle.TYPE*/String type;
	public Date pickUpTime;
	public Date returnTime;
	public ArrayList</*Equipment.TYPE*/String> equipments;	//addEquipment, removeEquipment
	public Vehicle.INSURANCE insurance;
	public long reserveTime;
	public long lastUpdateTime;		//add by Elitward
	
	public /*long*/String reservationNumber;	//(can be NULL in database, if not reserved in advance)
	
	public boolean create(){
            
            
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
	public ArrayList<Reserve> search(){	//construct with UNKNOWs, then search in database
		return null;
	}
	public Reserve getByReserveId(){
		return null;
	}
	public Reserve getByReservationNumber(){
		return null;
	}
}
