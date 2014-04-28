package ControlObjects;

import SystemOperations.ErrorMsg;
import entity.Equipment;
import dao.DaoException;
import dao.EquipmentDao;
import dao.ReservationInfoDao;
import dao.SupportEquipmentDao;
import entity.Branch;
import entity.SupportEquipment;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * CustomerCtrl is one of the components in Logical Control, get input from Database and output data to support User Interface, which operates Equipment entity object
 * </p>
 */
public class EquipmentCtrl {

    /**
     *This method create a new record of the equipment
     * @param equipment
     * @return The complete equipment
     */
    public Equipment createEquipment(Equipment equipment) {
        EquipmentDao equipmentDao = new EquipmentDao();
        boolean suc = false;
        try {
            suc = equipmentDao.add(equipment);
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        if (suc) {
            Equipment returnEquipment = null;
            try {
                returnEquipment = equipmentDao.findEquipmentById(equipment.getEquipmentId());
            } catch (DaoException ex) {
                Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
            }
            return returnEquipment;
        } else {
            return null;
        }
    }

    /**
     *This method update an equipment record (has not supported yet)
     * @param equipment
     * @return true for success, false for otherwise
     */
    public boolean updateEquipment(Equipment equipment) {
        return false;
    }

    /**
     *This method remove an equipment record (has not supported yet)
     * @param equipmentId
     * @return true for success, false for otherwise
     */
    public boolean removeEquipment(int equipmentId) {
        return false;
    }

    /**
     *This method find a {@link Equipment} with given {@code equipmentId}
     * @param equipmentId
     * @return the matching object or null
     */
    public Equipment getEquipment(int equipmentId) {
        return null;
    }

    /**
     *This method find all matching {@link Equipment} with given {@code equipment}
     * @param equipment
     * @return an array list of matching {@link Equipment} or null
     */
    public ArrayList<Equipment> searchEquipment(Equipment equipment) {
        return null;
    }

    /**
     *This method append Equipment Type To Vehicle Class (has not supported yet)
     * @param EquipmentType The specified {@code EquipmentType}
     * @param VehicleClass The specified {@code VehicleClass}
     * @return true for success, false for otherwise
     */
    public boolean appendEquipmentTypeToVehicleClass(String EquipmentType, String VehicleClass) {
        return false;
    }

    /**
     *This method remove Equipment Type From Vehicle Class (has not supported yet)
     * @param EquipmentType The specified {@code EquipmentType}
     * @param VehicleClass The specified {@code VehicleClass}
     * @return true for success, false for otherwise
     */
    public boolean removeEquipmentTypeFromVehicleClass(String EquipmentType, String VehicleClass) {
        return false;
    }

    /**
     *This method get Equipment Type By Vehicle Class
     * @param VehicleClass The specified {@code VehicleClass}
     * @return an array list string {@link String} of matching {@code VehicleClass}or null
     */
    public ArrayList<String> getEquipmentTypeByVehicleClass(String VehicleClass) {
        SupportEquipmentDao dao = new SupportEquipmentDao();
        ArrayList<SupportEquipment> list = null;
        try {
            list = dao.findByVehicleClass(VehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        ArrayList<String> ans = new ArrayList<>();
        for (SupportEquipment se : list) {
            ans.add(se.getEquipmentType());
        }
        return ans;
    }

    /**
     *This method get Vehicle Class By Equipment Type
     * @param EquipmentType The specified {@cide EquipmentType }
     * @return an array list string {@link String} of matching {@code EquipmentType}or null
     */
    public ArrayList<String> getVehicleClassByEquipmentType(String EquipmentType) {
        SupportEquipmentDao dao = new SupportEquipmentDao();
        ArrayList<SupportEquipment> list = null;
        try {
            list = dao.findByEquipmentType(EquipmentType);
        } catch (DaoException ex) {
            Logger.getLogger(EquipmentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        ArrayList<String> ans = new ArrayList<>();
        for (SupportEquipment se : list) {
            ans.add(se.getVehicleClass());
        }
        return ans;
    }

    /**
     *This method check Equipment Availability 
     * @param EquipmentType The specified {@code EquipmentType}
     * @param pickUpTime The specified {@code pickUpTime}
     * @param returnTime The specified {@code returnTime}
     * @param branch The specified {@code branch}
     * @return true for success, false for otherwise
     */
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
        return have > rent;
    }
}
