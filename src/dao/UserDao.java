package dao;

import entity.User;
import java.sql.*;
import dbconn.DbConn;
import dbconn.SqlBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.IllegalArgumentException;

public class UserDao implements GenericDao<User, String> {

    private static final String tb_name = "user";

    public void UserDao() {
    }

    @Override
    public User find(String pk) throws DaoException {
        User user = new User();
        String type;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(pk))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setType(User.TYPE.valueOf(rs.getString(3)));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }
        
        return user;
    }

    @Override
    public boolean update(User value) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        String sql = qb.update(tb_name)
                .set(
                        "Password=" + SqlBuilder.wrapStr(value.getPassword()),
                        "Type=" + SqlBuilder.wrapInt(value.getType().getValue())
                )
                .where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");
            
        }
        return true;
    }

    @Override
    public boolean add(User value) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(
                        SqlBuilder.wrapStr(value.getUsername()),
                        SqlBuilder.wrapStr(value.getPassword()),
                        SqlBuilder.wrapInt(value.getType().getValue()),
                        "NULL")
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
        
        return true;
    }

    @Override
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
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }

        return true;

    }

}
