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

    private static final MetaInfo ma[] = {
        new MetaInfo("Username", "Username", "String", String.class, MetaInfo.WrapType.STRING),
        new MetaInfo("Password", "Password", "String", String.class, MetaInfo.WrapType.STRING),
        new MetaInfo("Type", "Type", "String", String.class, MetaInfo.WrapType.ENUM)
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
                EntityParser.parseEntity(rs, user, ma);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        } catch (ParserException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException("parser exception");
        }

        return user;
    }

    @Override
    public boolean update(User value) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        String sql;
        /*
         String sql = qb.update(tb_name)
         .set(
         "Password=" + SqlBuilder.wrapStr(value.getPassword()),
         "Type=" + SqlBuilder.wrapInt(value.getType().getValue())
         )
         .where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
         .toString();
         */

        String set_str;
        qb.update(tb_name);

        try {
            for (int i = 1; i < ma.length; i++) {
                set_str = ma[i].getAttr_name() + "=" + EntityParser.wrapAttr(value, ma[i]);
                qb.set(set_str);
            }
        } catch (ParserException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException("parser exception");
        }
        
        sql = qb.where("Username=" + SqlBuilder.wrapStr(value.getUsername()))
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
        String sql;

        String val_str;
        qb.insert(tb_name);
        
        try {
        for (MetaInfo m : ma) {
            val_str = EntityParser.wrapAttr(value, m);
            qb.values(val_str);
        }
        } catch (ParserException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException("parser exception");
        }
        
        qb.values("NULL");
        sql = qb.toString();

        /*
         qb = new SqlBuilder();
         sql = qb
         .insert(tb_name)
         .values(
         SqlBuilder.wrapStr(value.getUsername()),
         SqlBuilder.wrapStr(value.getPassword()),
         SqlBuilder.wrapInt(value.getType().getValue()),
         "NULL")
         .toString();
         */
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
