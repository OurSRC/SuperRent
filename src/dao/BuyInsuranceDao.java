/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.BuyInsurance;
import entity.Insurance;
import entityParser.*;
import java.util.ArrayList;

/**
 * <p>
 *  This class provides basic access method for buy_insurance table.</p>
 */
public class BuyInsuranceDao extends AbstractDao<BuyInsurance> {
    
    protected static final String tb_name = "buy_insurance";
    
    protected static final AttributeParser ap[] = {
        new StringParser("InsuranceName", "InsuranceName"),
        new IntParser("ReservationInfoId", "ReservationInfoId"),
        new IntParser("HourlyRate", "HourlyRate"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("WeeklyRate", "WeeklyRate")
    };
    
    protected static final int[] pkIndex = {0, 1};

    protected static final boolean pkIsAutoGen = false;

    @Override
    protected BuyInsurance getInstance() {
        return new BuyInsurance();
    }
    
    /**
     * Make a {@link BuyInsurance} object with given insuranceName and reservationInfoId.
     * @param reservationInfoId The Id of ReservationInfo to buy insurance.
     * @param insuranceName The name of the Insurance to buy.
     * @return the created BuyInsurance object.
     * @throws DaoException
     */
    public BuyInsurance makeBuyInsurance(int reservationInfoId, String insuranceName) throws DaoException {
        InsuranceDao dao = new InsuranceDao();
        Insurance insu = dao.findByName(insuranceName);
        
        if(insu==null)
            return null;
        
        BuyInsurance ans = new BuyInsurance(insuranceName, reservationInfoId, insu.getHourlyRate(), insu.getDailyRate(), insu.getWeeklyRate());
        return ans;
    }
    
    /**
     * Find {@link BuyInsurance} records by reservationInfoId.
     * @param reservationId
     * @return ArrayList of matching {@link BuyInsurance} object.
     * @throws DaoException
     */
    public ArrayList<BuyInsurance> findBuyInsuranceByReservationId(int reservationId) throws DaoException{
        return find("ReservationInfoId = "+ SqlBuilder.wrapInt(reservationId));
    }
}
