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
public class PayRental {

    
    private int contractNo;
    private int paymentId;
    
    public PayRental() {
    }

    public PayRental(int contractNo, int paymentId) {
        this.contractNo = contractNo;
        this.paymentId = paymentId;
    }
    
}
