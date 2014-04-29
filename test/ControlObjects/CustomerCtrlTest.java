/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import Account.CustomerCtrl;
import Account.Customer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Hang Miao
 */
public class CustomerCtrlTest {

    public CustomerCtrlTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void testCreateCustomer() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            d = df.parse("2018-04-02");
            CustomerCtrl instance = new CustomerCtrl();
            Customer cstm;
            cstm = new Customer("jzhang", "123456", "7788556632", "1234 12th AVE.",
                    "Jane", "", "Zhang", "jzhang@gmail.com",
                    "5240912", true, 1000, d
            );
            Customer result = instance.createCustomer(cstm);
            assertTrue(result != null);
        } catch (ParseException ex) {
            Logger.getLogger(CustomerCtrlTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUpdateCustomer() {
    }

    @Test
    public void testDeleteCustomer() {
    }

    @Test
    public void testGetCustomerByPhone() {
        
        CustomerCtrl instance = new CustomerCtrl();
        Customer cstm = instance.getCustomerByPhone("7788364782");
        assertTrue(cstm.getAddress().equals("5609 IMPERIAL STREET"));
        assertTrue(cstm.getDriverLicenseNumber().equals("5240912"));
        assertTrue(cstm.getEmail().equals("jlz@hotmail.com"));
        assertTrue(cstm.getPhone().equals("7788364782"));
        assertTrue(cstm.getFirstName().equals("Jake"));
        assertTrue(cstm.getMiddleName().equals("Tom"));
        assertTrue(cstm.getLastName().equals("Zhang"));
        assertTrue(cstm.getUsername().equals("jakezhang"));

    }

    @Test
    public void testGetCustomerByUsername() {
        
        CustomerCtrl instance = new CustomerCtrl();
        Customer cstm = instance.getCustomerByUsername("jakezhang");
        assertTrue(cstm.getAddress().equals("5609 IMPERIAL STREET"));
        assertTrue(cstm.getDriverLicenseNumber().equals("5240912"));
        assertTrue(cstm.getEmail().equals("jlz@hotmail.com"));
        assertTrue(cstm.getPhone().equals("7788364782"));
        assertTrue(cstm.getFirstName().equals("Jake"));
        assertTrue(cstm.getMiddleName().equals("Tom"));
        assertTrue(cstm.getLastName().equals("Zhang"));
        assertTrue(cstm.getUsername().equals("jakezhang"));
    }

    @Test
    public void testGetCustomerById() {

        CustomerCtrl instance = new CustomerCtrl();
        Customer cstm = instance.getCustomerById(1);
        assertTrue(cstm.getAddress().equals("5609 IMPERIAL STREET"));
        assertTrue(cstm.getDriverLicenseNumber().equals("5240912"));
        assertTrue(cstm.getEmail().equals("jlz@hotmail.com"));
        assertTrue(cstm.getPhone().equals("7788364782"));
        assertTrue(cstm.getFirstName().equals("Jake"));
        assertTrue(cstm.getMiddleName().equals("Tom"));
        assertTrue(cstm.getLastName().equals("Zhang"));
        assertTrue(cstm.getUsername().equals("jakezhang"));

    }

    @Test
    public void testSearchCustomer() {
    }

    @Test
    public void testCheckMembershipActive() {
        
        CustomerCtrl instance = new CustomerCtrl();
        Customer cstm = instance.getCustomerById(1);
        assertTrue(cstm.getIsClubMember());
 
        Customer william = instance.getCustomerById(4);
        assertTrue(!william.getIsClubMember());
    }

    @Test
    public void testExtendMembership() {
    }

    @Test
    public void testPayClubMemberFee() {
    }

}
