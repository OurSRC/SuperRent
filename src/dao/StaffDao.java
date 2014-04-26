/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.DbConn;
import dbconn.SqlBuilder;
import entity.Staff;
import entity.User;
import entityParser.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jingchuan Chen
 */
public class StaffDao implements GenericDao<Staff, Integer> {

    private static final String tb_name = "staff";

    private static final AttributeParser ap[] = {
        new IntParser("StaffID", "StaffId"),
        new StringParser("FirstName", "FistName"),
        new StringParser("MiddleName", "MiddleName"),
        new StringParser("LastName", "LastName"),
        new StringParser("Email", "Email"),
        new StringParser("PhoneNo", "Phone"),
        new EnumParser("Type", "StaffType"),
        new EnumParser("Status", "Status"),
        new StringParser("Username", "Username"),
        new IntParser("BranchID", "BranchId")
    };

    @Override
    public boolean update(Staff value) throws DaoException {
        Connection conn = null;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        boolean added = false;
        Staff staff_db;
        User user_db;

        String sql = qb.update(tb_name)
                .set(EntityParser.wrapEntityForSet(value, ap))
                .where("StaffID=" + SqlBuilder.wrapInt(value.getStaffId()))
                .toString();

        try {
            staff_db = find(value.getStaffId());
            user_db = udao.find(value.getUsername());

            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            if (staff_db == null) {
                throw new DaoException(tb_name, "update() existing record not found");
            }
            if (staff_db.getUsername().equals(value.getUsername())) { // username not changed
                udao.update(value);
            } else {                                                  // username changed
                if (user_db != null) {  // username exist
                    throw new DaoException(tb_name, "update() username exist");
                }

                udao.add(value);
                added = true;
            }
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            if (added) {
                udao.delete(staff_db.getUsername());
            }

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "update()");
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    @Override
    public boolean add(Staff value) throws DaoException {
        Connection conn = null;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();

        AttributeParser ap_remove_id[] = Arrays.copyOfRange(ap, 1, ap.length);

        String sql = qb
                .insert(tb_name)
                .columns(EntityParser.getColunmList(ap_remove_id))
                .values(EntityParser.wrapEntity(value, ap_remove_id))
                .toString();

        try {
            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            udao.add(value);

            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);

            conn.commit();

        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    public boolean delete(Staff staff) throws DaoException {
        return delete(staff.getStaffId());
    }

    @Override
    public boolean delete(Integer pk) throws DaoException {
        Connection conn = null;

        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("StaffID=" + SqlBuilder.wrapInt(pk))
                .toString();
        try {
            Staff staff = find(pk);

            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            udao.delete(staff.getUsername());

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    private Staff parseStaff(ResultSet rs) throws SQLException, DaoException {
        Staff staff = new Staff();

        EntityParser.parseEntity(rs, staff, ap);

        if (staff.getUsername() != null) {
            UserDao udao = new UserDao();
            User user;
            user = udao.find(staff.getUsername());
            if (user != null) {
                staff.setPassword(user.getPassword());
                staff.setType(user.getType());
            } else { //database error
                //return null;
                staff.setUsername(null);
            }
        }

        return staff;

    }

    private ArrayList<Staff> find(String cond) throws DaoException {
        UserDao udao = new UserDao();
        User tmpu;
        ArrayList<Staff> result = new ArrayList<>();

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
                //Staff entity = new Staff();
                Staff entity = parseStaff(rs);      //modified by Elitward
                EntityParser.parseEntity(rs, entity, ap);

                tmpu = udao.find(entity.getUsername());
                entity.setPassword(tmpu.getPassword());
                entity.setType(tmpu.getType());

                result.add(entity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return result;
    }

    private Staff findOne(String cond) throws DaoException {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where(cond)
                .toString();

        Staff staff = null;
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                staff = parseStaff(rs);
                User u = udao.find(staff.getUsername());
                staff.setPassword(u.getPassword());
                staff.setType(u.getType());
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }
        return staff;

    }

    public ArrayList<Staff> findByInstance(Staff value) throws DaoException {
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

    public Staff findByUsername(String username) throws DaoException {
        Staff staff = findOne("Username=" + SqlBuilder.wrapStr(username));
        return staff;
    }

    @Override
    public Staff find(Integer pk) throws DaoException {
        Staff staff = findOne("StaffID=" + SqlBuilder.wrapInt(pk));
        return staff;
    }
}
