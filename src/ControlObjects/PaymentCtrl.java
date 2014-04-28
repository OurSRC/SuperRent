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
import entity.Customer;
import entity.Payment;
import entity.PaymentItem;
import entity.Rent;
import entity.Return;
import finance.Price;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provide data access and control function of Payment entity for UI.
 * </p>
 */
public class PaymentCtrl {

    private final static int PAYMENT_TO_POINT_RATE = 5 * 100;     //cent

    private Payment pay;
    private ArrayList<PaymentItem> payItem;

    private PaymentDao payDao = null;
    private PaymentItemDao itemDao = null;

    private int customerId;
    private Return returnInfo;

    public PaymentCtrl() {
        pay = null;
        payItem = new ArrayList<>();
        payDao = new PaymentDao();
        itemDao = new PaymentItemDao();
        customerId = 0;
        returnInfo = null;
    }

    public PaymentCtrl(int CustomerID, String Title, String CreditCardNum, Date Expire, String HolderName) {
        this();
        pay = new Payment(CustomerID, Title, 0, CreditCardNum, new Date());
        customerId = CustomerID;

        boolean suc = CreditCardCtrl.create(CreditCardNum, Expire, HolderName);
    }

    public PaymentCtrl(int CustomerID, String Title) {
        this();
        pay = new Payment(CustomerID, Title, 0, null, new Date());
        customerId = CustomerID;
    }

    /**
     * Setup credit card to use
     *
     * @param CreditCardNum
     * @param Expire
     * @param HolderName
     * @return True if credit card has been added. False otherwise.
     */
    public boolean useCreditCard(String CreditCardNum, Date Expire, String HolderName) {
        boolean suc = CreditCardCtrl.create(CreditCardNum, Expire, HolderName);
        pay.setCreditCardNo(CreditCardNum);
        return suc;
    }

    /**
     * Proceed payment, add record to database.
     *
     * @return Payment object inserted into database on success. Or null if not
     * success.
     */
    public Payment proceed() {
        boolean suc = false;
        int moneyTotal = 0;
        int moneyForPoint = 0;
        try {
            suc = payDao.add(pay);
        } catch (DaoException ex) {
            Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return null;
        }
        if (suc) {
            for (PaymentItem item : payItem) {
                item.setPaymentId(pay.getPaymentId());
                try {
                    itemDao.add(item);
                } catch (DaoException ex) {
                    Logger.getLogger(PaymentCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
                    return null;
                }
                moneyTotal += item.getQuantity() * item.getPrice();

                //reduse the point if using discount
                if (item.getType() == PaymentItem.ITEMTYPE.POINTEXCHANGE && returnInfo != null) {
                    CustomerCtrl cc = new CustomerCtrl();
                    Customer c = cc.getCustomerById(customerId);
                    Rent rt = RentCtrl.getRentByContractNumber(returnInfo.getContractNo());
                    Reservation rs = ReserveCtrl.getReserve(rt.getReservationInfold());
                    c.setPoint(c.getPoint() - (item.getQuantity() * FinanceCtrl.calculateMembershipPointForOneDay(rs)));
                }

                //new or extend membership
                if (item.getType() == PaymentItem.ITEMTYPE.MEMBERSHIP) {
                    CustomerCtrl cc = new CustomerCtrl();
                    Customer c = cc.getCustomerById(customerId);
                    cc.extendMembership(c, item.getQuantity(), new Date());
                } else {
                    moneyForPoint += item.getQuantity() * item.getPrice();
                }
            }

            if (customerId != 0) {  //add new point
                CustomerCtrl cc = new CustomerCtrl();
                Customer c = cc.getCustomerById(customerId);
                boolean member = cc.checkMembershipActive(c);
                int newPoint = moneyForPoint / PAYMENT_TO_POINT_RATE;
                if (member && newPoint > 0) {
                    c.setPoint(c.getPoint() + newPoint);
                    cc.updateCustomer(c);
                }
            }

            //write the record into return_table
            if (returnInfo != null) {
                returnInfo.setPaymentId(pay.getPaymentId());
                ReturnCtrl.createReturn(returnInfo);
            }
            return pay;
        } else {
            return null;
        }
    }

    /**
     * Add payment information to return record.
     *
     * @param returnInfo The corresponding return record.
     * @param usePoint True if customer want to use point to pay.
     * @param roadStar True if customer is a roadStar.
     * @return True on success, false otherwise.
     */
    public boolean addForReturn(Return returnInfo, boolean usePoint, boolean roadStar) {
        this.returnInfo = returnInfo;
        FinanceCtrl finCtrl = new FinanceCtrl();
        ArrayList<PaymentItem> list = finCtrl.calulateReturnCost(returnInfo, usePoint, roadStar);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                pay.setAmount(pay.getAmount() + list.get(i).getPrice() * list.get(i).getQuantity());
                addPayItem(list);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add pay member ship item.
     *
     * @param year Number of years to pay.
     * @param branchId The branch to handle this operation.
     * @return True on success, false otherwise.
     */
    public boolean addForMembershipFee(int year, int branchId) {
        FinanceCtrl finCtrl = new FinanceCtrl();
        PaymentItem item = finCtrl.calulateMembershipCost(year, branchId);
        if (item != null) {
            pay.setAmount(pay.getAmount() + item.getPrice() * item.getQuantity());
            addPayItem(item);
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

    public void addPayItem(PaymentItem payItem) {
        this.payItem.add(payItem);
    }

    public void addPayItem(ArrayList<PaymentItem> payItem) {
        this.payItem.addAll(payItem);
    }

    public Payment getByPaymentId() {
        return null;
    }

    public int getTotalAmount() {
        return pay.getAmount();
    }

    public String getTotalAmountText() {
        return Price.toText(getTotalAmount());
    }
}
