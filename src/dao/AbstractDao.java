/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.DbConn;
import dbconn.SqlBuilder;
import entity.User;
import entityParser.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jingchuan Chen
 */
public abstract class AbstractDao<T> {

    public boolean add(T entity) throws DaoException {
        if (entity == null) {
            return false;
        }
        if (PkIsAutoGen()) {
            return addAutoGenPk(entity);
        } else {
            return addWithPk(entity);
        }
    }

    private boolean addWithPk(T entity) throws DaoException {
        boolean ans = false;
        String tb_name = getTbName();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(EntityParser.wrapEntity(entity, getAP()))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
            ans = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
        return ans;
    }

    private boolean addAutoGenPk(T entity) throws DaoException {
        boolean ans = false;
        String tb_name = getTbName();
        int pk = getPkIndex()[0];
        AttributeParser ap[] = getAP();
        AttributeParser ap_no_pk[] = genAPWithoutPK(ap, pk);

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .columns(EntityParser.getColunmList(ap_no_pk))
                .values(EntityParser.wrapEntity(entity, ap_no_pk))
                .toString();

        System.out.println("SQL:" + sql);
        

        try {
            
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            ans = true;
            
            /*
            Connection conn = DbConn.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    */
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
        return ans;
    }

    public boolean delete(T entity) throws DaoException {
        if (entity == null) {
            return false;
        }

        boolean ans = false;
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int pk[] = getPkIndex();

        SqlBuilder qb = new SqlBuilder();
        qb.deleteFrom(tb_name);
        
        for (int i : pk) {
            qb.where(ap[i].getColName() + "=" + ap[i].wrapAttr(entity));
        }
        String sql = qb.toString();

        System.out.println(sql);
        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            int ret = stmt.executeUpdate(sql);
            if (ret == 0) {
                ans = false;
            } else {
                ans = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }
        return ans;
    }

    public boolean update(T entity) throws DaoException {
        if (entity == null) {
            return false;
        }
        boolean ans = false;
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int[] pk = getPkIndex();

        SqlBuilder qb = new SqlBuilder();
        qb.update(getTbName());
        qb.set(EntityParser.wrapEntityForSet(entity, ap));

        for (int i : pk) {
            qb.where(ap[i].getColName() + "=" + ap[i].wrapAttr(entity));
        }

        String sql = qb.toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
            ans = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");
        }
        return ans;
    }

    protected T findOne(String cond) throws DaoException {

        String tb_name = getTbName();
        AttributeParser ap[] = getAP();

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where(cond)
                .toString();

        T entity = getInstance();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                EntityParser.parseEntity(rs, entity, ap);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return entity;
    }

    protected ArrayList<T> find(String cond) throws DaoException {

        String tb_name = getTbName();
        AttributeParser ap[] = getAP();

        ArrayList<T> result = new ArrayList<>();

        SqlBuilder qb = new SqlBuilder();
        
        
        String sql = qb
                .select("*")
                .from(tb_name)
                .where(cond)
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                T entity = getInstance();
                EntityParser.parseEntity(rs, entity, ap);
                result.add(entity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return result;
    }
    
    public ArrayList<T> findByInstance(T value) throws DaoException {
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        
        SqlBuilder qb = new SqlBuilder();
        
        for (AttributeParser attr : ap) {
            String str = attr.wrapAttr(value);
            if (!(str.equalsIgnoreCase("null") || (str.equals("0") && attr.getClass().equals(IntParser.class)))) {
                qb.cond(attr.getColName() + "=" + str);
            }
        }
        
        String sql = qb.toString();
        return find(sql);
    }
    
    public ArrayList<T> all() throws DaoException {
        return find("true");
    }
    
    protected int count(String cond) throws DaoException {
        String tb_name = getTbName();
        int result;
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("COUNT(*)")
                .from(tb_name)
                .where(cond)
                .toString();
        
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                throw new DaoException(tb_name, "count() fail");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }
        
        return result;
    }
    
    protected int getLastAutoIncrementId() throws DaoException {
        String tb_name = getTbName();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("last_insert_id() as last_id")
                .from(tb_name)
                .toString();
        
        System.out.println("******SQL AI: " + sql);
        String last_id;
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                last_id = rs.getString("last_id");
            } else {
                throw new DaoException(tb_name, "getLastAutoIncrementId() fail");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "getLastAutoIncrementId()");
        }
        
        return Integer.parseInt(last_id);
        
    }

    private String getTbName() {
        String tb_name = (String) getField("tb_name");
        return tb_name;
    }

    private AttributeParser[] getAP() {
        AttributeParser ap[] = (AttributeParser[]) getField("ap");
        return ap;
    }

    private int[] getPkIndex() {
        int[] pk = (int[]) getField("pkIndex");
        return pk;
    }

    private boolean PkIsAutoGen() {
        boolean b = (boolean) getField("pkIsAutoGen");
        return b;
    }

    private Object getField(String name) {
        Object obj = null;
        try {
            obj = this.getClass().getDeclaredField(name).get(this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    private AttributeParser[] genAPWithoutPK(AttributeParser ap[], int pk) {
        AttributeParser ap_no_pk[] = new AttributeParser[ap.length - 1];
        int j = 0;
        for (int i = 0; i < ap.length; i++) {
            if (i != pk) {
                ap_no_pk[j] = ap[i];
                j++;
            }
        }

        return ap_no_pk;
    }

    protected abstract T getInstance();
}
