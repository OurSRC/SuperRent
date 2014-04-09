/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Vehicle;
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
 * @author Jingchuan Chen
 */
public class VehicleDaoTest {
    
    public VehicleDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
     
    @Before
    public void setUp() throws DaoException {
        VehicleDao dao = new VehicleDao();
        Vehicle entity;
        entity = new Vehicle("357-ADF", new Date(), "Mimi Copper", 100, 1, 
                Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.AVAILABLE, 
                null, "TestClass", 0);
        dao.add(entity);
        entity = new Vehicle("357-ADG", new Date(), "BMW X5", 100, 1, 
                Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.AVAILABLE, 
                null, "TestClass", 0);
        dao.add(entity);
        entity = new Vehicle("357-ADH", new Date(), "GM", 100, 1, 
                Vehicle.STATUS.FORSALE, null, 
                Vehicle.SELLSTATUS.FORSALE, "TestClass", 10000000);
        dao.add(entity);
    }
    
    @After
    public void tearDown() throws DaoException {
        VehicleDao dao = new VehicleDao();
        Vehicle entity;
        entity = dao.findByPlateNo("357-ADF");
        dao.delete(entity);
        entity = dao.findByPlateNo("357-ADG");
        dao.delete(entity);
        entity = dao.findByPlateNo("357-ADH");
        dao.delete(entity);
    }
    
    @Test
    public void testUpdate() throws DaoException {
        VehicleDao dao = new VehicleDao();
        Vehicle entity;
        entity = dao.findByPlateNo("357-ADF");
        entity.setOdometer(500);
        dao.update(entity);
        Vehicle result = dao.findByPlateNo("357-ADF");
        assertEquals(result.getOdometer(), 500);
    }
    
    @Test
    public void testFind() throws  DaoException {
        VehicleDao dao = new VehicleDao();
        ArrayList<Vehicle> result;
        result = dao.findAvailableForRent();
        assertEquals(result.size(), 2);
        Vehicle entity = result.get(0);
        if (entity.getPlateNo().equals("357-ADF") || entity.getPlateNo().equals("357-ADG")) {
        } else {
            fail("not found");
        }
        
        result = dao.findAvailableForSale();
        assertEquals(result.size(), 1);
        entity = result.get(0);
        if (!entity.getPlateNo().equals("357-ADH")) {
            fail("not found");
        }
    }


    
}
