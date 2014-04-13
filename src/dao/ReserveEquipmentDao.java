/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ReserveEquipment;
import entityParser.AttributeParser;
import entityParser.IntParser;
import entityParser.StringParser;

/**
 *
 * @author Elitward
 */
public class ReserveEquipmentDao extends AbstractDao<ReserveEquipment> {

    protected static final String tb_name = "reserve_equipment";

    protected static final AttributeParser ap[] = {
        //        new IntParser("ReservationInfoId", "reservationInfoId"),
        //        new StringParser("EquipmentType", "equipmentType"),
        //        new IntParser("eHourlyRate", "eHourlyRate"),
        //        new IntParser("eDailyRate", "eDailyRate")
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
    
    public ReserveEquipment createReserveEquipment( int reservationInfoId,String equipmentType ) throws DaoException{
        return null;
    }

}
