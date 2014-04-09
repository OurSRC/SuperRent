package dao;

import entityParser.*;
import entity.User;
import java.sql.*;
import dbconn.DbConn;
import dbconn.SqlBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements GenericDao<User, String> {

    private static final String tb_name = "user";
    
    private static final AttributeParser ap[] = {
        new StringParser("Username", "Username"),
        new StringParser("Password", "Password"),
        new EnumParser("Type", "Type")
    };

    public void UserDao() {
    }

    @Override
    public User find(String pk) throws DaoException {
        User user = new User();
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

    @Override
    public boolean update(User value) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        qb.update(tb_name);
        qb.set(EntityParser.wrapEntityForSet(value, ap));
        String sql = qb.where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
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
        qb.insert(tb_name);
        qb.values(EntityParser.wrapEntity(value, ap));    
        qb.values("NULL");  // Last login in database, not handled in entity obj
        String sql = qb.toString();

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
