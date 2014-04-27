/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import entity.Payment;
import entity.Return;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Elitward
 */
public class PaymentCtrlTest {
    
    public PaymentCtrlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
     @Test
     public void hello() {
         int CustomerID = 5;
         int ContractNo = 2;
         int BranchID = 1;
         int Years = 3;
         Return returnInfo = ReturnCtrl.getReturnByContractNumber(ContractNo);
         PaymentCtrl paymentCtrl = new PaymentCtrl(CustomerID, "Pay Name", "343706449472636", new Date(), "Tim");
         paymentCtrl.addForReturn(returnInfo, true, true);
         paymentCtrl.addForMembershipFee(Years, BranchID);
         int total = paymentCtrl.getTotalAmount();
         Payment p = paymentCtrl.proceed();
     }
    
}
