/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ControlObjects.LoginCtrl;
import entity.Customer;
import entity.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerDaoTest {
    
    private static final String cname1 = "customer1";
    private static final String cname2 = "customer2";

    public CustomerDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws DaoException {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DaoException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer csmt1 = null;
        Customer csmt2 = null;
        try {
            csmt1 = new Customer(cname1, cname1, "1234568", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
            csmt2 = new Customer(cname2, cname2, "1234569", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomerDao cDAO = new CustomerDao();
        cDAO.add(csmt1);
        cDAO.add(csmt2);

    }

    @After
    public void tearDown() throws DaoException {

        CustomerDao cDAO = new CustomerDao();
        Customer customer1 = cDAO.findByUsername(cname1);
        cDAO.delete(customer1);
        Customer customer2 = cDAO.findByUsername(cname2);
        cDAO.delete(customer2);

    }
    
    @Test
    public void testTmp() {
        
    }

    /**
     * Test of find method, of class CustomerDao.
     */
    //@Test
    public void testFind() throws Exception {
        System.out.println("find");
        Integer customerID = 50;
        CustomerDao instance = new CustomerDao();
        Customer result = instance.find(customerID);
        assertEquals(result.getCustomerId(), 50);
        assertEquals(result.getPhone(), "1234567");
        assertEquals(result.getUsername(), cname1);
        assertEquals(result.getPassword(), "customer");
        assertEquals(result.getType(), User.TYPE.CUSTOMER);

    }

    /**
     * Test of findByUsername method, of class CustomerDao.
     */
    @Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        String username = cname1;
        CustomerDao instance = new CustomerDao();
        Customer customer = instance.findByUsername(username);
        assertEquals(cname1, customer.getUsername());
        assertEquals(cname1, customer.getPassword());
        assertEquals("1234568", customer.getPhone());
        assertEquals("firstName", customer.getFirstName());
    }

    /**
     * Test of findByPhone method, of class CustomerDao.
     */
    @Test
    public void testFindByPhone() throws Exception {
        System.out.println("findByPhone");
        String phone = "1234568";
        CustomerDao instance = new CustomerDao();
        Customer customer = instance.findByPhone(phone);
        assertEquals(cname1, customer.getUsername());
        assertEquals(cname1, customer.getPassword());
        assertEquals(phone, customer.getPhone());
        assertEquals("firstName", customer.getFirstName());
    }

    /**
     * Test of update method, of class CustomerDao.
     */
    @Test
    public void testUpdate() throws DaoException {
        System.out.println("update");
        Customer customer1 = null;
        CustomerDao instance = new CustomerDao();
        UserDao udao = new UserDao();
        customer1 = instance.findByUsername(cname1);
        assertEquals("firstName", customer1.getFirstName());
        assertEquals(cname1, customer1.getUsername());
        assertEquals(cname1, customer1.getPassword());

        // update name
        customer1.setFirstName("new name");
        instance.update(customer1);
        customer1 = instance.findByUsername(cname1);
        assertEquals("new name", customer1.getFirstName());

        //update password
        customer1.setPassword("new pass");
        instance.update(customer1);
        customer1 = instance.findByUsername(cname1);
        assertEquals("new pass", customer1.getPassword());
        
        // update username
        customer1.setUsername("new username");
        instance.update(customer1);
        customer1 = instance.findByUsername("new username");
        assertEquals("new username", customer1.getUsername());
        assertEquals("new pass", customer1.getPassword());
        assertEquals("1234568", customer1.getPhone());
        // old username has need deleted
        assertEquals(null, udao.find(cname1));
        
        customer1.setUsername(cname1);
        instance.update(customer1);

        // test to update existing username 
        boolean thrown = false;
        try {
            customer1.setUsername(cname2);
            instance.update(customer1);
        } catch (DaoException ex) {
            thrown = true;
        }
        assertTrue(thrown);
        customer1.setUsername(cname1);
        
        // test to update non unique phone number
        thrown = false;
        try {
            customer1.setPhone("1234569");
            instance.update(customer1);
        } catch (DaoException ex) {
            thrown = true;
        }
        assertTrue(thrown);
        
    }

    /**
     * Test of add method, of class CustomerDao.
     */
    //@Test
    public void testAddDelete() throws DaoException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("addDelete");
        Customer customer = null;
        CustomerDao instance = new CustomerDao();
        
        String cname2 = "another customername";

        try {
            customer = new Customer(cname2, cname2, "123456789", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(CustomerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //test add
        instance.add(customer);
        if (instance.findByUsername(cname2) == null) {
            fail("fail to add record");
        }

        //test delete
        instance.delete(customer);
        if (instance.findByUsername(cname2) != null) {
            fail("fail to delete record");
        }

        
        //test add non unique field
        boolean thrown = false;
        customer.setPhone("1234567");
        try {
            instance.add(customer);
        } catch (DaoException ex) {
            thrown = true;
        }
        assertTrue(thrown);

        
        try {
            //test add customer without username
            customer = new Customer(null, null, "123456789", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(CustomerDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.add(customer);
        customer = instance.findByPhone("123456789");
        if (customer == null) {
            fail("add fail");
        }
        
        assertEquals(null, customer.getUsername());
        
        instance.delete(customer);
        
    }
}
