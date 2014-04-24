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

public class ReserveCtrl {

    public Reservation createReserve(Reservation reserve) {
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

    public String createReservationNumber(Reservation reserve) {
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

    public boolean updateReserve(Reservation reserve) {	//modify & cancel
        ArrayList<ReserveEquipment> eqList = null;
        ArrayList<BuyInsurance> inList = null;

        ReservationInfoDao resInfDAO = new ReservationInfoDao();
        ReserveEquipmentDao resEqmDAO = new ReserveEquipmentDao();
        BuyInsuranceDao buyInsDAO = new BuyInsuranceDao();

        //clear all previous equipment & insurance
        eqList = resEqmDAO.findReserveEquipmentByReservationId(reserve.getReservationInfoId());
        inList = buyInsDAO.findBuyInsuranceByReservationId(reserve.getReservationInfoId());
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
            return  false;
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

    public boolean deleteReserve(int reserveId) {
        return false;
    }

    public Reservation getReserve(int reserveId) {
        return null;
    }

    private Reservation getCompleteReservation(ReservationInfo reserveInfo) {
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

    public Reservation getReserve(String ReservationNumber) {
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

    public ArrayList<Reservation> searchReserve(Reservation reserve) {
        ArrayList<Reservation> sample = new ArrayList<>();
        return sample;
    }

    public ArrayList<Reservation> searchReserveBetween(Date pickUpTime, Date returnTime, Branch branch) {
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

    public ArrayList<Reservation> searchReserveForRent(Date FromReserveTime, Date ToReserveTime, Branch branch, String ReservationNo) {
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
}
