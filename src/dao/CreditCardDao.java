/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entityParser.*;
import java.util.Date;

import dbconn.SqlBuilder;

import entity.CreditCard;

/**
 * <p>
 * This class provides basic access method for creditcard table.</p>
 * @author Jingchuan Chen
 */
public class CreditCardDao extends AbstractDao<CreditCard> {
    
    protected static final String tb_name = "creditcard";

    protected static final AttributeParser ap[] = {
        new StringParser("CreditCardNo", "CreditCardNo"),
        new DateParser("ExpireDate", "ExpireDate"),
        new StringParser("CardHolderName", "CardHolderName")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = false;
    

    @Override
    protected CreditCard getInstance() {
        return new CreditCard();
    }
    
    public CreditCard findByCardNumber(String cardNo) throws DaoException {
        String cond = "CreditCardNo=" + SqlBuilder.wrapStr(cardNo);
        return findOne(cond);
    }
    
}
