/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Dao.DaoException;
import Finance.PaymentDao;
import Account.CustomerDao;
import Finance.Payment;
import Account.Customer;

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
    private static int customerId;
    
    public PaymentDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws DaoException {
        CustomerDao cdao = new CustomerDao();
        Customer c = new Customer("cname_test", "somepass", "123123", 
                "somd addr", "fn", null, "Ln", "gmail", "lic", false, 0, null);
        cdao.add(c);
        c = cdao.findByUsername("cname_test");
        customerId = c.getCustomerId();
    }
    
    @AfterClass
    public static void tearDownClass() throws DaoException {
        CustomerDao cdao = new CustomerDao();
        Customer c = cdao.findByUsername("cname_test");
        cdao.delete(c);
    }
    
    @Before
    public void setUp() throws DaoException {
        Date d = new Date();
        p = new Payment(customerId, "Test payment", 100, null, d);
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
    public void testFind() throws Exception {
        PaymentDao pdao = new PaymentDao();
        
        ArrayList<Payment> pList = pdao.findByInstance(p);
        assertEquals(pList.size(), 1);
    }
    
}
