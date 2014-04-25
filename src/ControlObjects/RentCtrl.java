package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.RentDao;
import entity.Rent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentCtrl {

    public Rent createRent(Rent rent) {
        RentDao rentDAO = new RentDao();

        try {
            boolean suc = rentDAO.add(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return null;
        }
        return rent;
    }

    public Rent createRent(int ReservationId, int VehicleNo, int Fuel, int Odometer, String CreditCardNo, int StaffId) {
        Rent rent = new Rent(ReservationId, VehicleNo, Fuel, Odometer, CreditCardNo, StaffId, new Date());
        return createRent(rent);
    }

    public boolean updateRent(Rent rent) {
        RentDao rentDAO = new RentDao();

        boolean suc = false;
        try {
            suc = rentDAO.update(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return suc;
    }

    public boolean cancelRent(int rentId) {
        return false;
    }

    public boolean deleteRent(int rentId) {
        return false;
    }

    public ArrayList<Rent> searchRent(Rent rent) {
        return null;
    }

    public Rent getRent(String contractNumber) {
        return null;
    }

    public Rent getRent(Reservation reserve) {
        return null;
    }
}
