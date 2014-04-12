/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.ReservationInfo;
import entity.Branch;
import entity.Customer;
import entity.Staff;
import entity.VehicleClass;

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
public class ReservationInfoDaoTest {
    
    private static int branchId;
    private static int customerId;
    private static int staffId;
    private static String vehicleClass = "test_reservation_info_vclass";
    
    public ReservationInfoDaoTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws DaoException {
        Branch b = new Branch("bname_test res", "test res phone", "test res address");
        BranchDao bdao = new BranchDao();
        bdao.add(b);
        b = bdao.findByName("bname_test res");
        branchId = b.getBranchID();
        
        Customer c = new Customer("cname", "somepass", "lsjdkf", "sle", "fn cus", 
                null, "mn cus", "some mail", "DriverLic", false, 0, null);
        CustomerDao cdao = new CustomerDao();
        cdao.add(c);
        c = cdao.findByUsername("cname");
        customerId = c.getCustomerId();
        
        Staff s = new Staff(branchId, "s fn", null, "s ln", "staff email", "staff phone", 
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, "sname", "spass");
        StaffDao sdao = new StaffDao();
        sdao.add(s);
        s = sdao.findByUsername("sname");
        staffId = s.getStaffId();
        
        VehicleClass vc = new VehicleClass(vehicleClass, VehicleClass.TYPE.Car, 10, 10, 10);
        VehicleClassDao vcdao = new VehicleClassDao();
        vcdao.add(vc);
        
    }
    
    @AfterClass
    public static void tearDownClass() throws DaoException {
        BranchDao bdao = new BranchDao();
        Branch b = bdao.findByName(vehicleClass);
        bdao.delete(b);
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ReservationInfoDao.
     */
    @Test
    public void testAdd() {
        System.out.println("getInstance");
        ReservationInfoDao instance = new ReservationInfoDao();
        ReservationInfo expResult = null;
        ReservationInfo result = instance.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
