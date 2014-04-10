/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.UserDao;

import dbconn.DbConn;
import dbconn.SqlBuilder;
import entity.Staff;
import entity.User;
import entityParser.AttributeParser;
import entityParser.BooleanParser;
import entityParser.DateParser;
import entityParser.EntityParser;
import entityParser.EnumParser;
import entityParser.IntParser;
import entityParser.StringParser;
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
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        boolean added = false;
        boolean updated = false;
        Staff staff_db = null;

        String sql = qb.update(tb_name)
                .set(EntityParser.wrapEntityForSet(value, ap))
                .where("StaffID=" + SqlBuilder.wrapInt(value.getStaffId()))
                .toString();

        try {
            staff_db = find(value.getStaffId());
            if (staff_db == null) {
                throw new DaoException(tb_name, "update() existing record not found");
            }
            if (staff_db.getUsername().equals(value.getUsername())) { // username not changed
                udao.update(value);
                updated = true;
            } else {                                                  // username changed
                if (udao.find(value.getUsername()) != null) {  // username exist
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
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            if (added) {
                udao.delete(value.getUsername());
            }
            if (updated) {
                udao.update(staff_db);
            }
            throw new DaoException(tb_name, "update()");
        }

        return true;
    }

    @Override
    public boolean add(Staff value) throws DaoException {
        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();

        AttributeParser ap_remove_id[] = Arrays.copyOfRange(ap, 1, ap.length);

        String sql = qb
                .insert(tb_name)
                .columns(EntityParser.getColunmList(ap_remove_id))
                .values(EntityParser.wrapEntity(value, ap_remove_id))
                .toString();

        try {
            udao.add(value);
            add_user = true;

            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            if (add_user) {
                udao.delete(value.getUsername());
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
    
    public boolean delete(Staff staff) throws DaoException {
        return delete(staff.getStaffId());
    }

    @Override
    public boolean delete(Integer pk) throws DaoException {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("StaffID=" + SqlBuilder.wrapInt(pk))
                .toString();
        try {
            Staff staff = find(pk);
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            udao.delete(staff.getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
                Staff entity = new Staff();
                EntityParser.parseEntity(rs, entity, ap);
                result.add(entity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return result;
    }

    private Staff findOne(String cond) throws DaoException {
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
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }
        return staff;

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
