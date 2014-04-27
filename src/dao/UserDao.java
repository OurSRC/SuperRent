package dao;

import ControlObjects.SecurityCtrl;
import dbconn.DbConn;
import dbconn.SqlBuilder;
import entity.User;
import entityParser.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provides basic access methods for staff entity</p>
 */
public class UserDao {

    private static final String tb_name = "user";
    
    private static final AttributeParser ap[] = {
        new StringParser("Username", "Username"),
        new StringParser("Password", "Password"),
        new EnumParser("Type", "Type")
    };

    public void UserDao() {
    }

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

    public boolean update(User value) throws DaoException {
        String pswd = value.getPassword();  //encrypt password in database
        if(pswd!=null && pswd.length()>0)
            value.setPassword(SecurityCtrl.digestPassword(pswd));
        
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
