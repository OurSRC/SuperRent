/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.PaymentDao;
import dao.PaymentItemDao;
import entity.Payment;
import entity.PaymentItem;
import entity.Return;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elitward
 */
public class PaymentCtrl {

    private Payment pay;
    private ArrayList<PaymentItem> payItem;

    private PaymentDao payDao = null;
    private PaymentItemDao itemDao = null;

    public PaymentCtrl() {
        pay = null;
        payItem = new ArrayList<>();
        payDao = new PaymentDao();
        itemDao = new PaymentItemDao();
    }

    public PaymentCtrl(int CustomerID, String Title, String CreditCardNum) {
        this();
        pay = new Payment(CustomerID, Title, 0, CreditCardNum, new Date());
    }

    public Payment create() {
        boolean suc = false;
        try {
            suc = payDao.add(pay);
        } catch (DaoException ex) {
            Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        if (suc) {
            for (PaymentItem item : payItem) {
                item.setPaymentId(pay.getPaymentId());
                try {
                    itemDao.add(item);
                } catch (DaoException ex) {
                    Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
                }
            }
            return pay;
        } else {
            return null;
        }
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    public boolean addForReturn(Return returnInfo, boolean usePoint) {
        FinanceCtrl finCtrl = new FinanceCtrl();
        Integer sum = 0;
        ArrayList<PaymentItem> list = finCtrl.calulateReturnCost(returnInfo, usePoint, sum);
        if (list != null && list.size() > 0) {
            addPayItem(list);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addForMembershipFee(int year, int branchId){
        FinanceCtrl finCtrl = new FinanceCtrl();
        Integer sum = 0;
        ArrayList<PaymentItem> list = finCtrl.calulateMembershipCost(year, branchId, sum);
        if (list != null && list.size() > 0) {
            addPayItem(list);
            return true;
        } else {
            return false;
        }
    }

    public boolean addItem(PaymentItem item) {
        return payItem.add(item);
    }

    public PaymentItem removeItem(int index) {
        PaymentItem item = payItem.remove(index);
        return item;
    }

    public boolean removeItem(PaymentItem item) {
        return payItem.remove(item);
    }

    public Payment getPay() {
        return pay;
    }

    public void setPay(Payment pay) {
        this.pay = pay;
    }

    public ArrayList<PaymentItem> getPayItem() {
        return payItem;
    }

    public void setPayItem(ArrayList<PaymentItem> payItem) {
        this.payItem = payItem;
    }

    public void addPayItem(ArrayList<PaymentItem> payItem) {
        this.payItem.addAll(payItem);
    }

    public Payment getByPaymentId() {
        return null;
    }
}
