/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Finance;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.DateParser;
import Dao.AbstractDao;
import Dao.DaoException;
import java.util.Date;

import Dao.SqlBuilder;


/**
 * <p>
 * This class provides basic access method for creditcard table.</p>
 */
public class CreditCardDao extends AbstractDao<CreditCard> {
    
    public static final String tb_name = "creditcard";

    public static final AttributeParser ap[] = {
        new StringParser("CreditCardNo", "CreditCardNo"),
        new DateParser("ExpireDate", "ExpireDate"),
        new StringParser("CardHolderName", "CardHolderName")
    };

    public static final int[] pkIndex = {0};

    public static final boolean pkIsAutoGen = false;
    

    @Override
    protected CreditCard getInstance() {
        return new CreditCard();
    }
    
    /**
     * Search {@link CreditCard} by {@code cardNo}.
     * @param cardNo The string represents credit card number.
     * @return  Matching {@link CreditCard} object.
     * @throws DaoException
     */
    public CreditCard findByCardNumber(String cardNo) throws DaoException {
        String cond = "CreditCardNo=" + SqlBuilder.wrapStr(cardNo);
        return findOne(cond);
    }
    
}
