/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;


import SystemOperations.ErrorMsg;
import dao.CreditCardDao;
import dao.DaoException;
import entity.CreditCard;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * CreditCardCtrl is one of the components in Logical Control, get input from Database and output data to support User Interface, which operates CreditCard entity object
 * </p>
 */
public class CreditCardCtrl {
    static final boolean ENABLE_ENCRYPT = false;
    
    /**
     * Create a record for credit card information
     * @param cardNum Credit card number string
     * @param expire Expire date
     * @param name Credit card holder's name
     * @return true for success or false for duplicate record
     */
    static public boolean create( String cardNum, Date expire, String name){
        CreditCard creditcard = new CreditCard(cardNum, expire, name);
        return create(creditcard);
    }
    
    /**
     * Create a record for credit card information
     * @param CreditCard Credit card entity object
     * @return true for success or false for duplicate record
     */
    static private boolean create(CreditCard creditcard){
        CreditCardDao dao = new CreditCardDao();
        
        if(ENABLE_ENCRYPT)
            creditcard.encrypt();
        
        boolean suc = false;
        try {
            suc = dao.add(creditcard);
        } catch (DaoException ex) {
            Logger.getLogger(CreditCardCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        return suc;
    }
}
