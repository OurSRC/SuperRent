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
 *
 * 
 */
public class CreditCardCtrl {
    static final boolean ENABLE_ENCRYPT = false;
    
    /**
     *
     * @param cardNum
     * @param expire
     * @param name
     * @return
     */
    static public boolean create( String cardNum, Date expire, String name){
        CreditCard creditcard = new CreditCard(cardNum, expire, name);
        return create(creditcard);
    }
    
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
