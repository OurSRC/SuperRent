/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;

import Dao.EntityParser.DatetimeParser;
import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.IntParser;
import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;

/**
 * <p>
 * This class provides basic access methods, for example, find for payment
 * entity.</p>
 */
public class PaymentDao extends AbstractDao<Payment> {

    public static final String tb_name = "payment";

    public static final AttributeParser ap[] = {
        new IntParser("PaymentId", "PaymentId"),
        new IntParser("CustomerId", "CustomerId"),
        new StringParser("Title", "Title"),
        new IntParser("Amount", "Amount"),
        new StringParser("CreditCardNo", "CreditCardNo"),
        new DatetimeParser("Time", "Time")
    };

    public static final int[] pkIndex = {0};

    public static final boolean pkIsAutoGen = true;

    /**
     * Find {@link Payment} by {@code paymentId}.
     *
     * @param paymentId The id of payment to search with.
     * @return Matching {@link Payment} object.
     * @throws DaoException
     */
    public Payment findById(int paymentId) throws DaoException {
        String cond = "PaymentId=" + SqlBuilder.wrapInt(paymentId);
        return findOne(cond);
    }

    @Override
    protected Payment getInstance() {
        return new Payment();
    }

}
