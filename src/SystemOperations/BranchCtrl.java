/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemOperations;

import SystemOperations.ErrorMsg;
import Dao.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<p>
 * This is one of the components in Logical Control, get input from Database and output data to support User Interface, which operates Branch entity object
 * </p>
 */
public class BranchCtrl {
    
    private static final Branch defaultBranch = new Branch("Default Branch", "911", "nowhere", 130, 18, 1000);
    
    /**
     *Get the default branch, if there is no Main Branch found in database, a dummy default branch will be returned.
     * @return The branch information.
     */
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
    
    /**
     *Get the complete Branch information as an object by specify a branch ID.
     * @param id The specified branch ID
     * @return The complete Branch information as an object.
     */
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
