/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.SqlBuilder;
import entity.Branch;
import entity.ReservationInfo;
import entityParser.AttributeParser;
import entityParser.DateParser;
import entityParser.DatetimeParser;
import entityParser.EnumParser;
import entityParser.IntParser;
import entityParser.StringParser;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Elitward
 */
public class ReservationInfoDao extends AbstractDao<ReservationInfo> {

    protected static final String tb_name = "reservation_info";

    protected static final AttributeParser ap[] = {
        new IntParser("ReservationInfoId", "ReservationInfoId"),
        new IntParser("BranchId", "BranchId"),
        new DatetimeParser("ReserveTime", "ReserveTime"),
        new DatetimeParser("PickUpTime", "PickupTime"),
        new DatetimeParser("ReturnTime", "ReturnTime"),
        new IntParser("CustomerId", "CustomerId"),
        new IntParser("StaffId", "StaffId"),
        new StringParser("VehicleClass", "VehicleClass"),
        new IntParser("VDailyRate", "vDailyRate"),
        new IntParser("VHourlyRate", "vHourlyRate"),
        new IntParser("VWeeklyRate", "vWeeklyRate"),
        new StringParser("ReservationNo", "ReservationNo"),
        new EnumParser("ReservationStatus", "ReservationStatus")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;

    @Override
    protected ReservationInfo getInstance() {
        return new ReservationInfo();
    }
    
    public ReservationInfo findByReservationNo(String reservatioNo) throws DaoException {
        String cond = "ReservationNo=" + SqlBuilder.wrapStr(reservatioNo);
        return findOne(cond);
    }
    
    
    

    public ArrayList<ReservationInfo> countReservationBetween(String vehicleClass, Date pickUpTime, 
            Date returnTime, Branch branch) throws DaoException{
        
        SqlBuilder qb = new SqlBuilder();
        qb.cond("PickUpTime <" + SqlBuilder.wrapDate(returnTime));
        qb.cond("ReturnTime > " + SqlBuilder.wrapDate(pickUpTime));
        qb.cond("VehicleClass = " + SqlBuilder.wrapStr(vehicleClass));
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        String cond = qb.toString();
        
        return find(cond);
        
    }
}
