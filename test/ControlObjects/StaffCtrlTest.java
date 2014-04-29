/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import Account.StaffCtrl;
import Account.Staff;
import java.util.ArrayList;
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
public class StaffCtrlTest {
    
    public StaffCtrlTest() {
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
     * Test of searchStaff method, of class StaffCtrl.
     */
    @Test
    public void testSearchStaff() {
        System.out.println("searchStaff");
        Staff staff = new Staff();
        staff.setUsername("manager");
        StaffCtrl instance = new StaffCtrl();
        ArrayList<Staff> expResult = null;
        ArrayList<Staff> result = instance.searchStaff(staff);
        assertTrue(result!=null);
    }
    
}
