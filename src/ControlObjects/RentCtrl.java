package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.RentDao;
import entity.Rent;
import entity.Vehicle;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentCtrl {

    static public Rent createRent(Rent rent) {
        RentDao rentDAO = new RentDao();

        try {
            boolean suc = rentDAO.add(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return null;
        }

        //set the vehicle as rent
        setVehicleForRentStatus(rent, Vehicle.RENTSTATUS.RENTED);
        return rent;
    }

    static public Rent createRent(int ReservationId, int VehicleNo, int Fuel, int Odometer, String CreditCardNo, int StaffId) {
        Rent rent = new Rent(ReservationId, VehicleNo, Fuel, Odometer, CreditCardNo, StaffId, new Date());
        return createRent(rent);
    }

    static public boolean updateRent(Rent rent) {
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

    static public boolean cancelRent(int rentId) {
        return false;
    }

    static public boolean deleteRent(int rentId) {
        return false;
    }

    static public ArrayList<Rent> searchRent(Rent rent) {
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

    static public Rent searchFirstRent(Rent rent) {
        RentDao DAO = new RentDao();
        try {
            return DAO.findFirstInstance(rent);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }
    
    static public Rent searchRentByReservationInfoId(int reservationInfoId) {
        RentDao DAO = new RentDao();
        try {
            return DAO.findByReservationInfo(reservationInfoId);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }

    static public Rent getRentByContractNumber(int contractNumber) {
        Rent rent = new Rent();
        rent.setContractNo(contractNumber);
        return searchFirstRent(rent);
    }

    static public Rent getRent(Reservation reserve) {
        if (reserve == null) {
            return null;
        }
        Rent rent = new Rent();
        rent.setReservationInfold(reserve.getReservationInfoId());
        return searchFirstRent(rent);
    }

    static public Rent getRent(int vehicleNo) {
        Rent rent = new Rent();
        rent.setVehicleNo(vehicleNo);
        return searchFirstRent(rent);
    }

    static public ArrayList<Rent> getRentsByDate(Date d, int branchId) {
        return getRentByDates(d, d, branchId);
    }

    static public ArrayList<Rent> getRentByDates(Date start, Date end, int branchId) {
        RentDao rentDao = new RentDao();

        try {
            return rentDao.findBetween(start, end, branchId);
        } catch (DaoException ex) {
            Logger.getLogger(RentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }

        return null;
    }

    static private boolean setVehicleForRentStatus(Rent rent, Vehicle.RENTSTATUS status) {
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        if(rent==null)
            return false;
        Vehicle vehicle = vehicleCtrl.getVehicleByVehicleNo(rent.getVehicleNo());
        if(vehicle==null)
            return false;
        vehicle.setRentStatus(status);
        boolean suc = vehicleCtrl.updateVehicle(vehicle);
        return suc;
    }
}
