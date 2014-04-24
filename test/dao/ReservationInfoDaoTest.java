/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Branch;
import entity.Customer;
import entity.ReservationInfo;
import entity.Staff;
import entity.VehicleClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ReservationInfoDaoTest {
    
    private static int branchId;
    private static int customerId;
    private static int staffId;
    private static final String customerUName = "cname_test_resinfo";
    private static final String staffUName = "sname_test_resingo";
    private static String vehicleClassName = "test_reservation_info_vclass";
    
    
    public ReservationInfoDaoTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws DaoException {
        Branch b = new Branch("bname_test res", "test res phone", "test res address");
        BranchDao bdao = new BranchDao();
        bdao.add(b);
        b = bdao.findByName("bname_test res");
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
        
    }
    
    @AfterClass
    public static void tearDownClass() throws DaoException {
        StaffDao sdao = new StaffDao();
        Staff s = sdao.findByUsername(staffUName);
        sdao.delete(s);
        
        BranchDao bdao = new BranchDao();
        Branch b = bdao.findById(branchId);
        bdao.delete(b);
        
        CustomerDao cdao = new CustomerDao();
        Customer c = cdao.findByUsername(customerUName);
        cdao.delete(c);
        
        VehicleClassDao vdao = new VehicleClassDao();
        VehicleClass vc = vdao.findByName(vehicleClassName);
        vdao.delete(vc);
        
    }
    
    @Before
    public void setUp() throws DaoException, ParseException {
        ReservationInfoDao riDao = new ReservationInfoDao();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date pickupT;
        Date returnT;
        ReservationInfo rinfo;
        
        
        pickupT = ft.parse("2014-04-10 13:00:00");
        returnT = ft.parse("2014-04-13 10:00:00");
        rinfo = new ReservationInfo(branchId, new Date(), pickupT, 
                returnT, customerId, staffId, vehicleClassName, 10, 10, 10,
                "ResNo1", ReservationInfo.STATUS.PENDING);
        riDao.add(rinfo);
        
        pickupT = ft.parse("2014-04-09 12:00:00");
        returnT = ft.parse("2014-04-11 12:00:00");
        rinfo = new ReservationInfo(branchId, new Date(), pickupT, 
                returnT, customerId, staffId, vehicleClassName, 10, 10, 10,
                "ResNo2", ReservationInfo.STATUS.PENDING);
        riDao.add(rinfo);
        
        pickupT = ft.parse("2014-04-12 12:00:00");
        returnT = ft.parse("2014-04-14 12:00:00");
        rinfo = new ReservationInfo(branchId, new Date(), pickupT, 
                returnT, customerId, staffId, vehicleClassName, 10, 10, 10,
                "ResNo3", ReservationInfo.STATUS.PENDING);
        riDao.add(rinfo);
        
        pickupT = ft.parse("2014-04-10 13:00:00");
        returnT = ft.parse("2014-04-13 10:00:00");
        rinfo = new ReservationInfo(branchId, new Date(), pickupT, 
                returnT, customerId, staffId, vehicleClassName, 10, 10, 10,
                "ResNo4", ReservationInfo.STATUS.CANCELED);
        riDao.add(rinfo);
    }
    
    @After
    public void tearDown() throws DaoException {
        ReservationInfoDao riDao = new ReservationInfoDao();
        ReservationInfo rd;
        
        rd = riDao.findByReservationNo("ResNo1");
        riDao.delete(rd);
        
        rd = riDao.findByReservationNo("ResNo2");
        riDao.delete(rd);
        
        rd = riDao.findByReservationNo("ResNo3");
        riDao.delete(rd);
        
        rd = riDao.findByReservationNo("ResNo4");
        riDao.delete(rd);
    }

    /**
     * Test of getInstance method, of class ReservationInfoDao.
     */
    @Test
    public void testFindReservationBetween() throws ParseException, DaoException {
        ReservationInfoDao riDao = new ReservationInfoDao();
        ArrayList<ReservationInfo> rInfoList;
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Branch b = new Branch();
        b.setBranchID(branchId);
        Date pickupT;
        Date returnT;
        
        pickupT = ft.parse("2014-04-09 13:00:00");
        returnT = ft.parse("2014-04-11 10:00:00");
        rInfoList = riDao.findReservationBetween(vehicleClassName, pickupT, returnT, b);
        assertEquals(rInfoList.size(), 2);
        
        pickupT = ft.parse("2014-04-11 12:00:00");
        returnT = ft.parse("2014-04-12 12:00:00");
        rInfoList = riDao.findReservationBetween(vehicleClassName, pickupT, returnT, b);
        assertEquals(rInfoList.size(), 1);
        
        pickupT = ft.parse("2014-04-7 12:00:00");
        returnT = ft.parse("2014-04-16 12:00:00");
        rInfoList = riDao.findReservationBetween(vehicleClassName, pickupT, returnT, b);
        assertEquals(rInfoList.size(), 3);
    }
    
    @Test
    public void testMakeReservationInfo() throws ParseException, DaoException {
        ReservationInfoDao riDao = new ReservationInfoDao();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date pickupT;
        Date returnT;
        ReservationInfo rinfo;
        
        
        pickupT = ft.parse("2014-04-10 14:00:00");
        returnT = ft.parse("2014-04-13 15:00:00");
        rinfo = new ReservationInfo(branchId, new Date(), pickupT, 
                returnT, customerId, staffId, vehicleClassName, 10, 10, 10,
                null, null);
        riDao.add(rinfo);
        //rinfo = riDao.makeReservationInfo(rinfo);
        System.out.println(rinfo.getReservationInfoId());
        
        //rinfo = riDao.findByInstance(rinfo).get(0);
        riDao.delete(rinfo);
    }
    
}
