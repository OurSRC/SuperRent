/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operate;

import Finance.Price;
import java.util.Date;

/**
 * Entity class for reservation_info table.
 */
public class ReservationInfo {

    public enum STATUS {

        PENDING(1),
        RENTED(2),
        CANCELED(3);

        private int value;

        private STATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private int reservationInfoId;
    private int branchId;
    private Date reserveTime;
    private Date pickupTime;
    private Date returnTime;
    private int estimatePrice;
    private int customerId;
    private int staffId;
    private String vehicleClass;
    private int vHourlyRate;
    private int vDailyRate;
    private int vWeeklyRate;
    private String reservationNo;
    private STATUS reservationStatus;

    public ReservationInfo() {
    }

    public ReservationInfo(int branchId, Date reserveTime, Date pickupTime, Date returnTime,
            int customerId, int staffId, String vehicleClass, int vHourlyRate,
            int vDailyRate, int vWeeklyRate, String reservationNo, STATUS reservationStatus) {
        this.branchId = branchId;
        this.reserveTime = reserveTime;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.customerId = customerId;
        this.staffId = staffId;
        this.vehicleClass = vehicleClass;
        this.vHourlyRate = vHourlyRate;
        this.vDailyRate = vDailyRate;
        this.vWeeklyRate = vWeeklyRate;
        this.reservationNo = reservationNo;
        this.reservationStatus = reservationStatus;
    }

    /**
     * @return the reservationInfoId
     */
    public int getReservationInfoId() {
        return reservationInfoId;
    }

    public boolean checkReservationInfoIdValid() {
        if (reservationInfoId > 0) //the ID is auto increased from 1
        {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param reservationInfoId the reservationInfoId to set
     */
    public void setReservationInfoId(int reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
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
     * @return the reserveTime
     */
    public Date getReserveTime() {
        return reserveTime;
    }

    /**
     * @param reserveTime the reserveTime to set
     */
    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    /**
     * @return the pickupTime
     */
    public Date getPickupTime() {
        return pickupTime;
    }

    /**
     * @param pickupTime the pickupTime to set
     */
    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
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
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
     * @return the vehicleClass
     */
    public String getVehicleClass() {
        return vehicleClass;
    }

    /**
     * @param vehicleClass the vehicleClass to set
     */
    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * @return the vHourlyRate
     */
    public int getvHourlyRate() {
        return vHourlyRate;
    }

    /**
     * @param vHourlyRate the vHourlyRate to set
     */
    public void setvHourlyRate(int vHourlyRate) {
        this.vHourlyRate = vHourlyRate;
    }

    /**
     * @return the vDailyRate
     */
    public int getvDailyRate() {
        return vDailyRate;
    }

    /**
     * @param vDailyRate the vDailyRate to set
     */
    public void setvDailyRate(int vDailyRate) {
        this.vDailyRate = vDailyRate;
    }

    /**
     * @return the vWeeklyRate
     */
    public int getvWeeklyRate() {
        return vWeeklyRate;
    }

    /**
     * @param vWeeklyRate the vWeeklyRate to set
     */
    public void setvWeeklyRate(int vWeeklyRate) {
        this.vWeeklyRate = vWeeklyRate;
    }

    /**
     * @return the reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * @param reservationNo the reservationNo to set
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * @return the reservationStatus
     */
    public STATUS getReservationStatus() {
        return reservationStatus;
    }

    /**
     * @param reservationStatus the reservationStatus to set
     */
    public void setReservationStatus(STATUS reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        if (reservationStatus == null) {
            this.reservationStatus = null;
        } else {
            this.reservationStatus = STATUS.valueOf(reservationStatus);
        }
    }

    public String getPrice() {
        return Price.toText(this.estimatePrice);
    }

}
