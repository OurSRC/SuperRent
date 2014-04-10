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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jingchuan Chen
 */
public class StaffDao implements GenericDao<Staff, Integer> {

    private static final String tb_name = "staff";

    private static final AttributeParser ap[] = {
        //new IntParser("StaffID", "staffId"),
        //new StringParser("FirstName", "fistName"),
        //new StringParser("MiddleName", "middleName"),
        //new StringParser("LastName", "lastName"),
        //new StringParser("Email", "email"),
        //new StringParser("PhoneNo", "phone"),
        //new EnumParser("Type", "staffType"),
        //new EnumParser("Status", "status"),
        //new StringParser("Username", "username"),
        //new IntParser("BranchID", "branchId")
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
    public Staff find(Integer pk) throws DaoException {

        UserDao udao = new UserDao();
        User u;
        Staff staff = new Staff();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("StaffID=" + SqlBuilder.wrapInt(pk))
                .toString();
        staff = findOne(sql);
        return staff;
    }

    @Override
    public boolean update(Staff value) throws DaoException {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb.update(tb_name)
                .set("FirstName=" + SqlBuilder.wrapStr(value.getFistName()))
                .set("MiddleName=" + SqlBuilder.wrapStr(value.getMiddleName()))
                .set("LastName=" + SqlBuilder.wrapStr(value.getLastName()))
                .set("Email=" + SqlBuilder.wrapStr(value.getEmail()))
                .set("PhoneNo=" + SqlBuilder.wrapStr(value.getPhone()))
                .set("Type=" + SqlBuilder.wrapInt(value.getStaffType().getValue()))
                .set("Status=" + SqlBuilder.wrapInt(value.getStatus().getValue()))
                .set("Username=" + SqlBuilder.wrapStr(value.getUsername()))
                .set("BranchID=" + SqlBuilder.wrapInt(value.getBranchId()))
                .toString();

        try {
            Staff staff = find(value.getStaffId());
            if (staff.getUsername().equals(value.getUsername())) {
                udao.update(value);
            } else {
                udao.delete(staff.getUsername());
                udao.add(value);
            }
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    public boolean add(Staff value) throws DaoException {
        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(
                        SqlBuilder.wrapInt(value.getStaffId()),
                        SqlBuilder.wrapStr(value.getFistName()),
                        SqlBuilder.wrapStr(value.getMiddleName()),
                        SqlBuilder.wrapStr(value.getLastName()),
                        SqlBuilder.wrapStr(value.getEmail()),
                        SqlBuilder.wrapStr(value.getPhone()),
                        SqlBuilder.wrapInt(value.getStaffType().getValue()),
                        SqlBuilder.wrapInt(value.getStatus().getValue()),
                        SqlBuilder.wrapStr(value.getUsername()),
                        SqlBuilder.wrapInt(value.getBranchId())
                )
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

    private Staff parseCustomer(ResultSet rs) throws SQLException, DaoException {
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

    public Staff findOne(String sql) throws DaoException {
        Staff staff = null;
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                staff = parseCustomer(rs);
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
        Staff staff;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(username))
                .toString();
        staff = findOne(sql);
        return staff;
    }
}
