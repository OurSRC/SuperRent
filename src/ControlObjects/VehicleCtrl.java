package ControlObjects;

import java.util.ArrayList;

import VehicleManagement.Vehicle;

public class VehicleCtrl {
	public Vehicle createVehicle(Vehicle vehicle){
		return null;
	}
	public boolean updateVehicle(Vehicle vehicle){
		return false;
	}
	public boolean removeVehicle(int vehicleId){
		return false;
	}
	public Vehicle getVehicle(int vehicleId){
		return null;
	}
	public ArrayList<Vehicle> searchVehicle(Vehicle vehicle){
		return null;
	}
	public ArrayList<String> getVehicleAvailability(String vehicleType, long pickUpTime, long returnTime){
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("ECONOMY");
		ans.add("COMPACT");
		ans.add("MIDSIZE");
		return ans;
	}
	public ArrayList<String> getVehicleType(){
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("ECONOMY");
		ans.add("COMPACT");
		ans.add("MIDSIZE");
		ans.add("STANDARD");
		ans.add("FULLSIZE");
		ans.add("PREMIUM");
		ans.add("LUXURY");
		ans.add("SUV");
		ans.add("VAN");
		ans.add("FOOT24");
		ans.add("FOOT15");
		ans.add("FOOT12");
		ans.add("BOXTRUCKS");
		ans.add("CARGOVAN");
		return ans;
	}
	public ArrayList<String> getCarType(){
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("ECONOMY");
		ans.add("COMPACT");
		ans.add("MIDSIZE");
		ans.add("STANDARD");
		ans.add("FULLSIZE");
		ans.add("PREMIUM");
		ans.add("LUXURY");
		ans.add("SUV");
		ans.add("VAN");
		return ans;
	}
	public ArrayList<String> getTrunkType(){
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("FOOT24");
		ans.add("FOOT15");
		ans.add("FOOT12");
		ans.add("BOXTRUCKS");
		ans.add("CARGOVAN");
		return ans;
	}
}
