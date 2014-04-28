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
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            d = df.parse("2018-04-02");
            VehicleCtrl instance = new VehicleCtrl();
            Vehicle vec;
            vec = new Vehicle("DS1231", d, "Renault Laguna", 61446,
                    1, Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, null,
                    "ECONOMY", 2222222);
            Vehicle result = instance.createVehicle(vec);
            assertTrue(result != null);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerCtrlTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdateVehicle() {
    }

    @Test
    public void testRemoveVehicle() {
    }

    @Test
    public void testGetVehicleByVehicleNo() {
        VehicleCtrl instance = new VehicleCtrl();
        Vehicle vec = instance.getVehicleByVehicleNo(1);

        assertTrue(vec.getVehicleNo() == 1);
        assertTrue(vec.getPlateNo().equals("DS1111"));
        assertTrue(vec.getMode().equals("Renault Laguna"));
        assertTrue(vec.getOdometer() == 61446);
        assertTrue(vec.getBranchId() == 1);
        assertTrue(vec.getStatus().equals(Vehicle.STATUS.FORRENT));
        assertTrue(vec.getRentStatus().equals(Vehicle.RENTSTATUS.IDLE));
        assertTrue(vec.getClassName().equals("ECONOMY"));
    }

    @Test
    public void testGetVehicleByPlateNo() {
        VehicleCtrl instance = new VehicleCtrl();
        Vehicle vec = instance.getVehicleByPlateNo("DS1111");

        assertTrue(vec.getVehicleNo() == 1);
        assertTrue(vec.getPlateNo().equals("DS1111"));
        assertTrue(vec.getMode().equals("Renault Laguna"));
        assertTrue(vec.getOdometer() == 61446);
        assertTrue(vec.getBranchId() == 1);
        assertTrue(vec.getStatus().equals(Vehicle.STATUS.FORRENT));
        assertTrue(vec.getRentStatus().equals(Vehicle.RENTSTATUS.IDLE));
        assertTrue(vec.getClassName().equals("ECONOMY"));
    }

    @Test
    public void testSearchVehicle() {
    }

    @Test
    public void testSearchIdleVehicles() {
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
        Branch branch = new Branch("Main Branch", "12345678", "everywhere", 130, 18, 1000);
        BranchCtrl branchCtrl = new BranchCtrl();
        ArrayList<Vehicle> result = vehicleCtrl.searchIdleVehicles("BOXTRUCKS", branchCtrl.getBranchById(1));
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i);
            assertEquals(result.get(i).getRentStatus(), Vehicle.RENTSTATUS.IDLE);
        }
    }

    @Test
    public void testSearchForSaleVehicles() {
        VehicleCtrl vehicleCtrl = new VehicleCtrl();
//        Branch branch = new Branch("Main Branch", "12345678", "everywhere", 130, 18, 1000);
        BranchCtrl branchCtrl = new BranchCtrl();
        ArrayList<Vehicle> result = vehicleCtrl.searchForSaleVehicles("BOXTRUCKS", branchCtrl.getBranchById(1));
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i);
            assertEquals(result.get(i).getRentStatus(), Vehicle.SELLSTATUS.FORSALE);
        }
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
