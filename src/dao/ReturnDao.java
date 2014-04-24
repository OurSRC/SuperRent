/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.Return;
import entityParser.AttributeParser;
import entityParser.IntParser;
import entityParser.DateParser;
import entityParser.DatetimeParser;

/**
 * <p>
 *  This class provides basic access methods, for example, find
 *  for rent entity.</p>
 * @author Xi Yang
 */
public class ReturnDao extends AbstractDao<Return>{
    
    protected static final String tb_name = "return_record";
    
    protected static final AttributeParser ap[] = {
        new IntParser("ContractNo", "ContractNo"),
        new DatetimeParser("ReturnTime", "ReturnTime"),
        new IntParser("Price", "Price"),
        new IntParser("FuelLevel", "FuelLevel"),
        new IntParser("Odometer", "Odometer"),
        new IntParser("StaffId", "StaffId"),
        new IntParser("PaymentId", "PaymentId")
    };
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = false;
    
    @Override
    protected Return getInstance() {
        return new Return();
    }
    /**
     * This method find a {@link Return} with given {@code contractNo}
     * @param contractNo contract number of return
     * @return return with contract number, null if non found
     * @throws DaoException
     */
    public Return findByContractNo(int contractNo) throws DaoException{
        return findOne("ContractNo=" + SqlBuilder.wrapInt(contractNo));
    }
}
