/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entityParser.*;

import entity.Payment;
import dbconn.SqlBuilder;

/**
 * <p>
 * This class provides basic access methods, for example, find
 * for payment entity.</p>
 */
public class PaymentDao extends AbstractDao<Payment>{
    
    protected static final String tb_name = "payment";

    protected static final AttributeParser ap[] = {
        new IntParser("PaymentId", "PaymentId"),
        new IntParser("CustomerId", "CustomerId"),
        new StringParser("Title", "Title"),
        new IntParser("Amount", "Amount"),
        new StringParser("CreditCardNo", "CreditCardNo"),
        new DatetimeParser("Time", "Time")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;
    
    public Payment findById(int paymentId) throws DaoException {
        String cond = "PaymentId=" + SqlBuilder.wrapInt(paymentId);
        return findOne(cond);
    }

    @Override
    protected Payment getInstance() {
        return new Payment();
    }
    
}
