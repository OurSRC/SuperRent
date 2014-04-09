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
    
    private Class<T> cls;
    
    public void add(T entity) throws DaoException {
        if (entity == null) {
            return;
        }
        if (PkIsAutoGen()) {
            addAutoGenPk(entity);
        } else {
            addWithPk(entity);
        }
        
    }

    public void addWithPk(T entity) throws DaoException {
        String tb_name = getTbName();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(EntityParser.wrapEntity(entity, getAP()))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
    }
    
    public void addAutoGenPk(T entity) throws DaoException {
        String tb_name = getTbName();
        int pk = getPkIndex();
        AttributeParser ap[] = getAP();
        AttributeParser ap_no_pk[] = genAPWithoutPK(ap, pk);
        
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .columns(EntityParser.getColunmList(ap_no_pk))
                .values(EntityParser.wrapEntity(entity, ap_no_pk))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
    }
    
    
    
    public void delete(T entity) throws DaoException {
        if (entity == null) {
            return;
        }
        
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int pk = getPkIndex();
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where(ap[pk].getColName() + "=" + ap[pk].wrapAttr(entity))
                .toString();
        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }
    }
    
    public void update(T entity) throws DaoException {
        
        
        if (entity == null) {
            return;
        }
        
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int pk = getPkIndex();
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .update(getTbName())
                .set(EntityParser.wrapEntityForSet(entity, ap))
                .where(ap[pk].getColName() + "=" + ap[pk].wrapAttr(entity))
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");

        }
    }
    
    public T findOne(String cond) throws DaoException {
        
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int pk = getPkIndex();
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where(cond)
                .toString();
        
        T entity = getInstance();

        try {
            Statement stmt = DbConn.getStmt();
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
    
    public ArrayList<T> find(String cond) throws DaoException {
        
        String tb_name = getTbName();
        AttributeParser ap[] = getAP();
        int pk = getPkIndex();
        
        ArrayList<T> result = new ArrayList<>();
        
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where(cond)
                .toString();

        try {
            Statement stmt = DbConn.getStmt();
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
    
    protected String getTbName() {
        String tb_name = (String)getField("tb_name");
        return tb_name;
    }
    
    protected AttributeParser[] getAP() {
        AttributeParser ap[] = (AttributeParser [])getField("ap");
        return ap;
    }
    
    protected int getPkIndex() {
        int pk = (int)getField("pkIndex");
        return pk;
    }
    
    protected boolean PkIsAutoGen() {
        boolean b = (boolean)getField("pkIsAutoGen");
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
    
    /*
    private T getInstance() {
        try {
            System.out.println("before");
            T entity = cls.newInstance();
            System.out.println("after");
            return entity;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    */

}
