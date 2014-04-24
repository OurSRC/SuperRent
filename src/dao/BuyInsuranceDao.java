/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.BuyInsurance;
import entity.Insurance;
import entityParser.*;
import java.util.ArrayList;

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
    
    public BuyInsurance makeBuyInsurance(int reservationInfoId, String insuranceName) throws DaoException {
        InsuranceDao dao = new InsuranceDao();
        Insurance insu = dao.findByName(insuranceName);
        
        if(insu==null)
            return null;
        
        BuyInsurance ans = new BuyInsurance(insuranceName, reservationInfoId, insu.getHourlyRate(), insu.getDailyRate(), insu.getWeeklyRate());
        return ans;
    }
    
    public ArrayList<BuyInsurance> findBuyInsuranceByReservationId(int reservationId){
        return null;
    }
}
