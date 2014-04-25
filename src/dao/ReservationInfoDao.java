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

    public ReservationInfo makeReservationInfo(ReservationInfo reservationInfo) throws DaoException {
        add(reservationInfo);
        int id = getLastAutoIncrementId();
        reservationInfo.setReservationInfoId(id);
        return reservationInfo;
    }

    public ReservationInfo findByReservationNo(String reservatioNo) throws DaoException {
        String cond = "ReservationNo=" + SqlBuilder.wrapStr(reservatioNo);
        return findOne(cond);
    }

    public ArrayList<ReservationInfo> findReservationBetween(Date pickUpTime,
            Date returnTime, Branch branch) throws DaoException {

        SqlBuilder qb = new SqlBuilder();
        qb.cond("PickUpTime <" + SqlBuilder.wrapDatetime(returnTime));
        qb.cond("ReturnTime > " + SqlBuilder.wrapDatetime(pickUpTime));
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        String cond = qb.toString();

        return find(cond);
    }

    public ArrayList<ReservationInfo> findReservationUsingReserveTime(Date FromReserveTime,
            Date ToReserveTime, Branch branch, String ReservationNo) throws DaoException {

        SqlBuilder qb = new SqlBuilder();

        qb.cond("ReserveTime < " + SqlBuilder.wrapDatetime(ToReserveTime));
        qb.cond("ReserveTime > " + SqlBuilder.wrapDatetime(FromReserveTime));
        qb.cond("ReservationStatus = 'PENDING'");
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        if (!ReservationNo.equals("")) {
            System.out.println("I am here");
            qb.cond("ReservationNo =" + SqlBuilder.wrapStr(ReservationNo));
        }

        String cond = qb.toString();

        return find(cond);
    }

    public ArrayList<ReservationInfo> findReservationBetween(String vehicleClass, Date pickUpTime,
            Date returnTime, Branch branch) throws DaoException {

        SqlBuilder qb = new SqlBuilder();
        qb.cond("PickUpTime <" + SqlBuilder.wrapDatetime(returnTime));
        qb.cond("ReturnTime > " + SqlBuilder.wrapDatetime(pickUpTime));
        qb.cond("VehicleClass = " + SqlBuilder.wrapStr(vehicleClass));
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        qb.cond("ReservationStatus<>" + SqlBuilder.wrapInt(ReservationInfo.STATUS.CANCELED.getValue()));
        String cond = qb.toString();

        return find(cond);
    }

    public ArrayList<ReservationInfo> findReservationTimeBetween(Date early_bound, Date late_bound, Branch branch) throws DaoException {

        SqlBuilder qb = new SqlBuilder();
        qb.cond("ReserveTime <" + SqlBuilder.wrapDatetime(late_bound));
        qb.cond("ReserveTime > " + SqlBuilder.wrapDatetime(early_bound));
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        String cond = qb.toString();

        return find(cond);
    }

    public ArrayList<ReservationInfo> findReservationBetweenUsingEquipmentType(String equipmentType,
            Date pickUpTime, Date returnTime, Branch branch) throws DaoException {

        SqlBuilder subQue = new SqlBuilder();
        String subQueueStr;

        subQueueStr = subQue
                .select("ReservationInfoId")
                .from(ReserveEquipmentDao.tb_name)
                .where("EquipmentType=" + SqlBuilder.wrapStr(equipmentType))
                .isSubQueue()
                .toString();

        subQueueStr = "(" + subQueueStr + ")";

        SqlBuilder qb = new SqlBuilder();
        qb.cond("PickUpTime <" + SqlBuilder.wrapDatetime(returnTime));
        qb.cond("ReturnTime > " + SqlBuilder.wrapDatetime(pickUpTime));
        qb.cond("BranchId =" + SqlBuilder.wrapInt(branch.getBranchID()));
        qb.cond("ReservationStatus<>" + SqlBuilder.wrapInt(ReservationInfo.STATUS.CANCELED.getValue()));
        qb.cond("ReservationInfoId IN " + subQueueStr);

        String cond = qb.toString();

        return find(cond);
    }

}
