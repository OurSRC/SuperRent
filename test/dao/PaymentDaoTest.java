/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Payment;
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
public class PaymentDaoTest {
    
    private static Payment p;
    
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
        p = new Payment(5001, d, Payment.PAYMETHOD.CASH, 0, null);
        PaymentDao pdao = new PaymentDao();
        pdao.add(p);
    }
    
    @After
    public void tearDown() throws DaoException {
        PaymentDao pdao = new PaymentDao();
        p = pdao.findByInstance(p).get(0);
        pdao.delete(p);
    }

    /**
     * Test of findById method, of class PaymentDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        ArrayList<Payment> pList;
        PaymentDao pdao = new PaymentDao();
        pList = pdao.findByInstance(p);
        System.out.println(pList.size());
    }
    
}
