/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.DbConn;
import dbconn.SqlBuilder;
import dao.UserDao;
import entity.Customer;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class CustomerDao implements GenericDao<Customer, Integer> {

    private static String tb_name = "customer";

    @Override
    public Customer find(Integer pk) throws SQLException {
        Customer csmt = new Customer();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("CustomerID=" + Integer.toString(pk))
                .toString();

        Statement stmt = DbConn.getStmt();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            csmt.setCustomerId(rs.getInt(1));
            csmt.setPhone(rs.getString(2));
            csmt.setFistName(rs.getString(3));
            csmt.setMiddleName(rs.getString(4));
            csmt.setLastName(rs.getString(5));
            csmt.seteMail(rs.getString(6));
            csmt.setAddress(rs.getString(7));
            csmt.setDriverLicenseNumber(rs.getString(8));
            csmt.setIsClubMember(rs.getBoolean(9));
            csmt.setPoint(rs.getInt(10));
            csmt.setMembershipExpiry(rs.getDate(11));
            csmt.setUsername(rs.getString(12));

            if (csmt.getUsername() != null) {
                UserDao udao = new UserDao();
                User u;
                u = udao.find(csmt.getUsername());
                csmt.setPassword(u.getPassword());
                csmt.setType(u.getType());
            }
        } else {
            return null;
        }

        return csmt;
    }

    @Override
    public boolean update(Customer value) {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb.update(tb_name)
                .set("PhoneNo=" + SqlBuilder.wrapStr(value.getPhone()))
                .set("FirstName=" + SqlBuilder.wrapStr(value.getFistName()))
                .set("MiddleName=" + SqlBuilder.wrapStr(value.getMiddleName()))
                .set("LastName=" + SqlBuilder.wrapStr(value.getLastName()))
                .set("Email=" + SqlBuilder.wrapStr(value.geteMail()))
                .set("Address=" + SqlBuilder.wrapStr(value.getAddress()))
                .set("DriverLicenseNo=" + SqlBuilder.wrapStr(value.getDriverLicenseNumber()))
                .set("IsClubMember=" + SqlBuilder.wrapBool(value.getIsClubMember()))
                .set("MemberPoint=" + SqlBuilder.wrapInt(value.getPoint()))
                .set("MembershipExpireDate=" + SqlBuilder.wrapDate(value.getMembershipExpiry()))
                .set("Username=" + SqlBuilder.wrapStr(value.getUsername()))
                .where("CustomerID=" + SqlBuilder.wrapInt(value.getCustomerId()))
                .toString();

        try {
            Customer cust = find(value.getCustomerId());
            if (cust.getUsername().equals(value.getUsername())) {
                udao.update(value);
            } else {
                udao.delete(cust.getUsername());
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
    public boolean add(Customer value) {
        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .insert(tb_name)
                .values(
                        SqlBuilder.wrapInt(value.getCustomerId()),
                        SqlBuilder.wrapStr(value.getPhone()),
                        SqlBuilder.wrapStr(value.getFistName()),
                        SqlBuilder.wrapStr(value.getMiddleName()),
                        SqlBuilder.wrapStr(value.getLastName()),
                        SqlBuilder.wrapStr(value.geteMail()),
                        SqlBuilder.wrapStr(value.getAddress()),
                        SqlBuilder.wrapStr(value.getDriverLicenseNumber()),
                        SqlBuilder.wrapBool(value.getIsClubMember()),
                        SqlBuilder.wrapInt(value.getIsClubMember() ? 1 : 0),
                        SqlBuilder.wrapInt(value.getPoint()),
                        SqlBuilder.wrapDate(value.getMembershipExpiry()),
                        SqlBuilder.wrapStr(value.getUsername())
                )
                .toString();

        try {
            
            if (value.getUsername() != null) {
                
                udao.add(value);
                add_user = true;
            }
            
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
    public boolean delete(Integer pk) {

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("CustomerID=" + SqlBuilder.wrapInt(pk))
                .toString();
        try {
            Customer cust = find(pk);
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            if (cust.getUsername() != null) {
                UserDao udao = new UserDao();
                udao.delete(cust.getUsername());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

}
