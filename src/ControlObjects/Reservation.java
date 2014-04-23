/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.BranchDao;
import dao.BuyInsuranceDao;
import dao.DaoException;
import dao.ReserveEquipmentDao;
import dao.SupportEquipmentDao;
import dao.VehicleClassDao;
import entity.Branch;
import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import entity.VehicleClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elitward
 */
public final class Reservation {

    private ReservationInfo reserveInfo;
    private ArrayList<ReserveEquipment> reserveEquipment;
    private ArrayList<BuyInsurance> reserveInsurance;

    public Reservation() {
        this.reserveInfo = new ReservationInfo();
        this.reserveEquipment = new ArrayList();
        this.reserveInsurance = new ArrayList();
    }

    public Reservation(ReservationInfo reserveInfo, ArrayList<ReserveEquipment> reserveEqmt, ArrayList<BuyInsurance> reserveInsn) {
        this.reserveInfo = reserveInfo;
        this.reserveEquipment = reserveEqmt;
        this.reserveInsurance = reserveInsn;
    }

    public Reservation(int branchId, Date pickupTime, Date returnTime, int customerId, int staffId, String vehicleClass, ArrayList<String> equipmentType, ArrayList<String> insurance) {
        VehicleClassDao vcDAO = new VehicleClassDao();
        VehicleClass vc = null;
        try {
            vc = vcDAO.findByName(vehicleClass);
        } catch (DaoException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_PARAMETER_ERROR);
            return;
        }
        this.reserveInfo = new ReservationInfo(branchId, new Date(), pickupTime, returnTime, customerId, staffId, vehicleClass,
                vc.getHourlyRate(), vc.getDailyRate(), vc.getWeeklyRate(), null, ReservationInfo.STATUS.PENDING);

        setEquipmentType(equipmentType);

        setInsurance(insurance);
    }

    public ReservationInfo getReserveInfo() {
        return reserveInfo;
    }

    public ArrayList<ReserveEquipment> getReserveEquipment() {
        return reserveEquipment;
    }

    public ArrayList<BuyInsurance> getReserveInsurance() {
        return reserveInsurance;
    }

    public void setReserveInfo(ReservationInfo reserveInfo) {
        this.reserveInfo = reserveInfo;
    }

    public void setReserveEquipment(ArrayList<ReserveEquipment> reserveEquipment) {
        this.reserveEquipment = reserveEquipment;
    }

    public void setReserveInsurance(ArrayList<BuyInsurance> reserveInsurance) {
        this.reserveInsurance = reserveInsurance;
    }

    public int getBranchId() {
        return reserveInfo.getBranchId();
    }
    
    public Branch matchBranch(){
        BranchDao brDAO = new BranchDao();
        Branch b = null;
        try {
            b = brDAO.findById(reserveInfo.getBranchId());
        } catch (DaoException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
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
        for (ReserveEquipment anEqmt : reserveEquipment) {
            equipments.add(anEqmt.getEquipmentType());
        }
        return equipments;
    }

    public ArrayList<String> getInsurance() {
        ArrayList<String> insurances = new ArrayList<>();
        for (BuyInsurance anInsu : reserveInsurance) {
            insurances.add(anInsu.getInsuranceName());
        }
        return insurances;
    }

    public boolean setEquipmentType(ArrayList<String> equipmentType) {
        if (reserveInfo == null) {
            ErrorMsg.setLastError(ErrorMsg.ERROR_INVOKE_MISTAKE);
            return false;
        }

        this.reserveEquipment = new ArrayList();
        for (String str : equipmentType) {
            ReserveEquipmentDao reDAO = new ReserveEquipmentDao();
            SupportEquipmentDao seDAO = new SupportEquipmentDao();
            try {
                if (seDAO.matchVehicleClassAndEquipmentType(reserveInfo.getVehicleClass(), str)) {
                    this.reserveEquipment.add( reDAO.makeReserveEquipment(this.reserveInfo.getReservationInfoId(), str) );
                }
            } catch (DaoException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
                return false;
            }
        }

        return true;
    }

    public boolean setInsurance(ArrayList<String> insurance) {
        if (reserveInfo == null) {
            ErrorMsg.setLastError(ErrorMsg.ERROR_INVOKE_MISTAKE);
            return false;
        }

        this.reserveInsurance = new ArrayList<BuyInsurance>();
        for( String str : insurance ) {
            BuyInsuranceDao binDAO = new BuyInsuranceDao();
            try {
                this.reserveInsurance.add( binDAO.makeBuyInsurance(this.reserveInfo.getReservationInfoId(), str) );
            } catch (DaoException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_PARAMETER_ERROR);
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