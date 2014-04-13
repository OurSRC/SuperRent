/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Date;

/**
 *
 * @author Jingchuan Chen
 */
public class Payment {

    /**
     * @return the paymentId
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the method
     */
    public PAYMETHOD getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(PAYMETHOD method) {
        this.method = method;
    }
    
    public void setMethod(String method) {
        if (method == null) {
            this.method = null;
        }
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * @return the creditCardNo
     */
    public String getCreditCardNo() {
        return creditCardNo;
    }

    /**
     * @param creditCardNo the creditCardNo to set
     */
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }
    
    public enum PAYMETHOD {
        CASH(1),
        CREDITCARD(2),
        POINT(3);
        
        private int value;
        
        private PAYMETHOD(int i) {
            this.value = i;
        }
        
        public int getValue() {
            return value;
        } 
    }
    
    private int paymentId;
    private int amount;
    private Date time;
    private PAYMETHOD method;
    private int point;
    private String creditCardNo;

    public Payment() {
    }

    public Payment(int amount, Date time, PAYMETHOD method, int point, String creditCardNo) {
        this.amount = amount;
        this.time = time;
        this.method = method;
        this.point = point;
        this.creditCardNo = creditCardNo;
    }
    
    
}
