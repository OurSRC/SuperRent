/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.BuyInsuranceDao;
import dao.DaoException;
import dao.EquipmentTypeDao;
import dao.ReserveEquipmentDao;
import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elitward
 */
public class Reservation {

    private ReservationInfo reserveInfo;
    private ArrayList<ReserveEquipment> reserveEqmt;
    private ArrayList<BuyInsurance> reserveInsu;

    public Reservation() {
        this.reserveInfo = new ReservationInfo();
        this.reserveEqmt = new ArrayList();
        this.reserveInsu = new ArrayList();
    }

    public Reservation(ReservationInfo reserveInfo, ArrayList<ReserveEquipment> reserveEqmt, ArrayList<BuyInsurance> reserveInsn) {
        this.reserveInfo = reserveInfo;
        this.reserveEqmt = reserveEqmt;
        this.reserveInsu = reserveInsn;
    }

    public Reservation(int branchId, Date pickupTime, Date returnTime, int customerId, int staffId, String vehicleClass, ArrayList<String> equipmentType, ArrayList<String> Insurance) {
    }

    public int getBranchId() {
        return reserveInfo.getBranchId();
    }

    public void setBranchId(int branchId) {
        this.reserveInfo.setBranchId(branchId);
    }

    public Date getPickupTime() {
        return reserveInfo.getPickupTime();
    }

    public void setPickupTime(Date pickupTime) {
        this.reserveInfo.setPickupTime(pickupTime);
    }

    public Date getReturnTime() {
        return reserveInfo.getReturnTime();
    }

    public void setReturnTime(Date returnTime) {
        this.reserveInfo.setReturnTime(returnTime);
    }

    public int getCustomerId() {
        return reserveInfo.getCustomerId();
    }

    public void setCustomerId(int customerId) {
        this.reserveInfo.setCustomerId(customerId);
    }

    public int getStaffId() {
        return reserveInfo.getStaffId();
    }

    public void setStaffId(int staffId) {
        this.reserveInfo.setStaffId(staffId);
    }

    public String getVehicleClass() {
        return reserveInfo.getVehicleClass();
    }

    public void setVehicleClass(String vehicleClass) {
        this.reserveInfo.setVehicleClass(vehicleClass);
    }

    public int getReservationInfoId() {
        return reserveInfo.getReservationInfoId();
    }

    public void setReservationInfoId(int reservationInfoId) {
        this.reserveInfo.setReservationInfoId(reservationInfoId);
    }

    public Date getReserveTime() {
        return reserveInfo.getReserveTime();
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveInfo.setReserveTime(reserveTime);
    }

    public String getReservationNo() {
        return reserveInfo.getReservationNo();
    }

    public void setReservationNo(String reservationNo) {
        this.reserveInfo.setReservationNo(reservationNo);
    }

    public ReservationInfo.STATUS getReservationStatus() {
        return reserveInfo.getReservationStatus();
    }

    public void setReservationStatus(ReservationInfo.STATUS reservationStatus) {
        this.reserveInfo.setReservationStatus(reservationStatus);
    }

    public ArrayList<String> getEquipmentType() {
        ArrayList<String> equipments = new ArrayList<>();
        for (ReserveEquipment anEqmt : reserveEqmt) {
            equipments.add(anEqmt.getEquipmentType());
        }
        return equipments;
    }

    public ArrayList<String> getInsurance() {
        ArrayList<String> insurances = new ArrayList<>();
        for (BuyInsurance anInsu : reserveInsu) {
            insurances.add(anInsu.getInsuranceName());
        }
        return insurances;
    }
    
    public boolean setEquipmentType(ArrayList<String> equipmentType) {
        if (reserveInfo==null || reserveInfo.checkReservationInfoIdValid() == false) {
            ErrorMsg.setLastError(ErrorMsg.ERROR_INVOKE_MISTAKE);
            return false;
        }
        
        this.reserveEqmt = new ArrayList<ReserveEquipment>();
        for (String anEquipment : equipmentType) {
            ReserveEquipmentDao dao = new ReserveEquipmentDao();
            try {
                reserveEqmt.add(dao.createReserveEquipment(reserveInfo.getReservationInfoId(), anEquipment));
            } catch (DaoException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
                return false;
            }
        }
        return true;
    }


    public boolean setInsurance(ArrayList<String> Insurance) {
        if (reserveInfo==null || reserveInfo.checkReservationInfoIdValid() == false) {
            ErrorMsg.setLastError(ErrorMsg.ERROR_INVOKE_MISTAKE);
            return false;
        }
        
        this.reserveInsu = new ArrayList<BuyInsurance>();
        for(String anInsurance: Insurance){
            BuyInsuranceDao dao = new BuyInsuranceDao();
            try {
                reserveInsu.add(dao.createBuyInsurance(reserveInfo.getReservationInfoId(), anInsurance));
            } catch (DaoException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
                return false;
            }
        }
        return true;
    }
    public String getHourlyPrice() {
        return "123.45";
    }

    public boolean setHourlyPrice(String price) {
        return true;
    }

    public String getDailyPrice() {
        return "123.45";
    }

    public boolean setDailyPrice(String price) {
        return true;
    }

    public String getWeeklyPrice() {
        return "123.45";
    }

    public boolean setWeeklyPrice(String price) {
        return true;
    }
}
