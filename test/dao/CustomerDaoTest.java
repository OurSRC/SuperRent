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
    
    private static final String cname = "customer";

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
        Customer csmt = null;
        Customer csmt2 = null;
        try {
            csmt = new Customer(cname, "customer", "1234567", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
            csmt2 = new Customer("customer2", "customer2", "12345678", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomerDao cDAO = new CustomerDao();
        cDAO.add(csmt);
        cDAO.add(csmt2);

    }

    @After
    public void tearDown() throws DaoException {

        CustomerDao cDAO = new CustomerDao();
        Customer customer = cDAO.findByUsername(cname);
        cDAO.delete(customer);
        Customer customer2 = cDAO.findByUsername("customer2");
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
        assertEquals(result.getUsername(), cname);
        assertEquals(result.getPassword(), "customer");
        assertEquals(result.getType(), User.TYPE.CUSTOMER);

    }

    /**
     * Test of findByUsername method, of class CustomerDao.
     */
    @Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        String username = cname;
        CustomerDao instance = new CustomerDao();
        Customer customer = instance.findByUsername(username);
        assertEquals("customer", customer.getUsername());
        assertEquals("customer", customer.getPassword());
        assertEquals("1234567", customer.getPhone());
        assertEquals("firstName", customer.getFistName());
    }

    /**
     * Test of findByPhone method, of class CustomerDao.
     */
    @Test
    public void testFindByPhone() throws Exception {
        System.out.println("findByPhone");
        String phone = "1234567";
        CustomerDao instance = new CustomerDao();
        Customer customer = instance.findByPhone(phone);
        assertEquals(cname, customer.getUsername());
        assertEquals("customer", customer.getPassword());
        assertEquals("1234567", customer.getPhone());
        assertEquals("firstName", customer.getFistName());
    }

    /**
     * Test of update method, of class CustomerDao.
     */
    @Test
    public void testUpdate() throws DaoException {
        System.out.println("update");
        Customer customer = null;
        CustomerDao instance = new CustomerDao();
        UserDao udao = new UserDao();
        customer = instance.findByUsername(cname);
        assertEquals("firstName", customer.getFistName());
        assertEquals("customer", customer.getUsername());
        assertEquals("customer", customer.getPassword());
        
        

        // update name
        customer.setFistName("new name");
        instance.update(customer);
        customer = instance.findByUsername(cname);
        assertEquals("new name", customer.getFistName());

        //update password
        customer.setPassword("new pass");
        instance.update(customer);
        customer = instance.findByUsername(cname);
        assertEquals("new pass", customer.getPassword());
        
        // update username
        customer.setUsername("new username");
        instance.update(customer);
        customer = instance.findByUsername("new username");
        assertEquals("new username", customer.getUsername());
        assertEquals("new pass", customer.getPassword());
        assertEquals("1234567", customer.getPhone());
        // old username has need deleted
        assertEquals(null, udao.find("customer"));
        
        customer.setUsername(cname);
        instance.update(customer);

        
        // test to update existing username 
        boolean thrown = false;
        try {
            customer.setUsername("customer2");
            instance.update(customer);
        } catch (DaoException ex) {
            thrown = true;
        }
        assertTrue(thrown);
        customer.setUsername("customer");
        
        // test to update non unique phone number
        thrown = false;
        try {
            customer.setPhone("12345678");
            instance.update(customer);
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
            customer = new Customer(cname2, "customer2", "123456789", "No.111, ABC road", "firstName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
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
