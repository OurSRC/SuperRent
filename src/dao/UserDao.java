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

    public void UserDao() throws SQLException {
    }

    @Override
    public User find(String pk) throws SQLException{
        User user = new User();
        String type;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username="+SqlBuilder.wrapStr(pk))
                .toString();
        
        Statement stmt = DbConn.getStmt();
        ResultSet rs = stmt.executeQuery(sql);
        
        if (rs.next()) {
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setType(User.TYPE.valueOf(rs.getString(3)));
        } else {
            return null;
        }

        return user;
    }

    @Override
    public boolean update(User value) {
        SqlBuilder qb = new SqlBuilder();
        String sql = qb.update(tb_name)
                .set("Password=" + SqlBuilder.wrapStr(value.getPassword()))
                .set("Type=" + Integer.toString(parseType(value.getType())))
                .where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;

    }

    private int parseType(User.TYPE type) {
        if (type == User.TYPE.CUSTOMER) {
            return 1;
        } else if (type == User.TYPE.STAFF) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public boolean add(User value) {
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(
                        SqlBuilder.wrapStr(value.getUsername()), 
                        SqlBuilder.wrapStr(value.getPassword()), 
                        Integer.toString(parseType(value.getType())), "NULL")
                .toString();
        
        System.out.println(sql);

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(String pk) {
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
            return false;
        }

        return true;
                
    }

}
