package ControlObjects;

import SystemOperations.ErrorMsg;
import VehicleManagement.Equipment;
import dao.DaoException;
import dao.EquipmentDao;
import dao.ReservationInfoDao;
import dao.ReserveEquipmentDao;
import dao.SupportEquipmentDao;
import entity.Branch;
import entity.SupportEquipment;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipmentCtrl {

    public Equipment createEquipment(Equipment equipment) {
        return null;
    }

    public boolean updateEquipment(Equipment equipment) {
        return false;
    }

    public boolean removeEquipment(int equipmentId) {
        return false;
    }

    public Equipment getEquipment(int equipmentId) {
        return null;
    }

    public ArrayList<Equipment> searchEquipment(Equipment equipment) {
        return null;
    }

    public boolean appendEquipmentTypeToVehicleClass(String EquipmentType, String VehicleClass) {
        return false;
    }

    public boolean removeEquipmentTypeFromVehicleClass(String EquipmentType, String VehicleClass) {
        return false;
    }

    public ArrayList<String> getEquipmentTypeByVehicleClass(String VehicleClass) {
        SupportEquipmentDao dao = new SupportEquipmentDao();
        ArrayList<SupportEquipment> list = null;
        try {
            list = dao.findByVehicleClass(VehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        ArrayList<String> ans = new ArrayList<String>();
        for (SupportEquipment se : list) {
            ans.add(se.getEquipmentType());
        }
        return ans;
    }

    public ArrayList<String> getVehicleClassByEquipmentType(String EquipmentType) {
        SupportEquipmentDao dao = new SupportEquipmentDao();
        ArrayList<SupportEquipment> list = null;
        try {
            list = dao.findByEquipmentType(EquipmentType);
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        ArrayList<String> ans = new ArrayList<String>();
        for (SupportEquipment se : list) {
            ans.add(se.getVehicleClass());
        }
        return ans;
    }

    public ArrayList<String> getEquipmentType() {
        return null;
    }
    
    public boolean checkEquipmentAvailability(String EquipmentType, Date pickUpTime, Date returnTime, Branch branch) {
        EquipmentDao eqDAO = new EquipmentDao();
        ReservationInfoDao rInfoDao = new ReservationInfoDao();
        int have = 0;
        int rent = 0;
        try {
            have = eqDAO.countEquipment(new entity.Equipment(EquipmentType, entity.Equipment.STATUS.AVAILABLE, null, null, null, branch.getBranchID()));
            rent = rInfoDao.findReservationBetweenUsingEquipmentType(EquipmentType, pickUpTime, returnTime, branch).size();
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        if(have>rent)
            return true;
        else
            return false;
    }
}
