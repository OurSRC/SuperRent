/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Staff;
import entity.User;
import dbconn.DbConn;
import dbconn.SqlBuilder;
import dao.UserDao;

import java.sql.SQLException;
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
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                staff.setStaffId(rs.getInt(1));
                staff.setFistName(rs.getString(2));
                staff.setMiddleName(rs.getString(3));
                staff.setLastName(rs.getString(4));
                staff.seteMail(rs.getString(5));
                staff.setPhone(rs.getString(6));
                staff.setStaffType(Staff.TYPE.valueOf(rs.getString(7)));
                staff.setStatus(Staff.STATUS.valueOf(rs.getString(8)));
                staff.setUsername(rs.getString(9));
                staff.setBranchId(rs.getInt(10));

                u = udao.find(staff.getUsername());
                staff.setPassword(u.getPassword());
                staff.setType(u.getType());

            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                .set("Email=" + SqlBuilder.wrapStr(value.geteMail()))
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
                        SqlBuilder.wrapStr(value.geteMail()),
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

}
