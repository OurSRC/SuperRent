/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Dao.DaoException;
import Vehicle.VehicleClassDao;
import Vehicle.VehicleClass;
import java.util.ArrayList;
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
public class VehicleClassDaoTest {
    
    public VehicleClassDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        VehicleClass entity;
        entity = new VehicleClass("C1", VehicleClass.TYPE.Car, 10, 10, 10);
        dao.add(entity);
        entity = new VehicleClass("C2", VehicleClass.TYPE.Car, 20, 20, 20);
        dao.add(entity);
        entity = new VehicleClass("T1", VehicleClass.TYPE.Truck, 30, 30, 30);
        dao.add(entity);
        
    }
    
    @After
    public void tearDown() throws DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        VehicleClass entity;
        entity = new VehicleClass("C1", VehicleClass.TYPE.Car, 10, 10, 10);
        dao.delete(entity);
        entity = new VehicleClass("C2", VehicleClass.TYPE.Car, 20, 20, 20);
        dao.delete(entity);
        entity = new VehicleClass("T1", VehicleClass.TYPE.Truck, 30, 30, 30);
        dao.delete(entity);
    }
    
    

    @Test
    public void testAddDelete() throws DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        VehicleClass entity;
        entity = new VehicleClass("C3", VehicleClass.TYPE.Car, 10, 10, 10);
        dao.add(entity);
        dao.delete(entity);
    }
    
    @Test
    public void testFindByName() throws DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        VehicleClass entity = dao.findByName("C1");
        assertEquals(entity.getDailyRate(), 10);
        assertEquals(entity.getClassName(), "C1");
        assertEquals(entity.getVehicleType(), VehicleClass.TYPE.Car);
    }
    
    //@Test
    public void testFindByType() throws DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        ArrayList<VehicleClass> result = dao.findByClass(VehicleClass.TYPE.Car);
        assertEquals(result.size(), 2);
    }
    
    @Test
    public void testUpdate() throws  DaoException {
        VehicleClassDao dao = new VehicleClassDao();
        VehicleClass entity;
        entity = dao.findByName("C1");
        assertEquals(entity.getDailyRate(), 10);
        entity.setDailyRate(20);
        dao.update(entity);
        entity = null;
        entity = dao.findByName("C1");
        assertEquals(entity.getDailyRate(), 20);
        
    }
    
    
    
}
