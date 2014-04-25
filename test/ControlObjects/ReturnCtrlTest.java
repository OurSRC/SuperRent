/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import entity.Rent;
import entity.Return;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Hang
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
    }

    /**
     * Test of getReturnByPaymentId method, of class ReturnCtrl.
     */
    @Test
    public void testGetReturnByPaymentId() {
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
