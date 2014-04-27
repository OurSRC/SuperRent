/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import entity.Branch;
import entity.Vehicle;
import entity.VehicleClass;
import entity.VehicleClass.TYPE;
import java.util.ArrayList;
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
public class VehicleCtrlTest {

    public VehicleCtrlTest() {
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

    @Test
    public void testCreateVehicle() {
    }

    @Test
    public void testUpdateVehicle() {
    }

    @Test
    public void testRemoveVehicle() {
    }

    @Test
    public void testGetVehicleByVehicleNo() {
    }

    @Test
    public void testGetVehicleByPlateNo() {
    }

    @Test
    public void testSearchVehicle() {
    }

    @Test
    public void testSearchIdleVehicles() {
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        Branch branch = new Branch("Main Branch", "12345678", "everywhere", 130, 18, 1000);
        ArrayList<Vehicle> result = vehicleCtrl.searchIdleVehicles("BOXTRUCKS", branch);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testSearchForSaleVehicles() {
    }

    @Test
    public void testGetAvailableVehicleClasses() {
    }

    @Test
    public void testCheckVehicleAvailability() {
    }

    @Test
    public void testCreateVehicleClass() {
    }

    @Test
    public void testDeleteVehicleClass() {
    }

    @Test
    public void testUpdateVehicleClass() {
    }

    @Test
    public void testFindVehicleClass() {
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        VehicleClass vehicleClass = vehicleCtrl.findVehicleClass("COMPACT");
        assertTrue(vehicleClass.getVehicleType() == TYPE.Car);
        assertTrue(vehicleClass.getDailyRate() == 5500);
        assertTrue(vehicleClass.getHourlyRate() == 1100);
        assertTrue(vehicleClass.getWeeklyRate() == 22000);
    }

    @Test
    public void testGetVehicleType() {
    }

    @Test
    public void testGetSubVehicleType() {
    }

    @Test
    public void testGetCarType() {
    }

    @Test
    public void testGetTruckType() {
    }

    @Test
    public void testGetVehicleTypeByClassName() {
    }

}
