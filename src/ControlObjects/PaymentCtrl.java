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
 *
 * @author Elitward
 */
public class PaymentCtrl {
    private final static int PAYMENT_TO_POINT_RATE = 5;

    private Payment pay;
    private ArrayList<PaymentItem> payItem;

    private PaymentDao payDao = null;
    private PaymentItemDao itemDao = null;
    
    private int  customerId;
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
    
    public boolean useCreditCard(String CreditCardNum, Date Expire, String HolderName){
        boolean suc = CreditCardCtrl.create(CreditCardNum, Expire, HolderName);
        pay.setCreditCardNo(CreditCardNum);
        return suc;
    }
    
    public Payment proceed() {
        boolean suc = false;
        int total = 0;
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
                total += item.getQuantity() * item.getPrice();
                
                //reduse the point if using discount
                if(item.getType()==PaymentItem.ITEMTYPE.POINTEXCHANGE && returnInfo!=null){
                    CustomerCtrl cc = new CustomerCtrl();
                    Customer c = cc.getCustomerById(customerId);
                    Rent rt = RentCtrl.getRentByContractNumber( returnInfo.getContractNo() );
                    Reservation rs =  ReserveCtrl.getReserve( rt.getReservationInfold() );
                    c.setPoint(c.getPoint() - (item.getQuantity()*FinanceCtrl.calculateMembershipPointForOneDay(rs)) );
                }
                
                //new or extend membership
                if(item.getType()==PaymentItem.ITEMTYPE.MEMBERSHIP){
                    CustomerCtrl cc = new CustomerCtrl();
                    Customer c = cc.getCustomerById(customerId);
                    cc.extendMembership(c, item.getQuantity(), new Date());
                }
            }
            
            if(customerId!=0){  //add new point
                CustomerCtrl cc = new CustomerCtrl();
                Customer c = cc.getCustomerById(customerId);
                boolean member = cc.checkMembershipActive(c);
                int newPoint = total/PAYMENT_TO_POINT_RATE;
                if(member && newPoint>0){
                    c.setPoint( c.getPoint() +  newPoint);
                    cc.updateCustomer(c);
                }
            }
            return pay;
        } else {
            return null;
        }
    }

    public boolean addForReturn(Return returnInfo, boolean usePoint) {
        this.returnInfo = returnInfo;
        FinanceCtrl finCtrl = new FinanceCtrl();
        Integer sum = 0;
        ArrayList<PaymentItem> list = finCtrl.calulateReturnCost(returnInfo, usePoint, sum);
        if (list != null && list.size() > 0) {
            for(int i =0 ;i < list.size();i++)
            {
            pay.setAmount(pay.getAmount()+ list.get(i).getPrice());
            addPayItem(list);
            }
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
            pay.setAmount(pay.getAmount()+sum);
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
    
    public int getTotalAmount(){
        return pay.getAmount();
    }
    
    public String getTotalAmountText(){
        return Price.toText(getTotalAmount());
    }
}
