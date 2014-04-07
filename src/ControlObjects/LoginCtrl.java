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
    }
}
