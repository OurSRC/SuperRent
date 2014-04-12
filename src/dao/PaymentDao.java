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
 *
 * @author Jingchuan Chen
 */
public class PaymentDao extends AbstractDao<Payment>{
    
    protected static final String tb_name = "payment";

    protected static final AttributeParser ap[] = {
        new IntParser("PaymentId", "PaymentId"),
        new IntParser("Amount", "Amound"),
        new DateParser("Time", "Time"),
        new EnumParser("Method", "Method"),
        new IntParser("Point", "Point"),
        new StringParser("CreditCardNo", "CreditCardNo")
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
