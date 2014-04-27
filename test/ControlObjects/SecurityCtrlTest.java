/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

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
public class SecurityCtrlTest {
    
    public SecurityCtrlTest() {
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

    /**
     * Test of toMD5 method, of class SecurityCtrl.
     */
    @Test
    public void testToMD5() {
        System.out.println("toMD5");
        String text = "123456";
        String expResult = "e10adc3949ba59abbe56e057f20f883e";
        String result = SecurityCtrl.toMD5(text);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testCrypt(){
        String key="ezeon8547";  
        String plain="This is an important message";
        String enc=SecurityCtrl.encrypt(key, plain);
        System.out.println("Original text: "+plain);
        System.out.println("Encrypted text: "+enc);
        String plainAfter=SecurityCtrl.decrypt(key, enc);
        System.out.println("Original text after decryption: "+plainAfter);
        
        assertEquals(plain, plainAfter);
    }
}
