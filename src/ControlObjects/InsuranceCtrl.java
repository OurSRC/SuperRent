/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.InsuranceDao;
import entity.Insurance;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>This InsuranceCtrl class provides data access and control function of user entity for UI</p> 
 */
public class InsuranceCtrl {

    /**
     * This method gets the insurance type
     * @return array list of strings
     */
    public ArrayList<String> getInsuranceType() {
        InsuranceDao dao = new InsuranceDao();
        ArrayList<Insurance> list = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        try {
            list = dao.findAll();
        } catch (DaoException ex) {
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
            Logger.getLogger(InsuranceCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Insurance i : list) {
            ans.add(i.getName());
        }
        return ans;
    }
}
