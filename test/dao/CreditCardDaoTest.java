/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class CreditCardDaoTest {
    
    public CreditCardDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws DaoException {
        CreditCardDao cdao = new CreditCardDao();
        CreditCard card = new CreditCard("1234123412341234", new Date(), "card holder");
        cdao.add(card);
    }
    
    @After
    public void tearDown() throws DaoException {
        CreditCardDao cdao = new CreditCardDao();
        CreditCard card = new CreditCard("1234123412341234", new Date(), "card holder");
        cdao.delete(card);
    }
    
    @Test
    public void testFind() throws DaoException {
        CreditCardDao cdao = new CreditCardDao();
        CreditCard card = cdao.findByCardNumber("1234123412341234");
        assertNotNull(card);
        assertEquals(card.getCardHolderName(), "card holder");
    }
    


}
