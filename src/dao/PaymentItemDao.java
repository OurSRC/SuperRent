/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.PaymentItem;
import entityParser.*;
import dbconn.SqlBuilder;
import java.util.ArrayList;

/**
 * <p>
 * This class provides basic access methods, for example, find
 * for item entity.</p>
 * @author Jingchuan Chen
 */
public class PaymentItemDao extends AbstractDao<PaymentItem> {
    
    protected static final String tb_name = "item";

    protected static final AttributeParser ap[] = {
        new IntParser("ItemId", "ItemId"),
        new IntParser("PaymentId", "PaymentId"),
        new EnumParser("Type", "Type"),
        new StringParser("Name", "Name"),
        new IntParser("Price", "Price"),
        new IntParser("Quantity", "Quantity")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;
    
    public ArrayList<PaymentItem> findByPaymentId(int paymentId) throws DaoException {
        String cond = "PaymentId=" + SqlBuilder.wrapInt(paymentId);
        return find(cond);
    }

    @Override
    protected PaymentItem getInstance() {
        return new PaymentItem();
    }
    
}
