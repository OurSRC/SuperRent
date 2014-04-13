/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.Rent;
import entityParser.AttributeParser;
import entityParser.IntParser;
import entityParser.StringParser;
import entityParser.DateParser;
import entityParser.DatetimeParser;
/**
 *
 * @author Xi Yang
 */
public class RentDao extends AbstractDao<Rent>{
    
    protected static final String tb_name = "reservation_info";
    protected static final AttributeParser ap[] = {
        new IntParser("ContractNo","contractNo"),
        new IntParser("ReservationInfoId","reservationInfold"),
        new IntParser("VehicleNo","vehicleNo"),
        new IntParser("FuelLevel","fuelLevel"),
        new IntParser("Odometer","odometer"),
        new StringParser("CreditCardNo","creditCardNo"),
        new IntParser("StaffId","staffId"),
        new DatetimeParser("Time","time")
    };
    
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = true;
    
    @Override
    protected Rent getInstance() {
        return new Rent();
    }
    public Rent findByContractNo(String contractNo) throws DaoException{
        return findOne("ContractNo=" + SqlBuilder.wrapStr(contractNo));
    }
}
