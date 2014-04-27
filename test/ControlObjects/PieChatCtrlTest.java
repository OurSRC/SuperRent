/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;


import ControlObjects.PieChatCtrl;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Jingchuan Chen
 */
public class PieChatCtrlTest {
    
    public PieChatCtrlTest() {
    }

    /**
     * Test of getReservationChat method, of class PieChatCtrl.
     */
    @Test
    public void testGetReservationChat() {
        ArrayList<SimpleEntry<String, Integer>> result = null;
        
        result = PieChatCtrl.getPendingReservationCount(0, null);
        
        for (SimpleEntry<String, Integer> element : result) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
        }
        
    }
    
    @Test
    public void testGetPendingReturnCount() {
        ArrayList<SimpleEntry<String, Integer>> result = null;
        
        result = PieChatCtrl.getPendingReturnCount(0, null);
        
        for (SimpleEntry<String, Integer> element : result) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
        }
    }
    
}
