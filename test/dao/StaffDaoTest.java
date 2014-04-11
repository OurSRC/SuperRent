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
        Branch b = new Branch(branchName, "21837419", "some addr testbranch");
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
        StaffDao sdao = new StaffDao();
        Staff staffa = new Staff(branchId, "FNStaffa", null, "LNStaffa", "test@superrent.com", "123123123",
                Staff.STATUS.ACTIVE, Staff.TYPE.CLERK, "StaffTestUser", "SomePass");
        Staff result = sdao.findByUsername("StaffTestUser");
        
        assertStaffEquals(staffa, result);
        
        result = sdao.find(result.getStaffId());
        
        assertStaffEquals(staffa, result);
    }

    /**
     * Test of update method, of class StaffDao.
     */
    @Test
    public void testUpdate() throws Exception {
        StaffDao sdao = new StaffDao();
        Staff result1 = sdao.findByUsername("StaffTestUser");
        result1.setFistName("another Firse");
        result1.setUsername("some other user name");
        sdao.update(result1);
        Staff result2 = sdao.find(result1.getStaffId());
        assertStaffEquals(result1, result2);
        
        result2 = sdao.findByUsername("StaffTestUser");
        assertNull(result2);
        
        result1.setUsername("StaffTestUser");
        sdao.update(result1);
        
    }

}
