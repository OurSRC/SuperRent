/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Branch;
import entity.VehicleClass;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BranchDaoTest {
    
    public BranchDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        BranchDao dao = new  BranchDao();
        Branch entity;
        entity = new Branch("FirstBranch", "123456", "At Paradise");
        boolean suc = false;
        try {
            suc = dao.add(entity);
        } catch (DaoException ex) {
            Logger.getLogger(BranchDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(suc);
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
     * Test of getInstance method, of class BranchDao.
     */
    @Test
    public void testGetInstance() {
        /*
        System.out.println("getInstance");
        BranchDao instance = new BranchDao();
        Branch expResult = null;
        Branch result = instance.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }
    
    @Test
    public void test1_AddDelete() throws DaoException {
        BranchDao dao = new  BranchDao();
        Branch entity = new Branch("testBranch", "123456", "At Paradise");
        
        boolean suc;
        suc = dao.add(entity);
        Branch found = dao.findByName("testBranch");
        suc = dao.delete(entity);
        assertTrue(suc);
    }
    
    @Test
    public void test2_Find() throws DaoException {
        BranchDao dao = new  BranchDao();
        Branch entity = new Branch("FirstBranch", "123456", "At Paradise");
        
        Branch found;
        found = dao.findByAddress(entity.getBranchAddress());
        assertEquals(found, entity);
        found = dao.findByName(entity.getBranchName());
        assertEquals(found, entity);
        found = dao.findByPhone(entity.getBranchPhone());
        assertEquals(found, entity);
    }
    
    @Test
    public void test3_Update() throws  DaoException {
        BranchDao dao = new  BranchDao();
        
        Branch found = dao.findByName("FirstBranch");
        assertTrue(found!=null);
        found.setBranchAddress(found.getBranchAddress()+"+");
        dao.update(found);
        Branch found2 = dao.findByName("FirstBranch");
        assertTrue(found2!=null);
        assertTrue( found.getBranchAddress().compareTo(found2.getBranchAddress())==0 );     //string same address
    }

}

