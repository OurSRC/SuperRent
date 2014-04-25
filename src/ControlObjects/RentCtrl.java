package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.RentDao;
import entity.Rent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        RentDao DAO = new RentDao();
        ArrayList<Rent> list = null;
        try {
            list = DAO.findByInstance(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return list;
    }

    public Rent searchFirstRent(Rent rent) {
        RentDao DAO = new RentDao();
        try {
            return DAO.findFirstInstance(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }

    public Rent getRentByContractNumber(int contractNumber) {
        Rent rent = new Rent();
        rent.setContractNo(contractNumber);
        return searchFirstRent(rent);
    }

    public Rent getRent(Reservation reserve) {
        if (reserve == null) {
            return null;
        }
        Rent rent = new Rent();
        rent.setReservationInfold(reserve.getReservationInfoId());
        return searchFirstRent(rent);
    }

    public Rent getRent(int vehicleNo) {
        Rent rent = new Rent();
        rent.setVehicleNo(vehicleNo);
        return searchFirstRent(rent);
    }
    
    public ArrayList<Rent> getRentsByDate(Date d, int branchId) {
        return getRentByDates(d, d, branchId);
    }
    
    public ArrayList<Rent> getRentByDates(Date start, Date end, int branchId) {
        RentDao rentDao  = new RentDao();
        
        try {
            return rentDao.findBetween(start, end, branchId);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        
        return null;
    }
    
}
