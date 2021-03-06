package Account;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EntityParser;
import Dao.EntityParser.EnumParser;
import SystemOperations.SecurityCtrl;
import Dao.DaoException;
import Dao.DbConn;
import Dao.SqlBuilder;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provides basic access methods for staff entity</p>
 */
public class UserDao {

    public static final String tb_name = "user";
    
    public static final AttributeParser ap[] = {
        new StringParser("Username", "Username"),
        new StringParser("Password", "Password"),
        new EnumParser("Type", "Type")
    };

    public void UserDao() {
    }

    /**
     * Find {@link User} by {@code username}.
     * @param username Username to search with.
     * @return Matching {@link User} object.
     * @throws DaoException
     */
    public User find(String username) throws DaoException {
        User user = new User();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(username))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                EntityParser.parseEntity(rs, user, ap);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return user;
    }

    /**
     * Update user record in database with {@code value} of {@link User}.
     * @param value Object of {@link User} to update.
     * @return True on success, false otherwise.
     * @throws DaoException
     */
    public boolean update(User value) throws DaoException {
        String pswd = value.getPassword();  //encrypt password in database
        if(pswd!=null && pswd.length()>0){
            value.setPassword(SecurityCtrl.digestPassword(pswd));
        }else{
            User readBack = find(value.getUsername());
            value.setPassword(readBack.getPassword());  //do not encrypt again, for the password read from database has already been encrypted.
        }
        
        SqlBuilder qb = new SqlBuilder();
        qb.update(tb_name);
        qb.set(EntityParser.wrapEntityForSet(value, ap));
        String sql = qb.where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");

        }
        return true;
    }

    /**
     * Add a {@link User} object into database.
     * @param value The {@link User} to add.
     * @return True on success, false otherwise.
     * @throws DaoException
     */
    public boolean add(User value) throws DaoException {
        String pswd = value.getPassword();  //encrypt password in database
        if(pswd!=null && pswd.length()>0)
            value.setPassword(SecurityCtrl.digestPassword(pswd));
        
        SqlBuilder qb = new SqlBuilder();
        qb.insert(tb_name);
        qb.values(EntityParser.wrapEntity(value, ap));    
        String sql = qb.toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }

        return true;
    }
    
    /**
     * Delete a user record from database.
     * @param pk The user with primary key to delete.
     * @return True on success, false otherwise.
     * @throws DaoException
     */
    public boolean delete(String pk) throws DaoException {
        if (pk == null) {
            return true;
        }
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(pk))
                .toString();
        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }

        return true;

    }

}
