package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.ReturnDao;
import entity.Rent;
import entity.Return;
import entity.Vehicle;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>This ReturnCtrl class provides data access and control function of user entity for UI</p> 
 */
public class ReturnCtrl {

    /**
     * This method create return a {@link Return} with given {@code returnInfo}
     * @param returnInfo
     * @return returnInfo return information
     */
    static public Return createReturn(Return returnInfo) {
        ReturnDao dao = new ReturnDao();
        try {
            boolean suc = dao.add(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return null;
        }
        
        //set the vehicle as idle again
        setVehicleForRentStatus(returnInfo, Vehicle.RENTSTATUS.IDLE);
        
        return returnInfo;
    }

    /**
     * This method create return a {@link Return} with given {@code ContractNum, Fuel, Odometer, StaffId, PaymentId, damageCost}
     * @param ContractNum
     * @param Fuel
     * @param Odometer
     * @param StaffId
     * @param PaymentId
     * @param damageCost
     * @return
     */
    static public Return createReturn(int ContractNum, int Fuel, int Odometer, int StaffId, int PaymentId, int damageCost) {
        Return rtn = new Return(ContractNum, new Date(), Fuel, Odometer, StaffId, PaymentId, damageCost);
        return createReturn(rtn);
    }

    /**
     * This method provides update return true or false with given {@code returnInfo}
     * @param returnInfo return information
     * @return true or false
     */
    static public boolean updateReturn(Return returnInfo) {
        ReturnDao dao = new ReturnDao();
        boolean suc = false;
        try {
            suc = dao.update(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return suc;
    }

    /**
     * This method provides boolean value of delete return with given {@code returnId}
     * @param returnId Id of return
     * @return true or false
     */
    static public boolean deleteReturn(int returnId) {
        //ReturnDao dao = new ReturnDao();
        return false;
    }

    /**
     * This method create array list of {@link Return} with given {@code returnInfo}
     * @param returnInfo
     * @return array list of return
     */
    static public ArrayList<Return> searchReturn(Return returnInfo) {
        ReturnDao dao = new ReturnDao();
        ArrayList<Return> list = null;
        try {
            list = dao.findByInstance(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return list;
    }

    /**
     * This method searches the first return of {@link Return} with given {@code returnInfo}
     * @param returnInfo
     * @return Return
     */
    static public Return searchFirstReturn(Return returnInfo) {
        ReturnDao dao = new ReturnDao();
        try {
            return dao.findFirstInstance(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }

    /**
     * This method gets a {@link return} with given {@code contractNum}
     * @param contractNum
     * @return search First Return
     */
    static public Return getReturnByContractNumber(int contractNum) {
        Return rtn = new Return();
        rtn.setContractNo(contractNum);
        return searchFirstReturn(rtn);
    }

    /**
     * This method gets a {@link Return} with given {@code paymentId}
     * @param paymentId
     * @return search First Return
     */
    static public Return getReturnByPaymentId(int paymentId) {
        Return rtn = new Return();
        rtn.setPaymentId(paymentId);
        return searchFirstReturn(rtn);
    }

    /**
     * This method gets a {@link Return} by date with given {@code d, branchId}
     * @param d
     * @param branchId id of the branch
     * @return array list of return
     */
    static public ArrayList<Return> getRerurnsByDate(Date d, int branchId) {
        return getReturnsBetweenDates(d, d, branchId);
    }

    /**
     * This method gets a {@link Return} by date with given {@code start, end, branchId}
     * @param start
     * @param end
     * @param branchId id of the branch
     * @return array list of return, which between start date and end date
     */
    static public ArrayList<Return> getReturnsBetweenDates(Date start, Date end, int branchId) {
        ReturnDao returnDao = new ReturnDao();
        try {
            return returnDao.findBetween(start, end, branchId);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }
   
    static private boolean setVehicleForRentStatus(Return returnInfo, Vehicle.RENTSTATUS status){
        RentCtrl rentCtrl = new RentCtrl();
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        if(returnInfo==null)
            return false;
        Rent rent = rentCtrl.getRentByContractNumber(returnInfo.getContractNo());
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
