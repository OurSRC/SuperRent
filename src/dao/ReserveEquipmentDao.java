/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.SqlBuilder;
import entity.Branch;
import entity.Equipment;
import entity.EquipmentType;
import entity.ReserveEquipment;
import entityParser.AttributeParser;
import entityParser.IntParser;
import entityParser.StringParser;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * This class provides basic access methods for reserve equipment entity.</p>
 */
public class ReserveEquipmentDao extends AbstractDao<ReserveEquipment> {

    protected static final String tb_name = "reserve_equipment";

    protected static final AttributeParser ap[] = {
        new IntParser("ReservationInfoId", "ReservationInfoId"),
        new StringParser("EquipmentType", "EquipmentType"),
        new IntParser("EHourlyRate", "EHourlyRate"),
        new IntParser("EDailyRate", "EDailyRate")
    };

    protected static final int[] pkIndex = {0, 1};

    protected static final boolean pkIsAutoGen = false;

    @Override
    protected ReserveEquipment getInstance() {
        return new ReserveEquipment();
    }
    
    /**
     * Make a {@link ReseveEquipment} object.
     * @param reservationInfoId Id of {@link ReservationInfo} correspond with this object.
     * @param equipmentType Type of the equipment to reserve.
     * @return Generated {@link ReserveEquopment} object.
     * @throws DaoException
     */
    public ReserveEquipment makeReserveEquipment( int reservationInfoId,String equipmentType ) throws DaoException{
        EquipmentTypeDao dao = new EquipmentTypeDao();
        EquipmentType eqt = dao.findEquipmentTypeByTypename(equipmentType);
        
        ReserveEquipment ans = new ReserveEquipment();
        ans.setReservationInfoId(reservationInfoId);
        ans.setEquipmentType(equipmentType);
        ans.setEHourlyRate( eqt.getHourlyRate() );
        ans.setEDailyRate( eqt.getDailyRate() );

        return ans;
    }
    
    /**
     * Find {@link ReserveEquipment} objects {@code reservationId}.
     * @param reservationId The reservationInfoId to search with.
     * @return ArrayList of matching {@link ReserveEquipment} objects.
     * @throws DaoException
     */
    public ArrayList<ReserveEquipment> findReserveEquipmentByReservationId(int reservationId) throws DaoException{
        String cond;
        cond = "ReservationInfoId = " + SqlBuilder.wrapInt(reservationId);
        return find(cond);
    }
}
