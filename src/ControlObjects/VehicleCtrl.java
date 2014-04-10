package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.VehicleClassDao;
import dao.VehicleDao;
import entity.Vehicle;
import entity.VehicleClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleCtrl {

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

    public boolean removeVehicle(int vehicleNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByVehicleNo(vehicleNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if( theVehicle!=null ){
            try {
                return vehicleDAO.delete(theVehicle);
            } catch (DaoException ex) {
                Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
                return false;
            }
        }else{
            return false;
        }
    }

    public Vehicle getVehicleByVehicleNo(int vehicleNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByVehicleNo(vehicleNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return null;
    }

    public Vehicle getVehicleByPlateNo(String plateNo) {
        VehicleDao vehicleDAO = new VehicleDao();
        Vehicle theVehicle = null;
        try {
            theVehicle = vehicleDAO.findByPlateNo(plateNo);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return null;
    }

    public ArrayList<Vehicle> searchVehicle(Vehicle vehicle) {
        VehicleDao vehicleDAO = new VehicleDao();
        ArrayList<Vehicle> getList = null;
        try {
            getList = vehicleDAO.find(vehicle);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        //ErrorMsg.setLastError(ErrorMsg.ERROR_NOT_SUPPORT_YET);
        return getList;
    }

    public ArrayList<String> getVehicleAvailability(String vehicleType, Date pickUpTime, Date returnTime) {
        VehicleDao vehicleDAO = new VehicleDao();
        ArrayList<String> ans = new ArrayList<String>();
        ans.add("ECONOMY");
        ans.add("COMPACT");
        ans.add("MIDSIZE");
        return ans;
    }
    
    public boolean createVehicleClass(VehicleClass vehicleClass){
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

    public boolean deleteVehicleClass(VehicleClass vehicleClass){
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
    
    public boolean updateVehicleClass(VehicleClass vehicleClass){
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

    public VehicleClass findVehicleClass(String className){
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
    
    public ArrayList<String> getVehicleType() {
        ArrayList<String> ans = getCarType();
        ans.addAll(getTruckType());
        return ans;
    }

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
        for(int i=0; i<classList.size(); i++){
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
}
