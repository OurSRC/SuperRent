/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Vehicle;

import entity.VehicleClass;
import entity.Branch;
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
    
    private static int branchId;
    private static String branchName = "TestBranchName";
    private static String className = "TestClass_unittest";
    
    public VehicleDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws DaoException {
        VehicleClassDao vdao = new VehicleClassDao();
        VehicleClass vc = new VehicleClass(className, VehicleClass.TYPE.Car, 12, 12, 12);
        vdao.add(vc);
        
        BranchDao bdao = new BranchDao();
        Branch b = new Branch(branchName, "12313", "some addr test vehicle");
        bdao.add(b);
        b = bdao.findByName(branchName);
        branchId = b.getBranchID();
    }
    
    @AfterClass
    public static void tearDownClass() throws DaoException {
        VehicleClassDao vdao = new VehicleClassDao();
        VehicleClass vc = vdao.findByName(className);
        vdao.delete(vc);
        
        BranchDao bdao = new BranchDao();
        Branch b = bdao.findByName(branchName);
        bdao.delete(b);
    }
     
    @Before
    public void setUp() throws DaoException {
        
        
        
        VehicleDao dao = new VehicleDao();
        Vehicle entity;

        entity = new Vehicle("357-ADF", new Date(), "Mimi Copper", 100, branchId, 
                Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, 
                null, className, 0);
        dao.add(entity);
        entity = new Vehicle("357-ADG", new Date(), "BMW X5", 100, branchId, 
                Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, 
                null, className, 0);
        dao.add(entity);
        entity = new Vehicle("357-ADH", new Date(), "GM", 100, branchId, 
                Vehicle.STATUS.FORSALE, null, 
                Vehicle.SELLSTATUS.FORSALE, className, 10000000);
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
    
    //@Test
    public void testFindByVehicle() throws DaoException {
        VehicleDao dao = new VehicleDao();
        Vehicle v = new Vehicle();
        v.setClassName(className);
        v.setStatus(Vehicle.STATUS.FORRENT);
        ArrayList<Vehicle> vlist = dao.findByInstance(v);
        assertEquals(vlist.size(), 2);
        
        v.setPlateNo("357-ADF");
        vlist = dao.findByInstance(v);
        assertEquals(vlist.size(), 1);
        
        v.setStatus(Vehicle.STATUS.FORSALE);
        vlist = dao.findByInstance(v);
        assertEquals(vlist.size(), 0);
    }


  

}
