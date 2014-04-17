package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.BranchDao;
import dao.BuyInsuranceDao;
import dao.DaoException;
import dao.ReservationInfoDao;
import dao.ReserveEquipmentDao;
import entity.Branch;
import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReserveCtrl {

    public Reservation createReserve(Reservation reserve) {
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

        try {
            completeInfo = reInDAO.makeReservationInfo(reserve.getReserveInfo());

            for (ReserveEquipment reEq : reserve.getReserveEquipment()) {
                ReserveEquipment reEqGet = reEqDAO.makeReserveEquipment(completeInfo.getReservationInfoId(), reEq.getEquipmentType());
            }

            for (BuyInsurance byIn : reserve.getReserveInsurance()) {
                BuyInsurance byInGet = byInDAO.makeBuyInsurance(completeInfo.getReservationInfoId(), byIn.getInsuranceName());
            }
        } catch (DaoException ex) {
            Logger.getLogger(ReserveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_DATA_OCCUPIED);
            return null;
        }
        
        reserve.setReserveInfo(completeInfo);

        return reserve;
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
