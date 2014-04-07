package ControlObjects;

import java.util.ArrayList;

import VehicleManagement.Equipment;

public class EquipmentCtrl {
	public Equipment createEquipment(Equipment equipment){
		return null;
	}
	public boolean updateEquipment(Equipment equipment){
		return false;
	}
	public boolean removeEquipment(int equipmentId){
		return false;
	}
	public Equipment getEquipment(int equipmentId){
		return null;
	}
	public ArrayList<Equipment> searchEquipment(Equipment equipment){
		return null;
	}

	public boolean appendEquipmentTypeToVehicleType(String EquipmentType, String VehicleType){
		return false;
	}
	public boolean removeEquipmentTypeFromVehicleType(String EquipmentType, String VehicleType){
		return false;
	}
	public ArrayList<String> getEquipmentTypeForVehicleType(String VehicleType){
		return null;
	}
	public ArrayList<String> getEquipmentType(){
		return null;
	}
}
