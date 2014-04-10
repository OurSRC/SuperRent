/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Reservation;
import entityParser.AttributeParser;
import entityParser.EnumParser;
import entityParser.IntParser;

/**
 *
 * @author Elitward
 */
public class ReservationDao extends AbstractDao<Reservation> {

    protected static final String tb_name = "reservation";

    protected static final AttributeParser ap[] = {
        //        new IntParser("ReservationNo", "reservationNo"),
        //        new IntParser("ReservationInfoId", "resrevationInfoId"),
        //        new EnumParser("Status", "status")
        new IntParser("ReservationNo", "ReservationNo"),
        new IntParser("ReservationInfoId", "ResrevationInfoId"),
        new EnumParser("Status", "Status")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;

    @Override
    protected Reservation getInstance() {
        return new Reservation();
    }

}
