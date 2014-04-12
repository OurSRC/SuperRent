/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Date;

/**
 *
 * @author Jingchuan Chen
 */
public class Payment {
    
    public enum PAYMETHOD {
        CASH(1),
        CREDITCARD(2),
        POINT(3);
        
        private int value;
        
        private PAYMETHOD(int i) {
            this.value = i;
        }
        
        public int getValue() {
            return value;
        } 
    }
    
    private int paymentId;
    private int amount;
    private Date time;
    private PAYMETHOD method;
    private int point;
    private String creditCardNo;

    public Payment() {
    }

    public Payment(int amount, Date time, PAYMETHOD method, int point, String creditCardNo) {
        this.amount = amount;
        this.time = time;
        this.method = method;
        this.point = point;
        this.creditCardNo = creditCardNo;
    }
    
    
}
