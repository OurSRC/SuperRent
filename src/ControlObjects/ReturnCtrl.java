package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.ReturnDao;
import entity.Return;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnCtrl {

    public Return createReturn(Return returnInfo) {
        ReturnDao dao = new ReturnDao();
        try {
            boolean suc = dao.add(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return null;
        }
        return returnInfo;
    }
    
    public Return createReturn(int ContractNum, int Fuel, int Odometer, int StaffId, int PaymentId){
        Return rtn = new Return(ContractNum, new Date(), 0, Fuel, Odometer, StaffId, PaymentId);
        return createReturn(rtn);
    }

    public boolean updateReturn(Return returnInfo) {
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

    public boolean deleteReturn(int returnId) {
        //ReturnDao dao = new ReturnDao();
        return false;
    }

    public ArrayList<Return> searchReturn(Return returnInfo) {
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
    
    public Return searchFirstReturn(Return returnInfo){
        ReturnDao dao = new ReturnDao();
        try {
            return dao.findFirstInstance(returnInfo);
        } catch (DaoException ex) {
            Logger.getLogger(ReturnCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return null;
    }

    public Return getReturnByContractNumber(int contractNum) {
        Return rtn = new Return();
        rtn.setContractNo(contractNum);
        return searchFirstReturn(rtn);
    }

    public Return getReturnByPaymentId(int paymentId) {
        Return rtn = new Return();
        rtn.setPaymentId(paymentId);
        return searchFirstReturn(rtn);
    }
}
