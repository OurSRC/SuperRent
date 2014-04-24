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
 * <p>
 *  This class provides basic access methods, for example, find
 *  for rent entity.</p>
 * @author Xi Yang
 */
public class RentDao extends AbstractDao<Rent>{
    
    protected static final String tb_name = "rent";
    protected static final AttributeParser ap[] = {
        new IntParser("ContractNo","ContractNo"),
        new IntParser("ReservationInfoId","ReservationInfold"),
        new IntParser("VehicleNo","VehicleNo"),
        new IntParser("FuelLevel","FuelLevel"),
        new IntParser("Odometer","Odometer"),
        new StringParser("CreditCardNo","CreditCardNo"),
        new IntParser("StaffId","StaffId"),
        new DatetimeParser("Time","Time")
    };
    
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = true;
    
    @Override
    protected Rent getInstance() {
        return new Rent();
    }
    
    /**
     * This method find a {@link Rent} with given {@code contractNo}
     * @param contractNo contract number of rent
     * @return rent with contract number, null if non found
     * @throws DaoException
     */
    public Rent findByContractNo(String contractNo) throws DaoException{
        return findOne("ContractNo=" + SqlBuilder.wrapStr(contractNo));
    }
    /**
     * This method find a {@link Rent} with given {@code reservationInfoId}
     * @param reservationInfoId reservation information id
     * @return rent with reservation information id, null if not found
     * @throws DaoException
     */
    public Rent findByReservationInfo(int reservationInfoId) throws DaoException {
        return findOne("ReservationInfoId=" + SqlBuilder.wrapInt(reservationInfoId) );
    }
}
