/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finance;

import SystemOperations.SecurityCtrl;
import java.util.Date;

/**
 * Entity class for creditcard table.
 */
public class CreditCard {

    private String creditCardNo;
    private Date expireDate;
    private String cardHolderName;

    public CreditCard() {
    }

    public CreditCard(String creditCardNo, Date expireDate, String cardHolderName) {
        this.creditCardNo = creditCardNo;
        this.expireDate = expireDate;
        this.cardHolderName = cardHolderName;
    }

    /**
     * @return the creditCardNo
     */
    public String getCreditCardNo() {
        return creditCardNo;
    }

    /**
     * @param creditCardNo the creditCardNo to set
     */
    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the cardHolderName
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * @param cardHolderName the cardHolderName to set
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    static private String key = "SuperRent";

    public void encrypt() {
        creditCardNo = SecurityCtrl.encrypt(key, creditCardNo);
    }

    public void decrypt() {
        creditCardNo = SecurityCtrl.decrypt(key, creditCardNo);
    }

}
