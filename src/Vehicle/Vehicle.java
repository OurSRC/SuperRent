/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import Finance.Price;
import java.util.Date;

/**
 * Entity class for vehicle table.
 */
public class Vehicle {

    public enum STATUS {

        FORRENT(1),
        FORSALE(2);

        private int value;

        private STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    public enum RENTSTATUS {

        IDLE(1),
        RENTED(2),
        UNAVAILABLE(3);

        private int value;

        private RENTSTATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    public enum SELLSTATUS {

        FORSALE(1),
        SOLD(2);

        private int value;

        private SELLSTATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
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

    public Vehicle() {
    }

    public Vehicle(String plateNo, Date mDate, String mode, int odometer,
            int branchId, STATUS status, RENTSTATUS rentstatus, SELLSTATUS sellstatus,
            String className, int price) {

        this.plateNo = plateNo;
        this.manufactureDate = mDate;
        this.mode = mode;
        this.odometer = odometer;
        //his.mode = mode;
        this.branchId = branchId;
        this.status = status;
        this.rentStatus = rentstatus;
        this.sellStatus = sellstatus;
        this.className = className;
        this.price = price;
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

    public void setStatus(String status) {
        if (status == null) {
            this.status = null;
        } else {
            this.status = STATUS.valueOf(status);
        }
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

    public void setRentStatus(String rentStatus) {
        if (rentStatus == null) {
            this.rentStatus = null;
        } else {
            this.rentStatus = RENTSTATUS.valueOf(rentStatus);
        }
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

    public void setSellStatus(String sellStatus) {
        if (sellStatus == null) {
            this.sellStatus = null;
        } else {
            this.sellStatus = SELLSTATUS.valueOf(sellStatus);
        }
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

    public String getSellingPrice() {
        return Price.toText(price);
    }

    public boolean setSellingPrice(String sellingPrice) {
        this.price = Price.toCent(sellingPrice);
        return true;
    }
}
