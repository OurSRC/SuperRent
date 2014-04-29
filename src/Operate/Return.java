/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operate;

import java.util.Date;

/**
 * Entity class for return table.
 */
public class Return {

    private int contractNo;
    private Date returnTime;
    private int fuelLevel;
    private int odometer;
    private int staffId;
    private int paymentId;
    private int damageCost;

    public Return() {
    }

    public Return(int contractNo, Date returnTime, int fuelLevel, int odometer,
            int staffId, int paymentId, int damageCost) {
        this.contractNo = contractNo;
        this.returnTime = returnTime;
        this.fuelLevel = fuelLevel;
        this.odometer = odometer;
        this.staffId = staffId;
        this.paymentId = paymentId;
        this.damageCost = damageCost;

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

    /**
     * @return the damageCost
     */
    public int getDamageCost() {
        return damageCost;
    }

    /**
     * @param damageCost the damageCost to set
     */
    public void setDamageCost(int damageCost) {
        this.damageCost = damageCost;
    }

}
