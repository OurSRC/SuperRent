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
    private int FuelLevel;
    private int odometer;
    private int StaffId;

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
     * @return the FuelLevel
     */
    public int getFuelLevel() {
        return FuelLevel;
    }

    /**
     * @param FuelLevel the FuelLevel to set
     */
    public void setFuelLevel(int FuelLevel) {
        this.FuelLevel = FuelLevel;
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
     * @return the StaffId
     */
    public int getStaffId() {
        return StaffId;
    }

    /**
     * @param StaffId the StaffId to set
     */
    public void setStaffId(int StaffId) {
        this.StaffId = StaffId;
    }
    
}
