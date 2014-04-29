package Operate;

import Vehicle.VehicleCtrl;
import SystemOperations.ErrorMsg;
import Dao.DaoException;
import Vehicle.Vehicle;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * RentCtrl is one of the components in Logical Control, get input from Database
 * and output data to support User Interface.
 * </p>
 */
public class RentCtrl {

    /**
     * Add {@link Rent} entity to database.
     *
     * @param rent The rent entity to add.
     * @return Original rent entity on success.
     */
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

    /**
     * Create {@link Rent} object and add it to database.
     *
     * @param ReservationId ReservationId of the rent.
     * @param VehicleNo Vehicle number of rent.
     * @param Fuel Fuel level of the vehicle.
     * @param Odometer Odometer of the vehicle.
     * @param CreditCardNo Credit card number used in rent.
     * @param StaffId Id of the staff precess the rent.
     * @return Created rent object on success, null otherwise.
     */
    static public Rent createRent(int ReservationId, int VehicleNo, int Fuel, int Odometer, String CreditCardNo, int StaffId) {
        Rent rent = new Rent(ReservationId, VehicleNo, Fuel, Odometer, CreditCardNo, StaffId, new Date());
        return createRent(rent);
    }

    /**
     * Update the rent record in database with {@link Rent} object with same
     * primary key.
     *
     * @param rent Rent object with the same contract number as the rent record
     * to update in database.
     * @return True on success, false otherwise.
     */
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

    /**
     * Search {@link Rent} with populated fields of a {@link Rent} object.
     *
     * @param rent The rent object with populated fields to search with.
     * @return ArrayList of match result.
     */
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

    /**
     * Search {@link Rent} with populated fields of a {@link Rent} object, and
     * return the first matching record.
     *
     * @param rent The rent object with populated fields to search with.
     * @return The first matching rent object.
     */
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

    /**
     * Search {@link Rent} by its reservationInfoId.
     *
     * @param reservationInfoId The reservationInfoId to search with.
     * @return Matching rent object, null if not found.
     */
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

    /**
     * Search {@link Rent} by contract number.
     *
     * @param contractNumber The contractNumber to search with.
     * @return matching rent object, null if not found.
     */
    static public Rent getRentByContractNumber(int contractNumber) {
        Rent rent = new Rent();
        rent.setContractNo(contractNumber);
        return searchFirstRent(rent);
    }

    /**
     * Get {@link Rent} object from reservation.
     *
     * @param reserve The Reservation object to search with.
     * @return matching Rent object, false otherwise.
     */
    static public Rent getRent(Reservation reserve) {
        if (reserve == null) {
            return null;
        }
        Rent rent = new Rent();
        rent.setReservationInfold(reserve.getReservationInfoId());
        return searchFirstRent(rent);
    }

    /**
     * Get {@link Rent} by vehicle number.
     *
     * @param vehicleNo vehicleNumber to search with
     * @return Matching rent.
     */
    static public Rent getRent(int vehicleNo) {
        Rent rent = new Rent();
        rent.setVehicleNo(vehicleNo);
        return searchFirstRent(rent);
    }

    /**
     * Search {@link Rent} objects that rented on date {@code d} for branch
     * {@code branchId}.
     *
     * @param d The day to search
     * @param branchId The branchId of the Branch to search.
     * @return ArrayList of matching Rent objects.
     */
    static public ArrayList<Rent> getRentsByDate(Date d, int branchId) {
        return getRentByDates(d, d, branchId);
    }

    /**
     * Search {@link Rent} objects that rented between two dates for branch
     * {@code branchId}.
     *
     * @param start Start date of the search.
     * @param end End date of the search.
     * @param branchId BranchId of Branch to search.
     * @return ArrayList of matching Rent objects.
     */
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
        if (rent == null) {
            return false;
        }
        Vehicle vehicle = vehicleCtrl.getVehicleByVehicleNo(rent.getVehicleNo());
        if (vehicle == null) {
            return false;
        }
        vehicle.setRentStatus(status);
        boolean suc = vehicleCtrl.updateVehicle(vehicle);
        return suc;
    }
}
