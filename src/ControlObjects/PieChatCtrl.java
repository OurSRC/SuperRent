/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import dao.DaoException;
import dao.ReservationInfoDao;
import dao.VehicleClassDao;
import entity.ReservationInfo;
import entity.VehicleClass;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class PieChatCtrl {
    public static ArrayList<SimpleEntry<String, Integer>> getPendingReservationCount(int branchId, Date date) {
        ReservationInfoDao resDao = new ReservationInfoDao();
        VehicleClassDao vcDao = new VehicleClassDao();
        int count;
        
        ArrayList<VehicleClass> vcList;
        ArrayList<SimpleEntry<String, Integer>> result = new ArrayList<>();
        
        try {
            vcList = vcDao.all();
            
            for (VehicleClass vc : vcList) {
                count = resDao.countPendingByClass(branchId, date, vc.getClassName());
                if (count != 0) {
                    SimpleEntry<String, Integer> element = 
                            new SimpleEntry<>(vc.getClassName(), count);
                    result.add(element);
                }
            }
            
        } catch (DaoException ex) {
            Logger.getLogger(PieChatCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
        
    }
    
    public static ArrayList<SimpleEntry<String, Integer>> getPendingReturnCount(int branchId, Date date) {
        ReservationInfoDao resDao = new ReservationInfoDao();
        VehicleClassDao vcDao = new VehicleClassDao();
        int count;
        
        ArrayList<VehicleClass> vcList;
        ArrayList<SimpleEntry<String, Integer>> result = new ArrayList<>();
        
        try {
            vcList = vcDao.all();
            
            for (VehicleClass vc : vcList) {
                count = resDao.countNotReturnedByClass(branchId, date, vc.getClassName());
                if (count != 0) {
                    SimpleEntry<String, Integer> element = 
                            new SimpleEntry<>(vc.getClassName(), count);
                    result.add(element);
                }
            }
            
        } catch (DaoException ex) {
            Logger.getLogger(PieChatCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return result;
        
    }
}