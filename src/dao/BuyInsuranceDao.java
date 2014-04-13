/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.BuyInsurance;
import entityParser.*;

public class BuyInsuranceDao extends AbstractDao<BuyInsurance> {
    
    protected static final String tb_name = "buy_insurance";
    
    protected static final AttributeParser ap[] = {
        new StringParser("InsuranceName", "InsuranceName"),
        new IntParser("ReservationInfoId", "ReservationInfoId")
    };
    
    protected static final int[] pkIndex = {0, 1};

    protected static final boolean pkIsAutoGen = false;

    @Override
    protected BuyInsurance getInstance() {
        return new BuyInsurance();
    }
    
}
