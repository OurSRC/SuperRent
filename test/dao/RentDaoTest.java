/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Branch;
import entity.Customer;
import entity.Rent;
import entity.ReservationInfo;
import entity.Staff;
import entity.VehicleClass;
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
public class RentDaoTest {
    
    private static final String customerUName = "cname_test_resinfo";
    private static final String staffUName = "sname_test_resingo";
    private static String vehicleClassName = "test_reservation_info_vclass";
    private static String contractNo = "test_rent_contact";

    public RentDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws DaoException {
        int branchId;
        int customerId;
        int staffId;

        Branch b = new Branch("bname_test rent", "test rent phone", "test rent address");
        BranchDao bdao = new BranchDao();
        bdao.add(b);
        b = bdao.findByName("bname_test rent");
        branchId = b.getBranchID();

        Customer c = new Customer(customerUName, "somepass", "lsjdkf", "sle", "fn cus",
                null, "mn cus", "some mail", "DriverLic", false, 0, null);
        CustomerDao cdao = new CustomerDao();
        cdao.add(c);
        c = cdao.findByUsername(customerUName);
        customerId = c.getCustomerId();

        Staff s = new Staff(branchId, "s fn", null, "s ln", "staff email", "staff phone",
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, staffUName, "spass");
        StaffDao sdao = new StaffDao();
        sdao.add(s);
        s = sdao.findByUsername(staffUName);
        staffId = s.getStaffId();

        VehicleClass vc = new VehicleClass(vehicleClassName, VehicleClass.TYPE.Car, 10, 10, 10);
        VehicleClassDao vcdao = new VehicleClassDao();
        vcdao.add(vc);
        
        ReservationInfoDao riDao = new ReservationInfoDao();
        ReservationInfo ri = new ReservationInfo(branchId, new Date(), new Date(), 
                new Date(), customerId, staffId, vehicleClassName, 10, 10, 10,
                "ResNo", ReservationInfo.STATUS.PENDING);
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

    /**
     * Test of getInstance method, of class RentDao.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        RentDao instance = new RentDao();
        Rent expResult = null;
        Rent result = instance.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByContractNo method, of class RentDao.
     */
    @Test
    public void testFindByContractNo() throws Exception {
        System.out.println("findByContractNo");
        String contractNo = "";
        RentDao instance = new RentDao();
        Rent expResult = null;
        Rent result = instance.findByContractNo(contractNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
