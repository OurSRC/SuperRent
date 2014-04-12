/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Payment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 *
 * @author Jingchuan Chen
 */
public class PaymentDaoTest {
    
    public PaymentDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DaoException {
        Date d = new Date();
        Payment p = new Payment(5000, d, Payment.PAYMETHOD.CASH, 0, null);
        PaymentDao pdao = new PaymentDao();
        pdao.add(p);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findById method, of class PaymentDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
    }
    
}
