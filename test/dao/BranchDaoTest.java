/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Dao.DaoException;
import SystemOperations.BranchDao;
import SystemOperations.Branch;
import Vehicle.VehicleClass;
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
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        BranchDao dao = new  BranchDao();
        Branch entity;
        entity = new Branch("FirstBranch", "123456", "At Paradise", 130, 18, 1000);
        boolean suc = false;
        try {
            suc = dao.add(entity);
        } catch (DaoException ex) {
            Logger.getLogger(BranchDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(suc);
    }
    
    @After
    public void tearDown() throws DaoException {
        BranchDao dao = new BranchDao();
        Branch entity = dao.findByName("FirstBranch");
        dao.delete(entity);
    }
    
    @Test
    public void test1_AddDelete() throws DaoException {
        BranchDao dao = new  BranchDao();
        Branch entity = new Branch("testBranch", "1234", "At Paradise2", 130, 18, 1000);
        
        boolean suc;
        suc = dao.add(entity);
        suc = dao.delete(entity);
        //Branch found = dao.findByName("testBranch");
        //suc = dao.delete(found);
        Branch found2 = dao.findByName("testBranch");
        assertEquals(found2, null);
        assertTrue(suc);
    }
    
    @Test
    public void test2_Find() throws DaoException {
        BranchDao dao = new  BranchDao();
        Branch entity = new Branch("FirstBranch", "123456", "At Paradise", 130, 18, 1000);
        
        Branch found;
        found = dao.findByAddress(entity.getBranchAddress());
        assertEquals(entity.getBranchAddress(), found.getBranchAddress());
        assertEquals(entity.getBranchName(), found.getBranchName());
        assertEquals(entity.getBranchPhone(), found.getBranchPhone());
        found = dao.findByName(entity.getBranchName());
        assertEquals(entity.getBranchAddress(), found.getBranchAddress());
        assertEquals(entity.getBranchName(), found.getBranchName());
        assertEquals(entity.getBranchPhone(), found.getBranchPhone());
        found = dao.findByPhone(entity.getBranchPhone());
        assertEquals(entity.getBranchAddress(), found.getBranchAddress());
        assertEquals(entity.getBranchName(), found.getBranchName());
        assertEquals(entity.getBranchPhone(), found.getBranchPhone());
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
        assertEquals(found.getBranchAddress(), found2.getBranchAddress());
        //assertTrue( found.getBranchAddress().compareTo(found2.getBranchAddress())==0 );     //string same address
    }

}

