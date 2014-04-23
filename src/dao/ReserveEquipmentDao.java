/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
 *
 * @author Elitward
 */
public class ReserveEquipmentDao extends AbstractDao<ReserveEquipment> {

    protected static final String tb_name = "reserve_equipment";

    protected static final AttributeParser ap[] = {
        new IntParser("ReservationInfoId", "ReservationInfoId"),
        new StringParser("EquipmentType", "EquipmentType"),
        new IntParser("eHourlyRate", "EHourlyRate"),
        new IntParser("eDailyRate", "EDailyRate")
    };

    protected static final int[] pkIndex = {0, 1};

    protected static final boolean pkIsAutoGen = false;

    @Override
    protected ReserveEquipment getInstance() {
        return new ReserveEquipment();
    }
    
    public ReserveEquipment makeReserveEquipment( int reservationInfoId,String equipmentType ) throws DaoException{
        EquipmentTypeDao dao = new EquipmentTypeDao();
        EquipmentType eqt = dao.findEquipmentTypeByTypename(equipmentType);
        
        ReserveEquipment ans = new ReserveEquipment();
        ans.setReservationInfoId(reservationInfoId);
        ans.setEquipmentType(equipmentType);
        ans.seteHourlyRate( eqt.getHourlyRate() );
        ans.seteDailyRate( eqt.getDailyRate() );
        return ans;
    }

    public ArrayList<ReserveEquipment> findReservationBetween(String equipmentType, Date pickUpTime, 
            Date returnTime, Branch branch) throws DaoException{
        return null;
    }
}
