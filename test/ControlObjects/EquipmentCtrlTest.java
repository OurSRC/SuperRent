/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import entity.Equipment;
import static entity.Equipment.STATUS.UNAVAILABLE;
import entity.Rent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Calendar.DATE;
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
 * @author Cesium
 */
public class EquipmentCtrlTest {

    public EquipmentCtrlTest() {
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
     * Test of createEquipment method, of class EquipmentCtrl.
     */
    @Test
    public void testCreateEquipment() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            d = df.parse("2018-04-02");
            EquipmentCtrl instance = new EquipmentCtrl();
            Equipment equipment;
            equipment = new Equipment("Ski Rack", UNAVAILABLE, "Thule", d, "Universal", 1);
            Equipment result = instance.createEquipment(equipment);
            assertTrue(result != null);
        } catch (ParseException ex) {
            Logger.getLogger(EquipmentCtrlTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of updateEquipment method, of class EquipmentCtrl.
     */
    @Test
    public void testUpdateEquipment() {
    }

    /**
     * Test of removeEquipment method, of class EquipmentCtrl.
     */
    @Test
    public void testRemoveEquipment() {
    }

    /**
     * Test of getEquipment method, of class EquipmentCtrl.
     */
    @Test
    public void testGetEquipment() {
    }

    /**
     * Test of searchEquipment method, of class EquipmentCtrl.
     */
    @Test
    public void testSearchEquipment() {
    }

    /**
     * Test of appendEquipmentTypeToVehicleClass method, of class EquipmentCtrl.
     */
    @Test
    public void testAppendEquipmentTypeToVehicleClass() {
    }

    /**
     * Test of removeEquipmentTypeFromVehicleClass method, of class
     * EquipmentCtrl.
     */
    @Test
    public void testRemoveEquipmentTypeFromVehicleClass() {
    }

    /**
     * Test of getEquipmentTypeByVehicleClass method, of class EquipmentCtrl.
     */
    @Test
    public void testGetEquipmentTypeByVehicleClass() {
    }

    /**
     * Test of getVehicleClassByEquipmentType method, of class EquipmentCtrl.
     */
    @Test
    public void testGetVehicleClassByEquipmentType() {
    }

    /**
     * Test of getEquipmentType method, of class EquipmentCtrl.
     */
    @Test
    public void testGetEquipmentType() {
    }

    /**
     * Test of checkEquipmentAvailability method, of class EquipmentCtrl.
     */
    @Test
    public void testCheckEquipmentAvailability() {
    }

}
