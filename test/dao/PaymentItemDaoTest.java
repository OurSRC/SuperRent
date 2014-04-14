/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Customer;
import entity.PaymentItem;
import entity.Payment;
import java.util.ArrayList;
import java.util.Date;
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
public class PaymentItemDaoTest {
    
    private static Payment p;
    private static PaymentItem pi;
    private static int paymentId;
    
    public PaymentItemDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws DaoException {
        CustomerDao cdao = new CustomerDao();
        Customer c = new Customer("cname_test", "somepass", "123123", 
                "somd addr", "fn", null, "Ln", "gmail", "lic", false, 0, null);
        cdao.add(c);
        c = cdao.findByUsername("cname_test");
        int customerId = c.getCustomerId();
        
        PaymentDao pdao = new PaymentDao();
        p = new Payment(customerId, "title", 100, null, new Date());
        pdao.add(p);
        p = pdao.findByInstance(p).get(0);
        paymentId = p.getPaymentId();
    }
    
    @AfterClass
    public static void tearDownClass() throws DaoException {
        PaymentDao pdao = new PaymentDao();
        Payment tmp = pdao.findByInstance(p).get(0);
        pdao.delete(tmp);
        
        CustomerDao cdao = new CustomerDao();
        Customer c = cdao.findByUsername("cname_test");
        cdao.delete(c);
    }
    
    @Before
    public void setUp() throws DaoException {
        PaymentItemDao piDao = new PaymentItemDao();
        pi = new PaymentItem(p.getPaymentId(), PaymentItem.ITEMTYPE.VEHICLE, "BMW", 1000, 2);
        piDao.add(pi);
    }
    
    @After
    public void tearDown() throws DaoException {
        PaymentItemDao piDao = new PaymentItemDao();
        PaymentItem tmp = piDao.findByInstance(pi).get(0);
        piDao.delete(tmp);
    }

    /**
     * Test of findByPaymentId method, of class PaymentItemDao.
     */
    @Test
    public void testFindByPaymentId() throws Exception {
        PaymentDao pdao = new PaymentDao();
        Payment tmp = pdao.findByInstance(p).get(0);
        int paymentId = tmp.getPaymentId();
        
        PaymentItemDao piDao = new PaymentItemDao();
        
        ArrayList<PaymentItem> piList = piDao.findByPaymentId(paymentId);
        assertEquals(piList.size(), 1);
    }
}
