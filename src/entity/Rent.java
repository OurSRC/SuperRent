/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 * Entity class for rent table.
 */
public class Rent {

    private int contractNo;
    private int reservationInfold;
    private int vehicleNo;
    private int fuelLevel;
    private int odometer;
    private String creditCardNo;
    private int staffId;
    private Date time;

    public Rent() {
    }

    public Rent(int reservationInfold, int vehicleNo, int fuelLevel, int odometer, String creditCardNo, int staffId, Date time) {
        this.reservationInfold = reservationInfold;
        this.vehicleNo = vehicleNo;
        this.fuelLevel = fuelLevel;
        this.odometer = odometer;
        this.creditCardNo = creditCardNo;
        this.staffId = staffId;
        this.time = time;
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
     * @return the reservationInfold
     */
    public int getReservationInfold() {
        return reservationInfold;
    }

    /**
     * @param reservationInfold the reservationInfold to set
     */
    public void setReservationInfold(int reservationInfold) {
        this.reservationInfold = reservationInfold;
    }

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
    
}
