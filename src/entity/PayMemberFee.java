/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Jingchuan Chen
 */
public class PayMemberFee {

    public PayMemberFee(int paymentId, int customerId) {
        this.paymentId = paymentId;
        this.customerId = customerId;
    }
    
    private int paymentId;
    private int customerId;
    
    public PayMemberFee(){}
    
    
    
}
