/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import entity.Branch;
import entity.Customer;
import entity.Staff;
import java.sql.Time;
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
 * @author Elitward
 */
public class ReserveCtrlTest {
    private  static Reservation sReserve;
    
    public ReserveCtrlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        CustomerCtrl customerCtrl = new CustomerCtrl();
        Customer customer = customerCtrl.getCustomerByPhone("7788364782");
        StaffCtrl staffCtrl = new StaffCtrl();
        Staff staff = staffCtrl.getStaffByUsername("admin");
        ArrayList<String> equipments = new ArrayList<>();
        equipments.add("Child Safety Seat");
        equipments.add("Ski Rack");
        ArrayList<String> insurances = new ArrayList<>();
        insurances.add("DW");
        insurances.add("error_insurance");
        
        Reservation reserve = new Reservation(BranchCtrl.getDefaultBranch().getBranchID(), 
                new Date(2014-1900, 4-1, 11, 9, 0, 0), new Date(2014-1900, 4-1, 12, 10, 0, 0), customer.getCustomerId(), staff.getStaffId(), 
                "COMPACT", equipments, insurances);
                //"COMPACT", null, null);
        sReserve = reserve;
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
     * Test of createReserve method, of class ReserveCtrl.
     */
    @Test
    public void testCreateReserve() {
        System.out.println("createReserve");

        ReserveCtrl instance = new ReserveCtrl();
        Reservation reserve = new Reservation();

        Reservation result = instance.createReserve(sReserve);
        assertTrue( result!=null );
        
        FinanceCtrl financeCtrl = new FinanceCtrl();
        int cent = financeCtrl.estimateReservationCost(result);
        assertTrue( cent > 0 );
    }

    /**
     * Test of updateReserve method, of class ReserveCtrl.
     */
    @Test
    public void testUpdateReserve() {
        /*
        System.out.println("updateReserve");
        ReserveCtrl instance = new ReserveCtrl();
        boolean expResult = false;
        
        boolean result = instance.updateReserve(sReserve);
        assertTrue(result);
        */
    }

    /**
     * Test of deleteReserve method, of class ReserveCtrl.
     */
    @Test
    public void testDeleteReserve() {
        /*
        System.out.println("deleteReserve");
        int reserveId = 0;
        ReserveCtrl instance = new ReserveCtrl();
        boolean expResult = false;
        boolean result = instance.deleteReserve(reserveId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of getReserve method, of class ReserveCtrl.
     */
    @Test
    public void testGetReserve_int() {
        /*
        System.out.println("getReserve");
        int reserveId = 0;
        ReserveCtrl instance = new ReserveCtrl();
        Reservation expResult = null;
        Reservation result = instance.getReserve(reserveId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of getReserve method, of class ReserveCtrl.
     */
    @Test
    public void testGetReserve_String() {
        /*
        System.out.println("getReserve");
        String ReservationNumber = "";
        ReserveCtrl instance = new ReserveCtrl();
        Reservation expResult = null;
        Reservation result = instance.getReserve(ReservationNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of searchReserve method, of class ReserveCtrl.
     */
    @Test
    public void testSearchReserve() {
        /*
        System.out.println("searchReserve");
        Reservation reserve = null;
        ReserveCtrl instance = new ReserveCtrl();
        ArrayList<Reservation> expResult = null;
        ArrayList<Reservation> result = instance.searchReserve(reserve);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of searchReserveBetween method, of class ReserveCtrl.
     */
    @Test
    public void testSearchReserveBetween() {
        /*
        System.out.println("searchReserveBetween");
        Date pickUpTime = null;
        Date returnTime = null;
        Branch branch = null;
        ReserveCtrl instance = new ReserveCtrl();
        ArrayList<Reservation> expResult = null;
        ArrayList<Reservation> result = instance.searchReserveBetween(pickUpTime, returnTime, branch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }
    
}
