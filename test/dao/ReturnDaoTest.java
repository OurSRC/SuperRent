/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Dao.DaoException;
import Vehicle.VehicleClassDao;
import Operate.ReturnDao;
import Account.StaffDao;
import Operate.ReservationInfoDao;
import Operate.RentDao;
import Finance.PaymentDao;
import Vehicle.VehicleDao;
import Account.CustomerDao;
import Finance.CreditCardDao;
import SystemOperations.BranchDao;
import SystemOperations.Branch;
import Finance.CreditCard;
import Account.Customer;
import Operate.Rent;
import Operate.ReservationInfo;
import Operate.Return;
import Account.Staff;
import Vehicle.Vehicle;
import Vehicle.VehicleClass;
import Finance.Payment;
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
public class ReturnDaoTest {

    private static final String customerUName = "cname_test_return";
    private static final String staffUName = "sname_test_return";
    private static final String vehicleClassName = "vclass_test_return";
    private static final String resNo = "test_rent_res_no";
    private static final String cardNo = "1234567887654321";
    private static final String branchName = "bname test return";

    private static int reservationInfoNo;
    private static int vehicleId;
    private static int branchId;
    private static int customerId;
    private static int staffId;
    private static int contractNo;
    private static int paymentId;

    public ReturnDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws DaoException {
        Branch b = new Branch(branchName, "test rent phone", "test rent address", 18, 130, 1000);
        BranchDao bdao = new BranchDao();
        bdao.add(b);
        b = bdao.findByName(branchName);
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

        RentDao rdao = new RentDao();
        Rent rent = new Rent(reservationInfoNo, vehicleId, 100, 20, cardNo, staffId, new Date());
        rdao.add(rent);
        rent = rdao.findByInstance(rent).get(0);
        contractNo = rent.getContractNo();
        
        PaymentDao pdao = new PaymentDao();
        Payment payment = new Payment(customerId, "title", 1000, null, new Date());
        pdao.add(payment);
        payment = pdao.findByInstance(payment).get(0);
        paymentId = payment.getPaymentId();

    }

    @AfterClass
    public static void tearDownClass() throws DaoException {
        
        PaymentDao pdao = new PaymentDao();
        Payment payment = pdao.findById(paymentId);
        pdao.delete(payment);

        RentDao rdao = new RentDao();
        Rent rent = rdao.findByReservationInfo(reservationInfoNo);
        rdao.delete(rent);

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
        ReturnDao rtnDao = new ReturnDao();
        Return rtn = new Return(contractNo, new Date(), 100, 100, staffId, paymentId, 0);
        rtnDao.add(rtn);
    }

    @After
    public void tearDown() throws DaoException {
        
        ReturnDao rtnDao = new ReturnDao();
        Return rtn = rtnDao.findByContractNo(contractNo);
        rtnDao.delete(rtn);
        
    }

    /**
     * Test of findByContractNo method, of class ReturnDao.
     */
    @Test
    public void testFindByContractNo() throws Exception {
        ReturnDao instance = new ReturnDao();
        Return rtn = instance.findByContractNo(contractNo);
        assertNotNull(rtn);
        
    }

}
