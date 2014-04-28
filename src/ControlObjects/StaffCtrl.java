package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.DaoException;
import dao.StaffDao;
import entity.Staff;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>This StaffCtrl class provides data access and control function of user entity for UI</p> 
 * 
 */
public class StaffCtrl {

    /**
     * This method find a {@link Staff} with given {@code staff}
     * @param staff 
     * @return Staff information
     */
    public Staff createStaff(Staff staff) {
        try {
            StaffDao staffDAO = new StaffDao();
            boolean suc = staffDAO.add(staff);
            if (suc) {
                Staff withIdInfo = staffDAO.findByUsername(staff.getUsername());
                return withIdInfo;
            }else{
                ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
                return null;
            }
        } catch (DaoException ex) {
            Logger.getLogger(StaffCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return null;
    }

    /**
     * This method is the boolean value whether update staff information or not with given {@code staff}
     * @param staff
     * @return true or false
     */
    public boolean updateStaff(Staff staff) {
        StaffDao staffDAO = new StaffDao();
        boolean ans = false;
        try {
            ans = staffDAO.update(staff);
        } catch (DaoException ex) {
            Logger.getLogger(StaffCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return ans;
    }

    /**
     * This method is the boolean value whether delete staff information or not with given {@code staffId}
     * @param staffId
     * @return true or false
     */
    public boolean deleteStaff(int staffId) {
        StaffDao staffDAO = new StaffDao();
        boolean ans = false;
        try {
            ans = staffDAO.delete(staffId);
        } catch (DaoException ex) {
            Logger.getLogger(StaffCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return ans;
    }

    /**
     * This method gets the staff ID with given {@code staffId}
     * @param staffId
     * @return Staff
     */
    public Staff getStaffById(int staffId) {
        ErrorMsg.setLastError(ErrorMsg.ERROR_NOT_SUPPORT_YET);
        return null;
    }

    /**
     * This method find a {@link Staff} with given {@code username}
     * @param username
     * @return Staff
     */
    public Staff getStaffByUsername(String username) {
        StaffDao staffDAO = new StaffDao();
        Staff staff = null;
        try {
            staff = staffDAO.findByUsername(username);
        } catch (DaoException ex) {
            Logger.getLogger(StaffCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return  staff;
    }

    /**
     * This method searches a {@link Staff} with given {@code staff}
     * @param staff
     * @return array list of staff
     */
    public ArrayList<Staff> searchStaff(Staff staff) {
        StaffDao staffDAO = new StaffDao();
        ArrayList<Staff> list = null;
        try {
            list = staffDAO.findByInstance(staff);
        } catch (DaoException ex) {
            Logger.getLogger(StaffCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return list;
    }
}
