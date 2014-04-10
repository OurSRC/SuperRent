/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ReservationInfo;
import entityParser.AttributeParser;
import entityParser.DateParser;
import entityParser.IntParser;
import entityParser.StringParser;

/**
 *
 * @author Elitward
 */
public class ReservationInfoDao extends AbstractDao<ReservationInfo> {

    protected static final String tb_name = "reservation_info";

    protected static final AttributeParser ap[] = {
        //        new IntParser("ReservationInfoId", "reservationInfoId"),
        //        new IntParser("BranchId", "branchId"),
        //        new DateParser("ReserveTime", "reserveTime"),
        //        new IntParser("EstimatePrice", "estimatePrice"),
        //        new DateParser("PickUpTime", "pickupTime"),
        //        new DateParser("ReturnTime", "returnTime"),
        //        new IntParser("CustomerId", "customerId"),
        //        new IntParser("StaffId", "staffId"),
        //        new StringParser("VehicleClassName", "vehicleClass"),
        //        new IntParser("vDailyRate", "vDailyRate"),
        //        new IntParser("vHourlyRate", "vHourlyRate"),
        //        new IntParser("vWeeklyRate", "vWeeklyRate")
        new IntParser("ReservationInfoId", "ReservationInfoId"),
        new IntParser("BranchId", "BranchId"),
        new DateParser("ReserveTime", "ReserveTime"),
        new IntParser("EstimatePrice", "EstimatePrice"),
        new DateParser("PickUpTime", "PickupTime"),
        new DateParser("ReturnTime", "ReturnTime"),
        new IntParser("CustomerId", "CustomerId"),
        new IntParser("StaffId", "StaffId"),
        new StringParser("VehicleClassName", "VehicleClass"),
        new IntParser("vDailyRate", "VDailyRate"),
        new IntParser("vHourlyRate", "VHourlyRate"),
        new IntParser("vWeeklyRate", "VWeeklyRate")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;

    @Override
    protected ReservationInfo getInstance() {
        return new ReservationInfo();
    }

}
