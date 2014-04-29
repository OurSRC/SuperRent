/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import Finance.CreditCardCtrl;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elitward
 */
public class CreditCardCtrlTest {
    
    public CreditCardCtrlTest() {
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
     * Test of create method, of class CreditCardCtrl.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String cardNum = "4532008861764514";
        Date expire = new Date(2018-1900, 10-1, 1);
        String name = "YEHUA DENG";
        boolean result;
        result = CreditCardCtrl.create(cardNum, expire, name);
        assertTrue(result);
        result = CreditCardCtrl.create(cardNum, expire, name);
        assertFalse(result);
    }
    
}
