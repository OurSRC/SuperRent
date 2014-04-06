package ControlObjects;

import java.util.ArrayList;

import Operations.Reserve;

public class ReserveCtrl {
	private static Reserve dummy = null;

	public Reserve createReserve(Reserve reserve){
		dummy = reserve;
		dummy.reserveId = 1;
		dummy.reservationNumber = "R00001";
		return dummy;
	}
        
	public boolean updateReserve(Reserve reserve){	//modify & cancel
		if(reserve!=null){
			dummy = reserve;
			return true;
		}else{
			return false;
		}
	}
	public boolean deleteReserve(int reserveId){
		if(reserveId!=dummy.reserveId){
			dummy = null;
			return true;
		}else{
			return false;
		}
	}
	public Reserve getReserve(int reserveId){
		if(reserveId==dummy.reserveId){
			return dummy;
		}else{
			return null;
		}
	}
	public Reserve getReserve(String ReservationNumber){
		if(dummy!=null && dummy.reservationNumber!=null && dummy.reservationNumber.compareTo(ReservationNumber)==0){
			return dummy;
		}else{
			return null;
		}
	}
	public ArrayList<Reserve> searchReserve(Reserve reserve){
            Reserve DummReserve1 = new Reserve();
            DummReserve1.reservationNumber="1234";
            DummReserve1.type="Hello";       
            
            Reserve DummReserve2 = new Reserve();
            DummReserve2.reservationNumber="12345";
            DummReserve2.type="Hello12345";  

            Reserve DummReserve3 = new Reserve();
            DummReserve3.reservationNumber="123423";
            DummReserve3.type="Hello123423";  

            Reserve DummReserve4 = new Reserve();
            DummReserve4.reservationNumber="99999";
            DummReserve4.type="Hello99999";  
            
            
            ArrayList<Reserve> sample = new ArrayList<Reserve>();
            sample.add(DummReserve1);
            sample.add(DummReserve2);
            sample.add(DummReserve3);
            sample.add(DummReserve4);

	return sample;
	}
        
        
        

        
        
}
