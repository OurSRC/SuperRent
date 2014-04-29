package Account;

import SystemOperations.SecurityCtrl;
import SystemOperations.ErrorMsg;
import Dao.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * LogininCtrl is one of the components in Logical Control, get input from
 * Database and output data to support User Interface.
 * </p>
 */
public class LoginCtrl {

    public LoginCtrl() {
        super();
        // Initialize database connection ...
    }

    /**
     * Check if given username and password is valid.
     * @param username Username provided by user.
     * @param password Password provided by user.
     * @return The type of user. Return User.TYPE.ERROR if username 
     * and password is invalid
     */
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
                String savedPswd = userInfo.getPassword();
                String digestPswd = SecurityCtrl.digestPassword(password);
                if (password.compareTo(savedPswd) == 0) {
                    return userInfo.getType();
                } else if (digestPswd.compareTo(savedPswd) == 0) {
                    return userInfo.getType();
                } else {
                    ErrorMsg.setLastError(ErrorMsg.ERROR_WRONG_PASSWORD);
                    return User.TYPE.ERROR;
                }
            }
        } catch (DaoException ex) {
            Logger.getLogger(LoginCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_SQL_ERROR);
            return User.TYPE.ERROR;
        }
    }
}
