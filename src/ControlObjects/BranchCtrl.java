/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.BranchDao;
import dao.DaoException;
import entity.Branch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elitward
 */
public class BranchCtrl {
    
    private static Branch defaultBranch = new Branch("Default Branch", "911", "nowhere", 130, 18, 1000);
    
    public static Branch getDefaultBranch(){
        BranchDao bDAO = new BranchDao();
        Branch branch = null;
        try {
            branch = bDAO.findByName("Main Branch");    //from SQL script
        } catch (DaoException ex) {
            Logger.getLogger(BranchCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
        }
        if(branch==null){
            branch = defaultBranch;
        }
        return branch;
    }
    
    public Branch getBranchById(int id){
        if( id==0 ) {
            return defaultBranch;
        } else {
            BranchDao bDAO = new BranchDao();
            Branch branch = null;
            try {
                branch = bDAO.findById(id);
            } catch (DaoException ex) {
                Logger.getLogger(BranchCtrl.class.getName()).log(Level.SEVERE, null, ex);
                ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            }
            return branch;
        }
    }
}
