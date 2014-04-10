/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Staff;
import entity.Branch;
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
public class StaffDaoTest {

    private static final String branchName = "Testbranch_Staffdao";
    private static int branchId;

    public StaffDaoTest() {
    }

    private static void assertStaffEquals(Staff a, Staff b) {
        assertEquals(a.getBranchId(), b.getBranchId());
        assertEquals(a.getEmail(), b.getEmail());
        assertEquals(a.getFistName(), b.getFistName());
        assertEquals(a.getLastName(), b.getLastName());
        assertEquals(a.getMiddleName(), b.getMiddleName());
        assertEquals(a.getPhone(), b.getPhone());
        assertEquals(a.getStaffType(), b.getStaffType());
        assertEquals(a.getStatus(), b.getStatus());
        assertEquals(a.getUsername(), b.getUsername());
        assertEquals(a.getPassword(), b.getPassword());
        assertEquals(a.getType(), b.getType());
    }

    @BeforeClass
    public static void setUpClass() throws DaoException {
        BranchDao bdao = new BranchDao();
        Branch b = new Branch(branchName, "21837419", "some addr");
        bdao.add(b);
        b = bdao.findByName(branchName);
        branchId = b.getBranchID();

    }

    @AfterClass
    public static void tearDownClass() throws DaoException {
        BranchDao bdao = new BranchDao();
        Branch b = bdao.findByName(branchName);
        bdao.delete(b);
    }

    @Before
    public void setUp() throws DaoException {
        StaffDao sdao = new StaffDao();
        Staff staffa = new Staff(branchId, "FNStaffa", null, "LNStaffa", "test@superrent.com", "123123123",
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, "StaffTestUser", "SomePass");
        Staff staffb = new Staff(branchId, "FNStaffb", null, "LNStaffb", "test@superrent.com", "123123123",
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, "StaffTestUser2", "SomePass");
        
        sdao.add(staffa);
        sdao.add(staffb);
    }

    @After
    public void tearDown() throws DaoException {
        StaffDao sdao = new StaffDao();
        Staff s;
        s = sdao.findByUsername("StaffTestUser");
        sdao.delete(s.getStaffId());
        s = sdao.findByUsername("StaffTestUser2");
        sdao.delete(s.getStaffId());
        
    }

    /**
     * Test of find method, of class StaffDao.
     */
    //@Test
    public void testFind() throws Exception {
        System.out.println("find");
        Integer pk = null;
        StaffDao instance = new StaffDao();
        Staff expResult = null;
        Staff result = instance.find(pk);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class StaffDao.
     */
    //@Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Staff value = null;
        StaffDao instance = new StaffDao();
        boolean expResult = false;
        boolean result = instance.update(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add and delete methods, of class StaffDao.
     */
    @Test
    public void testAddDelete() throws Exception {
        /*
        System.out.println("add");
        Staff value = new Staff(branchId, "FN", null, "LN", "test@superrent.com", "123123123",
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, "StaffTestUser", "SomePass");
        StaffDao instance = new StaffDao();

        instance.add(value);
        Staff s = instance.findByUsername("StaffTestUser");
        if (s == null) {
            fail("not added");
        }

        assertEquals(s.getBranchId(), value.getBranchId());
        assertEquals(s.getFistName(), value.getFistName());
        assertEquals(s.getUsername(), value.getUsername());
        assertEquals(s.getPassword(), value.getPassword());
        assertEquals(s.getType(), value.getType());

        instance.delete(s.getStaffId());

        s = instance.findByUsername("StaffTestUser");
        assertEquals(s, null);
        */
    }

    /**
     * Test of findOne method, of class StaffDao.
     */
    //@Test
    public void testFindOne() throws Exception {
        System.out.println("findOne");
        String sql = "";
        StaffDao instance = new StaffDao();
        Staff expResult = null;
        Staff result = instance.findOne(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUsername method, of class StaffDao.
     */
    //@Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        String username = "";
        StaffDao instance = new StaffDao();
        Staff expResult = null;
        Staff result = instance.findByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
