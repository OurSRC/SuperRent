/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EntityParser;
import Dao.EntityParser.IntParser;
import Account.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>This is the abstract class for all data access objects which provides
 * basic CRUD functionalities. </p>
 * @param <T> Corresponding entity class of the Dao class
 */
public abstract class AbstractDao<T> {

    /**
     * This function add an entity into database.
     * @param entity Entity to be added to database
     * @return True if add succeed, false otherwise.
     * @throws DaoException
     */
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

        try (Statement stmt = DbConn.getStmt()) {
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

        try (PreparedStatement pstmt = DbConn.getPStmtWithAutoGenKey(sql)) {
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            int key = keys.getInt(1);
            ap[pk].setAttrByVal(entity, key);
            
            ans = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
        return ans;
    }

    /**
     * Delete the entity from database. Entity is identified by its primary key.
     * @param entity Entity object contains primary key to identify record to delete.
     * @return True if succeed, false otherwise.
     * @throws DaoException
     */
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
        try (Statement stmt = DbConn.getStmt()) {
            System.out.println("SQL:" + sql);
            int ret = stmt.executeUpdate(sql);
            ans = ret != 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }
        return ans;
    }

    /**
     * Update entity in database. Entity is identified by its key.
     * @param entity Entity to be updated. 
     * @return True on success, false otherwise.
     * @throws DaoException
     */
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

        try (Statement stmt = DbConn.getStmt()) {
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
            ans = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");
        }
        return ans;
    }

    /**
     * Search the first matching record based on condition
     * @param cond String that specifies condition in SQL where statement.
     * @return The first entity populated with matched record. null if not found.
     * @throws DaoException
     */
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

        try (Statement stmt = DbConn.getStmt()) {
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

    /**
     * Search all records that match the condition
     * @param cond String that specifies condition in SQL where statement.
     * @return ArrayList of all matching records.
     * @throws DaoException
     */
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

        try (Statement stmt = DbConn.getStmt()) {
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

    /**
     * Find all records has same value to populated value in an entity.
     * Null value and value of 0 for attributes will be ignored
     * @param value The entity object with fields to search.
     * @return ArrayList of all matching records.
     * @throws DaoException
     */
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

    /**
     * Find first record that has same value to populated value in an entity.
     * Null value and value of 0 for attributes will be ignored
     * @param value The entity object with fields to search.
     * @return ArrayList of all matching records.
     * @throws DaoException
     */
    public T findFirstInstance(T value) throws DaoException {
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
        return findOne(sql);
    }

    /**
     * Get all record in table.
     * @return ArrayList of all records.
     * @throws DaoException
     */
    public ArrayList<T> all() throws DaoException {
        return find("true");
    }

    /**
     * Get the count of records matching condition.
     * @param cond String that specifies condition in SQL where statement.
     * @return The count to matching record.
     * @throws DaoException
     */
    protected int count(String cond) throws DaoException {
        String tb_name = getTbName();
        int result;

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("COUNT(*)")
                .from(tb_name)
                .where(cond)
                .toString();

        try (Statement stmt = DbConn.getStmt()) {
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

    /**
     * Get the last auto increment id for a table.
     * @return Last auto increment id.
     * @throws DaoException
     */
    protected int getLastAutoIncrementId() throws DaoException {
        String tb_name = getTbName();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("last_insert_id() as last_id")
                .from(tb_name)
                .toString();

        System.out.println("******SQL AI: " + sql);
        String last_id;
        try (Statement stmt = DbConn.getStmt()) {
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

    /**
     * Get an instance of entity class instance. This must be implemented
     * by subclasses in order to make all functions in this class functional.
     * @return instance of entity class.
     */
    protected abstract T getInstance();
}
