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
public class Vehicle {

    /**
     * @return the vehicleNo
     */
    public int getVehicleNo() {
        return vehicleNo;
    }

    /**
     * @param vehicleNo the vehicleNo to set
     */
    public void setVehicleNo(int vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    /**
     * @return the plateNo
     */
    public String getPlateNo() {
        return plateNo;
    }

    /**
     * @param plateNo the plateNo to set
     */
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    /**
     * @return the manufactureDate
     */
    public Date getManufactureDate() {
        return manufactureDate;
    }

    /**
     * @param manufactureDate the manufactureDate to set
     */
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
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
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(STATUS status) {
        this.status = status;
    }

    /**
     * @return the rentStatus
     */
    public RENTSTATUS getRentStatus() {
        return rentStatus;
    }

    /**
     * @param rentStatus the rentStatus to set
     */
    public void setRentStatus(RENTSTATUS rentStatus) {
        this.rentStatus = rentStatus;
    }

    /**
     * @return the sellStatus
     */
    public SELLSTATUS getSellStatus() {
        return sellStatus;
    }

    /**
     * @param sellStatus the sellStatus to set
     */
    public void setSellStatus(SELLSTATUS sellStatus) {
        this.sellStatus = sellStatus;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
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
    public enum STATUS {
        FORRENT,
        FORSALE
    };
    
    public enum RENTSTATUS {
        AVAILABLE,
        UNAVAILABLE
    };
    
    public enum SELLSTATUS {
        FORSALE,
        SOLD
    };
    
    private int vehicleNo;
    private String plateNo;
    private Date manufactureDate;
    private String mode;
    private int odometer;
    private int branchId;
    private STATUS status;
    private RENTSTATUS rentStatus;
    private SELLSTATUS sellStatus;
    private String className;
    private int price;
    
    
}