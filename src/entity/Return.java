/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Date;
/**
 *
 * @author Xi Yang
 */
public class Return {
    private int contractNo;
    private Date returnTime;
    private int price;
    private int fuelLevel;
    private int odometer;
    private int staffId;
    private int paymentId;

    public Return() {
    }

    public Return(int contractNo, Date returnTime, int price, int fuelLevel, int odometer, int staffId, int paymentId) {
        this.contractNo = contractNo;
        this.returnTime = returnTime;
        this.price = price;
        this.fuelLevel = fuelLevel;
        this.odometer = odometer;
        this.staffId = staffId;
        this.paymentId = paymentId;
    }

    /**
     * @return the contractNo
     */
    public int getContractNo() {
        return contractNo;
    }

    /**
     * @param contractNo the contractNo to set
     */
    public void setContractNo(int contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * @return the returnTime
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * @param returnTime the returnTime to set
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the fuelLevel
     */
    public int getFuelLevel() {
        return fuelLevel;
    }

    /**
     * @param fuelLevel the fuelLevel to set
     */
    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    /**
     * @return the odometer
     */
    public int getOdometer() {
        return odometer;
    }

    /**
     * @param odometer the odometer to set
     */
    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    /**
     * @return the staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

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
    
    
    
}
