package ControlObjects;

import entity.ReservationInfo;
import java.util.ArrayList;

public class ReserveCtrl {

    public ReservationInfo createReserve(ReservationInfo reserve) {
        return null;
    }

    public boolean updateReserve(ReservationInfo reserve) {	//modify & cancel
        return true;
    }

    public boolean deleteReserve(int reserveId) {
        return false;
    }

    public ReservationInfo getReserve(int reserveId) {
        return null;
    }

    public ReservationInfo getReserve(String ReservationNumber) {
        return null;
    }

    public ArrayList<ReservationInfo> searchReserve(ReservationInfo reserve) {
        ArrayList<ReservationInfo> sample = new ArrayList<ReservationInfo>();
        return sample;
    }

}
