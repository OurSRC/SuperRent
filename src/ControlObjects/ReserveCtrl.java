package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.BuyInsuranceDao;
import dao.DaoException;
import dao.ReservationInfoDao;
import dao.ReserveEquipmentDao;
import entity.Branch;
import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * ReserveCtrl is one of the components in Logical Control, which connect UI and
 * database by manipulating Reservation object.
 * </p>
 */
public class ReserveCtrl {

    /**
     * Create a reservation record in database.
     *
     * @param reserve The reservation object to be added to database.
     * @return Added {@link Reservation} object.
     */
    static public Reservation createReserve(Reservation reserve) {
        if (reserve == null) {
            return null;
        }

        if (reserve.getReserveTime() == null) {
            reserve.setReserveTime(new Date());
        }

        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        EquipmentCtrl equipCtrl = new EquipmentCtrl();
        Branch branch = reserve.matchBranch();
        boolean available = vehicleCtrl.checkVehicleAvailability(reserve.getVehicleClass(), reserve.getPickupTime(), reserve.getReturnTime(), branch);
        if (!available) {
            return null;
        }
        for (ReserveEquipment re : reserve.getReserveEquipment()) {
            available = equipCtrl.checkEquipmentAvailability(re.getEquipmentType(), reserve.getPickupTime(), reserve.getReturnTime(), branch);
            if (!available) {
                return null;
            }
        }

        //all available then proceed the reservation
        ReservationInfoDao reInDAO = new ReservationInfoDao();
        ReserveEquipmentDao reEqDAO = new ReserveEquipmentDao();
        BuyInsuranceDao byInDAO = new BuyInsuranceDao();
        ReservationInfo completeInfo = null;

        ReserveEquipmentDao reDAO = new ReserveEquipmentDao();
        BuyInsuranceDao biDAO = new BuyInsuranceDao();

        try {
            reserve.getReserveInfo().setReservationStatus(ReservationInfo.STATUS.PENDING);  //set pending status
            completeInfo = reInDAO.makeReservationInfo(reserve.getReserveInfo());

            for (ReserveEquipment reEq : reserve.getReserveEquipment()) {
                ReserveEquipment reEqGet = reEqDAO.makeReserveEquipment(completeInfo.getReservationInfoId(), reEq.getEquipmentType());
                reDAO.add(reEqGet);
            }

            for (BuyInsurance byIn : reserve.getReserveInsurance()) {
                BuyInsurance byInGet = byInDAO.makeBuyInsurance(completeInfo.getReservationInfoId(), byIn.getInsuranceName());
                biDAO.add(byInGet);
            }
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_DATA_OCCUPIED);
            return null;
        }

        reserve.setReserveInfo(completeInfo);

        return reserve;
    }

    /**
     * Generate reservation number for a {@link Reservation} object.
     *
     * @param reserve The {@link Reservation} object to generate a reservation
     * number.
     * @return The generated reservation number on success, false otherwise.
     */
    static public String createReservationNumber(Reservation reserve) {
        int id = reserve.getReservationInfoId();
        if (id > 0) {
            String rNo;
            rNo = reserve.getReserveInfo().getReservationNo();
            if (rNo == null) {
                rNo = "R" + id + reserve.getBranchId() + reserve.getCustomerId();
                reserve.getReserveInfo().setReservationNo(rNo);
                ReservationInfoDao resInfDAO = new ReservationInfoDao();
                try {
                    resInfDAO.update(reserve.getReserveInfo());
                } catch (DaoException ex) {
                    Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
                    return null;
                }
            }
            return rNo;
        } else {
            return null;
        }
    }

    /**
     * Update reservation with in database by {@link reservation} object with
     * same reservationInfoId.
     *
     * @param reserve The {@link reservation} object with information to update.
     * @return True on success, false otherwise.
     */
    static public boolean updateReserve(Reservation reserve) {	//modify & cancel
        ArrayList<ReserveEquipment> eqList = null;
        ArrayList<BuyInsurance> inList = null;

        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ReserveEquipmentDao resEqmDAO = new ReserveEquipmentDao();
        BuyInsuranceDao buyInsDAO = new BuyInsuranceDao();

        try {
            //clear all previous equipment & insurance
            eqList = resEqmDAO.findReserveEquipmentByReservationId(reserve.getReservationInfoId());
            inList = buyInsDAO.findBuyInsuranceByReservationId(reserve.getReservationInfoId());
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            for (ReserveEquipment re : eqList) {
                resEqmDAO.delete(re);
            }
            for (BuyInsurance in : inList) {
                buyInsDAO.delete(in);
            }
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
            return false;
        }

        eqList = reserve.getReserveEquipment();
        inList = reserve.getReserveInsurance();

        try {
            resInfDAO.update(reserve.getReserveInfo());
            for (ReserveEquipment re : eqList) {
                resEqmDAO.add(re);
            }
            for (BuyInsurance in : inList) {
                buyInsDAO.add(in);
            }
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return false;
        }
        return true;
    }

    /**
     * Update database and mark a reservation as canceled.
     *
     * @param reserve The {@link Reservation} to cancel.
     * @return True on success, false otherwise.
     */
    static public boolean cancelReserve(Reservation reserve) {
        ReservationInfoDao resInfoDAO = new ReservationInfoDao();
        ReservationInfo resInfo = reserve.getReserveInfo();
        resInfo.setReservationStatus(ReservationInfo.STATUS.CANCELED);
        boolean suc = false;
        try {
            suc = resInfoDAO.update(resInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return suc;
    }

    /**
     * Search {@link Reservation} by reservationInfoId.
     *
     * @param reserveId The reservationInfoId to search.
     * @return {@link Reservation} with matching reservationInfoId, or null if
     * not found.
     */
    static public Reservation getReserve(int reserveId) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ReservationInfo reserveInfo = new ReservationInfo();
        reserveInfo.setReservationInfoId(reserveId);
        try {
            reserveInfo = resInfDAO.findFirstInstance(reserveInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return getCompleteReservation(reserveInfo);
    }

    static private Reservation getCompleteReservation(ReservationInfo reserveInfo) {
        if (reserveInfo != null && reserveInfo.getReservationInfoId() != 0) {
            ReserveEquipmentDao resEqmDAO = new ReserveEquipmentDao();
            BuyInsuranceDao resInqDAO = new BuyInsuranceDao();
            ArrayList<ReserveEquipment> reserveEqmt = null;
            try {
                reserveEqmt = resEqmDAO.findByInstance(new ReserveEquipment(reserveInfo.getReservationInfoId(), null, 0, 0));
            } catch (DaoException ex) {
                Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            }
            ArrayList<BuyInsurance> reserveInsn = null;
            try {
                reserveInsn = resInqDAO.findByInstance(new BuyInsurance(null, reserveInfo.getReservationInfoId(), 0, 0, 0));
            } catch (DaoException ex) {
                Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            }
            Reservation reservation = new Reservation(reserveInfo, reserveEqmt, reserveInsn);
            return reservation;
        } else {
            return null;
        }
    }

    /**
     * Search {@link Reservation} by ReservationNumber.
     *
     * @param ReservationNumber The ReservationNumber to search with.
     * @return {@link Reservation} with matching {@code ReservationNumber}, or
     * null if not found.
     */
    static public Reservation getReserve(String ReservationNumber) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ReservationInfo reserveInfo = null;
        try {
            reserveInfo = resInfDAO.findByReservationNo(ReservationNumber);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return getCompleteReservation(reserveInfo);
    }

    /*
     static public ArrayList<Reservation> searchReserve(Reservation reserve) {
     ArrayList<Reservation> sample = new ArrayList<>();
     return sample;
     }
     */
    /**
     * Search not rented {@link Reservation}.
     *
     * @return ArrayList of not rented {@link Reservation} objects.
     */
    static public ArrayList<ReservationInfo> getUnrentedReservations() {
        ReservationInfo reservationInfo = new ReservationInfo();
        reservationInfo.setReservationStatus(ReservationInfo.STATUS.PENDING);
        ReservationInfoDao dao = new ReservationInfoDao();
        ArrayList<ReservationInfo> list = null;
        try {
            list = dao.findByInstance(reservationInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return list;
    }

    /**
     * Search {@link Reservation} objects whose pickup time and return time
     * overlap with given time period for a branch.
     *
     * @param pickUpTime Pickup time of given time period.
     * @param returnTime Return time of given time period.
     * @param branch {@link Branch} to search with.
     * @return ArrayList of all {@link Reservation} that overlap with given time
     * period.
     */
    static public ArrayList<Reservation> searchReserveBetween(Date pickUpTime, Date returnTime, Branch branch) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ArrayList<Reservation> list = new ArrayList<>();
        ArrayList<ReservationInfo> resInfList = null;
        try {
            resInfList = resInfDAO.findReservationBetween(pickUpTime, returnTime, branch);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        for (ReservationInfo a : resInfList) {
            list.add(getCompleteReservation(a));
        }
        return list;
    }

    /**
     * Search {@link Reservation} that made between a time period which are
     * available to be rent.
     *
     * @param FromReserveTime Start of time period to search.
     * @param ToReserveTime End of time period to search.
     * @param branch {@link Branch} to search.
     * @param ReservationNo ReservationNo to search with.
     * @return ArrayList of matching {@link Reservation} objects.
     */
    static public ArrayList<Reservation> searchReserveForRent(Date FromReserveTime, Date ToReserveTime, Branch branch, String ReservationNo) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ArrayList<Reservation> list = new ArrayList<>();
        ArrayList<ReservationInfo> resInfList = null;
        try {
            resInfList = resInfDAO.findReservationUsingReserveTime(FromReserveTime, ToReserveTime, branch, ReservationNo);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        for (ReservationInfo a : resInfList) {
            list.add(getCompleteReservation(a));
        }
        return list;
    }

    /**
     * Search pending reservation to be pick up for a branch in a day.
     *
     * @param branchId Id for branch to search. Search all branches if this
     * equals to 0.
     * @param day The date that pending reservations should be pick up. Search
     * all pending if is null.
     * @return ArrayList of reservation satisfy requirement.
     */
    static public ArrayList<Reservation> searchPendingReservation(int branchId, Date day) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ArrayList<Reservation> list = new ArrayList<>();
        ArrayList<ReservationInfo> resInfList = null;

        try {
            resInfList = resInfDAO.searchPending(branchId, day);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        for (ReservationInfo a : resInfList) {
            list.add(getCompleteReservation(a));
        }
        return list;
    }

    /**
     * Search {@link Reservation} that has been rented and should be returned on
     * specific day for a branch.
     *
     * @param branchId BranchId of the {@link Branch} to search.
     * @param day The date to search with.
     * @return ArrayList of matching {@link Reservation} objects.
     */
    static public ArrayList<Reservation> searchNotReturnedReservation(int branchId, Date day) {
        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ArrayList<Reservation> list = new ArrayList<>();
        ArrayList<ReservationInfo> resInfList = null;

        try {
            resInfList = resInfDAO.searchNotReturned(branchId, day);
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }

        for (ReservationInfo a : resInfList) {
            list.add(getCompleteReservation(a));
        }
        return list;
    }
}
