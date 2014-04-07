package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.CustomerDao;
import dao.UserDao;
import entity.Customer;
import entity.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCtrl {

    public LoginCtrl() {
        super();
        // Initialize database connection ...
    }

    public User.TYPE loginCheck(String username, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer csmt=null;
        try {
            csmt = new Customer("clerk", "clerk", "1234567", "No.111, ABC road", "fistName", "middleName", "lastname", "clerk@nowhere.com", "NA", false, 0, sdf.parse("2010-12-31"));
            csmt.setCustomerId(50);
        } catch (ParseException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomerDao cDAO = new CustomerDao();
        boolean suc = cDAO.add(csmt);
        return User.TYPE.ERROR;
        /*
        //search user table by username & password
        //if there is a match, get user type
        //then, use username to check target type table (staff or customer)
        //get all detail info
        UserDao userDAO = new UserDao();

        try {
            User userInfo = userDAO.find(username);
            if (userInfo == null) {
                ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
                return User.TYPE.ERROR;
            } else {
                if (password.compareTo(userInfo.getPassword()) == 0) {
                    return userInfo.getType();
                } else {
                    ErrorMsg.setLastError(ErrorMsg.ERROR_WRONG_PASSWORD);
                    return User.TYPE.ERROR;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return User.TYPE.ERROR;
        }
                */
    }

}
