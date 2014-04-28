package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.ReservationInfoDao;
import dao.VehicleClassDao;
import dao.VehicleDao;
import entity.Branch;
import entity.Vehicle;
import entity.VehicleClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>This VehicleCtrl class provides data access and control function of user entity for UI</p> 
 * 
 */
public class VehicleCtrl {

    /**
     * This method find a {@link Vehicle} with given {@code vehicle}
     * @param vehicle vehicle
     * @return vehicle or null
     */
    public Vehicle createVehicle(Vehicle vehicle) {
        VehicleDao vehicleDAO = new VehicleDao();
        boolean suc = false;
        try {
            suc = vehicleDAO.add(vehicle);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        if (suc) {
            Vehicle theVehicle = null;
            try {
                theVehicle = vehicleDAO.findByPlateNo(vehicle.getPlateNo());
            } catch (DaoException ex) {
                Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
            }
            return theVehicle;
        } else {
            return null;
        }
    }

    /**
     * This method find a boolean value with given {@code vehicle}
     * @param vehicle
     * @return true or false
     */
    public boolean updateVehicle(Vehicle vehicle) {
        VehicleDao vehicleDAO = new VehicleDao();
        boolean suc = false;
        try {
            suc = vehicleDAO.update(vehicle);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return suc;
    }

    /**
     * This method find a boolean value of remove vehicle with given {@code vehicleNo}
     * @param vehicleNo
     * @return true or false
     */
    public boolean removeVehicle(int vehicleNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByVehicleNo(vehicleNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if (theVehicle != null) {
            try {
                return vehicleDAO.delete(theVehicle);
            } catch (DaoException ex) {
                Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method find vehicle with given {@code vehicleNo}
     * @param vehicleNo The number of vehicle
     * @return Vehicle
     */
    public Vehicle getVehicleByVehicleNo(int vehicleNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByVehicleNo(vehicleNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return theVehicle;
    }

    /**
     * This method find vehicle with given {@code plateNo}
     * @param plateNo The number of plate
     * @return Vehicle
     */
    public Vehicle getVehicleByPlateNo(String plateNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByPlateNo(plateNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return theVehicle;
    }

    /**
     * This method searches vehicle with given {@code vehicle}
     * @param vehicle
     * @return array list of vehicle
     */
    public ArrayList<Vehicle> searchVehicle(Vehicle vehicle) {
        VehicleDao vehicleDAO = new VehicleDao();
        ArrayList<Vehicle> getList = null;
        try {
            getList = vehicleDAO.findByInstance(vehicle);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        //ErrorMsg.setLastError(ErrorMsg.ERROR_NOT_SUPPORT_YET);
        return getList;
    }

    /**
     * This method searches idle vehicle with given {@code VehivleClassName, branch}
     * @param VehivleClassName
     * @param branch
     * @return array list of vehicle
     */
    public ArrayList<Vehicle> searchIdleVehicles(String VehivleClassName, Branch branch) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBranchId(branch.getBranchID());
        vehicle.setStatus(Vehicle.STATUS.FORRENT);
        vehicle.setRentStatus(Vehicle.RENTSTATUS.IDLE);
        if (VehivleClassName != null) {
            vehicle.setClassName(VehivleClassName);
        }
        return searchVehicle(vehicle);
    }

    /**
     * This method searches vehicle for sale with given {@code VehivleClassName, branch}
     * @param VehicleClassName
     * @param branch
     * @return array list of vehicle
     */
    public ArrayList<Vehicle> searchForSaleVehicles(String VehicleClassName, Branch branch) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBranchId(branch.getBranchID());
        vehicle.setSellStatus(Vehicle.SELLSTATUS.FORSALE);
        vehicle.setClassName(VehicleClassName);
        return searchVehicle(vehicle);
    }

    /**
     * This method searches available vehicle with given {@code type, pickUpTime, returnTime, branch}
     * @param type type of vehicle class
     * @param pickUpTime
     * @param returnTime
     * @param branch
     * @return array list of available vehicle classes
     */
    public ArrayList<String> getAvailableVehicleClasses(VehicleClass.TYPE type, Date pickUpTime, Date returnTime, Branch branch) {
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<String> list = getSubVehicleType(type);
        for (String vClass : list) {
            if (checkVehicleAvailability(vClass, pickUpTime, returnTime, branch)) {
                ans.add(vClass);
            }
        }
        return ans;
    }

    /**
     * This method checks available vehicle with given {@code vehicleClass, pickUpTime, returnTime, branch}
     * @param vehicleClass
     * @param pickUpTime
     * @param returnTime
     * @param branch
     * @return true or false
     */
    public boolean checkVehicleAvailability(String vehicleClass, Date pickUpTime, Date returnTime, Branch branch) {
        try {
            VehicleDao vDAO = new VehicleDao();
            ReservationInfoDao rDAO = new ReservationInfoDao();
            int vCount = vDAO.countVehicle(new Vehicle(null, null, null, 0, branch.getBranchID(), Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, null, vehicleClass, 0));
            int rCount = rDAO.findReservationBetween(vehicleClass, pickUpTime, returnTime, branch).size();
            if (vCount > rCount) {
                return true;
            } else {
                return false;
            }
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return false;
    }

    /**
     * This method creates vehicle class with given {@code vehicleClass}
     * @param vehicleClass
     * @return true or false
     */
    public boolean createVehicleClass(VehicleClass vehicleClass) {
        VehicleClassDao classDAO = new VehicleClassDao();
        boolean suc = false;
        try {
            suc = classDAO.add(vehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return suc;
    }

    /**
     * This method delete vehicle class with given {@code vehicleClass}
     * @param vehicleClass
     * @return true or false
     */
    public boolean deleteVehicleClass(VehicleClass vehicleClass) {
        VehicleClassDao classDAO = new VehicleClassDao();
        boolean suc = false;
        try {
            suc = classDAO.delete(vehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return suc;
    }

    /**
     * This method update vehicle class with given {@code vehicleClass}
     * @param vehicleClass
     * @return true or false
     */
    public boolean updateVehicleClass(VehicleClass vehicleClass) {
        VehicleClassDao classDAO = new VehicleClassDao();
        boolean suc = false;
        try {
            suc = classDAO.update(vehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return suc;
    }

    /**
     * This method find vehicle class with given {@code vehicclassNameleClass}
     * @param className
     * @return VehicleClass
     */
    public VehicleClass findVehicleClass(String className) {
        VehicleClassDao classDAO = new VehicleClassDao();
        VehicleClass theClass = null;
        try {
            theClass = classDAO.findByName(className);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return theClass;
    }

    /**
     * This method gets vehicle type
     * @return array list of type
     */
    public ArrayList<String> getVehicleType() {
        ArrayList<String> ans = getCarType();
        ans.addAll(getTruckType());
        return ans;
    }

    /**
     * This method get sub vehicle type with given {@code type}
     * @param type
     * @return array list of SubVehicleType
     */
    public ArrayList<String> getSubVehicleType(VehicleClass.TYPE type) {
        VehicleClassDao classDAO = new VehicleClassDao();
        ArrayList<VehicleClass> classList = new ArrayList<VehicleClass>();
        try {
            classList = classDAO.findByClass(type);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            classList = null;
        }
        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < classList.size(); i++) {
            ans.add(classList.get(i).getClassName());
        }
        return ans;
    }

    
    public ArrayList<String> getCarType() {
        return getSubVehicleType(VehicleClass.TYPE.Car);
    }

    public ArrayList<String> getTruckType() {
        return getSubVehicleType(VehicleClass.TYPE.Truck);
    }

    /**
     * This method find vehicle type by class name with given {@code className}
     * @param className the class name of the vehicle
     * @return VehicleClass.TYPE
     */
    public VehicleClass.TYPE getVehicleTypeByClassName(String className) {
        VehicleClassDao classDAO = new VehicleClassDao();
        VehicleClass vehicleClass = null;
        try {
            vehicleClass = classDAO.findByName(className);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        if (vehicleClass != null) {
            return vehicleClass.getVehicleType();
        } else {
            return null;
        }
    }
}
