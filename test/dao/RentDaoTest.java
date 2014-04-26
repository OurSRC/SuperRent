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
import entity.Vehicle;
import entity.CreditCard;

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
    
    private static final String customerUName = "cname_test_rent";
    private static final String staffUName = "sname_test_rent";
    private static final String vehicleClassName = "test_rent_vclass";
    private static final String resNo = "test_rent_res_no";
    private static final String cardNo = "1234567887654321";
    
    private static int reservationInfoNo;
    private static int vehicleId;
    private static int branchId;
    private static int customerId;
    private static int staffId;

    public RentDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws DaoException {

        Branch b = new Branch("bname_test rent", "test rent phone", "test rent address", 18, 130, 1000);
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
                resNo, ReservationInfo.STATUS.PENDING);
        riDao.add(ri);
        ri = riDao.findByReservationNo(resNo);
        reservationInfoNo = ri.getReservationInfoId();
        
        VehicleDao vehDao = new VehicleDao();
        Vehicle vehicle = new Vehicle("PlateNo", new Date(), "BMW", customerId,
                branchId, Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, 
                null, vehicleClassName, 100);
        vehDao.add(vehicle);
        vehicle = vehDao.findByPlateNo("PlateNo");
        vehicleId = vehicle.getVehicleNo();
        
        CreditCardDao cardDao = new CreditCardDao();
        CreditCard card = new CreditCard(cardNo, new Date(), "some name");
        cardDao.add(card);
        
        
    }

    @AfterClass
    public static void tearDownClass() throws DaoException {
        CreditCardDao cardDao = new CreditCardDao();
        CreditCard card = new CreditCard(cardNo, null, null);
        cardDao.delete(card);
        
        
        VehicleDao vehDao = new VehicleDao();
        Vehicle vehicle = vehDao.findByPlateNo("PlateNo");
        vehDao.delete(vehicle);
        
        
        ReservationInfoDao riDao = new ReservationInfoDao();
        ReservationInfo ri = riDao.findByReservationNo(resNo);
        riDao.delete(ri);

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
    public void setUp() throws DaoException {
        RentDao rdao = new RentDao();
        Rent rent = new Rent(reservationInfoNo, vehicleId, 100, 20, cardNo, staffId, new Date());
        rdao.add(rent);
        
    }

    @After
    public void tearDown() throws DaoException {
        RentDao rdao = new RentDao();
        Rent rent = rdao.findByReservationInfo(reservationInfoNo);
        rdao.delete(rent);
    }

    /**
     * Test of findByContractNo method, of class RentDao.
     */
    @Test
    public void testFindByContractNo() throws Exception {
        RentDao rdao = new RentDao();
        Rent rent = rdao.findByReservationInfo(reservationInfoNo);
        assertNotNull(rent);
        assertEquals(rent.getCreditCardNo(), cardNo);

    }

}
