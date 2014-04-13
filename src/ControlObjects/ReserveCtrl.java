package ControlObjects;

import java.util.ArrayList;

public class ReserveCtrl {

    public Reservation createReserve(Reservation reserve) {
        return null;
    }

    public boolean updateReserve(Reservation reserve) {	//modify & cancel
        return true;
    }

    public boolean deleteReserve(int reserveId) {
        return false;
    }

    public Reservation getReserve(int reserveId) {
        return null;
    }

    public Reservation getReserve(String ReservationNumber) {
        return null;
    }

    public ArrayList<Reservation> searchReserve(Reservation reserve) {
        ArrayList<Reservation> sample = new ArrayList<Reservation>();
        return sample;
    }

}
