/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ControlObjects.LoginCtrl;
import dao.CustomerDao;
import entity.Customer;
import java.sql.SQLException;
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
 * @author Elitward
 */
public class DAO_Test {
    
    public DAO_Test() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test_CustomerDao() {
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer csmt=null;
        try {
            csmt = new Customer("customer", "customer", "1234567", "No.111, ABC road", "fistName", "middleName", "lastname", "customer@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
            csmt.setCustomerId(50);
        } catch (ParseException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomerDao cDAO = new CustomerDao();
        
        boolean suc;
        suc = cDAO.add(csmt);
        assertTrue(suc);
        suc = cDAO.delete(csmt.getCustomerId());
        assertTrue(suc);
        
        csmt.setLastName("lastlast");
        cDAO.update(csmt);
        
        Customer csmt2=null;
        try {
            csmt2 = cDAO.find(csmt.getCustomerId());
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Test.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        assertTrue(csmt.getLastName().equals(csmt2.getLastName()));

        suc = cDAO.add(csmt);
        assertTrue(suc);
        suc = cDAO.delete(csmt.getCustomerId());
        assertTrue(suc);
                */

    }
}
