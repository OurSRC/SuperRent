/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Insurance;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jingchuan Chen
 */
public class InsuranceDaoTest {
    
    public InsuranceDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DaoException {
        InsuranceDao idao = new InsuranceDao();
        Insurance i = new Insurance("InsuranceA", 10, 10, 10);
        idao.add(i);
    }
    
    @After
    public void tearDown() throws DaoException {
        InsuranceDao idao = new InsuranceDao();
        Insurance i = idao.findByName("InsuranceA");
        idao.delete(i);
        
    }

    /**
     * Test of findByName method, of class InsuranceDao.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        InsuranceDao idao = new InsuranceDao();
        Insurance i = idao.findByName("InsuranceA");
        assertNotNull(i);
        assertEquals(i.getDailyRate(), 10);
    }
    
}
