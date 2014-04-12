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

/**
 *
 * @author grace_xier
 */
public class ReturnDao extends AbstractDao<Return>{
    
    protected static final String tb_name = "return";
    
    protected static final AttributeParser ap[] = {
        new IntParser("ContractNo", "contractNo"),
        new DateParser("ReturnTime", "returnTime"),
        new IntParser("Price", "price"),
        new IntParser("FuelLevel", "FuelLevel"),
        new IntParser("Odometer", "odometer"),
        new IntParser("StaffId", "StaffId")
    };
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = false;
    
    @Override
    protected Return getInstance() {
        return new Return();
    }
    public Return findByContractNo(String contractNo) throws DaoException{
        return findOne("ContractNo=" + SqlBuilder.wrapStr(contractNo));
    }
}
