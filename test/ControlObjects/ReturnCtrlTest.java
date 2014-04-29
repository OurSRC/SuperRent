/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import Operate.ReturnCtrl;
import Operate.Rent;
import Operate.Return;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Hang Miao
 */
public class ReturnCtrlTest {

    public ReturnCtrlTest() {
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

    /**
     * Test of createReturn method, of class ReturnCtrl.
     */
    @Test
    public void testCreateReturn_Return() {
//        try {
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            Date d = null;
//            d = df.parse("2018-04-02");
//            Return rtn;
//            rtn = new Return(2, d, 20, 120100, 5, 2, 0);
//            Return result = ReturnCtrl.createReturn(rtn);
//            assertTrue(result != null);
//        } catch (ParseException ex) {
//            Logger.getLogger(CustomerCtrlTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * Test of createReturn method, of class ReturnCtrl.
     */
    @Test
    public void testCreateReturn_5args() {
    }

    /**
     * Test of updateReturn method, of class ReturnCtrl.
     */
    @Test
    public void testUpdateReturn() {
    }

    /**
     * Test of deleteReturn method, of class ReturnCtrl.
     */
    @Test
    public void testDeleteReturn() {
    }

    /**
     * Test of searchReturn method, of class ReturnCtrl.
     */
    @Test
    public void testSearchReturn() {
    }

    /**
     * Test of searchFirstReturn method, of class ReturnCtrl.
     */
    @Test
    public void testSearchFirstReturn() {
    }

    /**
     * Test of getReturnByContractNumber method, of class ReturnCtrl.
     */
    @Test
    public void testGetReturnByContractNumber() {
        Return result = ReturnCtrl.getReturnByContractNumber(2);
        assertTrue(result.getContractNo() == 2);
        assertTrue(result.getDamageCost() == 0);
        assertTrue(result.getFuelLevel() == 20);
        assertTrue(result.getOdometer() == 120100);
        assertTrue(result.getPaymentId() == 2);
        assertTrue(result.getStaffId() == 5);
    }

    /**
     * Test of getReturnByPaymentId method, of class ReturnCtrl.
     */
    @Test
    public void testGetReturnByPaymentId() {
        Return result = ReturnCtrl.getReturnByPaymentId(2);
        assertTrue(result.getContractNo() == 2);
        assertTrue(result.getDamageCost() == 0);
        assertTrue(result.getFuelLevel() == 20);
        assertTrue(result.getOdometer() == 120100);
        assertTrue(result.getPaymentId() == 2);
        assertTrue(result.getStaffId() == 5);
    }

    /**
     * Test of getRerurnsByDate method, of class ReturnCtrl.
     */
    @Test
    public void testGetRerurnsByDate() throws Exception {
        ReturnCtrl ctrl = new ReturnCtrl();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        ArrayList<Return> result;

        d = df.parse("2014-03-17");
        result = ctrl.getRerurnsByDate(d, 1);
        assertEquals(result.size(), 2);

        d = df.parse("2014-03-01");
        result = ctrl.getRerurnsByDate(d, 1);
        assertEquals(result.size(), 1);
    }

}
