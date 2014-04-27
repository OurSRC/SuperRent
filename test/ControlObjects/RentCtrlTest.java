/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Rent;

/**
 *
 * @author Jingchuan Chen
 */
public class RentCtrlTest {

    public RentCtrlTest() {
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
     * Test of createRent method, of class RentCtrl.
     */
    @Test
    public void testCreateRent_Rent() {
    }

    /**
     * Test of createRent method, of class RentCtrl.
     */
    @Test
    public void testCreateRent_6args() {
    }

    /**
     * Test of updateRent method, of class RentCtrl.
     */
    @Test
    public void testUpdateRent() {
    }

    /**
     * Test of cancelRent method, of class RentCtrl.
     */
    @Test
    public void testCancelRent() {
    }

    /**
     * Test of deleteRent method, of class RentCtrl.
     */
    @Test
    public void testDeleteRent() {
    }

    /**
     * Test of searchRent method, of class RentCtrl.
     */
    @Test
    public void testSearchRent() {
    }

    /**
     * Test of searchFirstRent method, of class RentCtrl.
     */
    @Test
    public void testSearchFirstRent() {
    }

    /**
     * Test of getRentByContractNumber method, of class RentCtrl.
     */
    @Test
    public void testGetRentByContractNumber() {
    }

    /**
     * Test of getRent method, of class RentCtrl.
     */
    @Test
    public void testGetRent_Reservation() {
        RentCtrl instance = new RentCtrl();
        Rent rent = instance.searchRentByReservationInfoId(1);
        assertTrue(rent.getReservationInfold() == 1);
        assertTrue(rent.getVehicleNo() == 5);
        assertTrue(rent.getFuelLevel() == 30);
        assertTrue(rent.getOdometer() == 100000);
        assertTrue(rent.getCreditCardNo().equals("5202601352321285"));
        assertTrue(rent.getStaffId() == 2);

    }

    /**
     * Test of getRent method, of class RentCtrl.
     */
    @Test
    public void testGetRent_int() {
    }

    /**
     * Test of getRentsByDate method, of class RentCtrl.
     */
    @Test
    public void testGetRentsByDate() throws Exception {
        RentCtrl ctl = new RentCtrl();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        ArrayList<Rent> result;

        d = df.parse("2014-04-02");
        result = ctl.getRentsByDate(d, 1);
        assertEquals(result.size(), 1);

        d = df.parse("2014-04-18");
        result = ctl.getRentsByDate(d, 1);
        assertEquals(result.size(), 1);

        d = df.parse("2014-10-02");
        result = ctl.getRentsByDate(d, 1);
        assertEquals(result.size(), 0);

    }

}
