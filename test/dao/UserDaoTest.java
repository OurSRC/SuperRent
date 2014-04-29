/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Dao.DaoException;
import Account.UserDao;
import Account.User;
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
public class UserDaoTest {

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private static final String test_pk = "test_username";
    private static final String test_pk2 = "test_username2";

    @Before
    public void setUp() throws DaoException {
        UserDao udao = new UserDao();
        User user = new User();
        user.setUsername(test_pk);
        user.setPassword("testpass");
        user.setType(User.TYPE.CUSTOMER);
        udao.add(user);
    }

    @After
    public void tearDown() throws DaoException {
        UserDao udao = new UserDao();
        udao.delete(test_pk);
    }

    /**
     * Test of find method, of class UserDao.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        UserDao instance = new UserDao();
        User result = instance.find(test_pk);
        assertEquals(test_pk, result.getUsername());
        //assertEquals("testpass", result.getPassword());
    }

    /**
     * Test of update method, of class UserDao.
     */
    @Test
    public void testUpdate() throws DaoException {
        System.out.println("update");
        UserDao instance = new UserDao();
        User user;

        user = instance.find(test_pk);
        user.setPassword("new pass");
        user.setType(User.TYPE.STAFF);
        instance.update(user);
        user = instance.find(test_pk);
        //assertEquals("new pass", user.getPassword());
        assertEquals(User.TYPE.STAFF, user.getType());
    }

    /**
     * Test of add method, of class UserDao.
     */
    @Test
    public void testAddDelete() throws DaoException {
        UserDao instance = new UserDao();
        User user = new User();
        boolean thrown;
        user.setUsername(test_pk);
        user.setPassword("testpassasd");
        user.setType(User.TYPE.CUSTOMER);
        
        /* test add existing user */
        thrown = false;
        try {
            instance.add(user);
        } catch (DaoException ex) {
            thrown = true;
        } 
        assertTrue(thrown);
        
        /* test add invalid record */
        user.setPassword(null);
        thrown = false;
        try {
            instance.add(user);
        } catch (DaoException ex) {
            thrown = true;
        } 
        assertTrue(thrown);
        
        
        user.setUsername(test_pk2);
        user.setPassword("pass");
        /* test add */
        instance.add(user);
        if (instance.find(test_pk2) == null) {
            fail("record is not added");
        }
        /* test delete */
        instance.delete(test_pk2);
        if (instance.find(test_pk2) != null) {
            fail("record is not deleted");
        }

    }

}
