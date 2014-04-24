package ControlObjects;

import entity.Rent;
import java.util.ArrayList;



public class RentCtrl {
	public Rent createRent(Rent rent){
		return null;
	}
	public boolean updateRent(Rent rent){ //for both modify & cancel
		return false;
	}
//	public boolean cancelRent(int rentId){
//		return false;
//	}
	public boolean deleteRent(int rentId){
		return false;
	}
	public ArrayList<Rent> searchRent(Rent rent){
		return null;
	}
	public Rent getRent(String contractNumber){
		return null;
	}
	public Rent getRent(Reservation reserve){
		return null;
	}
}
