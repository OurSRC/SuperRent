/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EnumParser;
import Dao.EntityParser.IntParser;
import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import java.util.ArrayList;

/**
 * <p>
 * This class provides basic access methods, for example, find for item
 * entity.</p>
 */
public class PaymentItemDao extends AbstractDao<PaymentItem> {

    public static final String tb_name = "item";

    public static final AttributeParser ap[] = {
        new IntParser("ItemId", "ItemId"),
        new IntParser("PaymentId", "PaymentId"),
        new EnumParser("Type", "Type"),
        new StringParser("Name", "Name"),
        new IntParser("Price", "Price"),
        new IntParser("Quantity", "Quantity")
    };

    public static final int[] pkIndex = {0};

    public static final boolean pkIsAutoGen = true;

    /**
     * Find {@link PaymentItem} objects by {@code PaymentId}.
     *
     * @param paymentId The id of {@link PaymentItem} to search with.
     * @return ArrayList of all matching {@link PaymentItem}.
     * @throws DaoException
     */
    public ArrayList<PaymentItem> findByPaymentId(int paymentId) throws DaoException {
        String cond = "PaymentId=" + SqlBuilder.wrapInt(paymentId);
        return find(cond);
    }

    @Override
    protected PaymentItem getInstance() {
        return new PaymentItem();
    }

}
