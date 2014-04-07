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
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class CustomerDao implements GenericDao<Customer, Integer> {

    private static String tb_name = "customer";

    @Override
    public Customer find(Integer customerID) throws SQLException {
        Customer csmt = new Customer();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("CustomerID=" + Integer.toString(customerID))
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
            csmt.setMembershipExpiry(rs.getDate(11));       //To Chris, need check this line: java.sql.Date getDate() java.sql.Date extend java.util.Date
            csmt.setUsername(rs.getString(12));
            /*
             java.sql.Date jd = new java.sql.Date(0);
             Date ud = new Date( 0 );
             csmt.setMembershipExpiry(jd);
             csmt.setMembershipExpiry(ud);
             */

            if (csmt.getUsername() != null) {
                UserDao udao = new UserDao();
                User u;
                u = udao.find(csmt.getUsername());
                if (u != null) {
                    csmt.setPassword(u.getPassword());
                    csmt.setType(u.getType());
                } else {
                    return null;    //database error
                }
            }
        } else {
            return null;
        }

        return csmt;
    }

    public Customer findByUsername(String username) throws SQLException {
        return null;
    }

    public Customer findByPhone(String phone) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Customer customer) {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        String sql = qb.update(tb_name)
                .set("PhoneNo=" + SqlBuilder.wrapStr(customer.getPhone()))
                .set("FirstName=" + SqlBuilder.wrapStr(customer.getFistName()))
                .set("MiddleName=" + SqlBuilder.wrapStr(customer.getMiddleName()))
                .set("LastName=" + SqlBuilder.wrapStr(customer.getLastName()))
                .set("Email=" + SqlBuilder.wrapStr(customer.geteMail()))
                .set("Address=" + SqlBuilder.wrapStr(customer.getAddress()))
                .set("DriverLicenseNo=" + SqlBuilder.wrapStr(customer.getDriverLicenseNumber()))
                .set("IsClubMember=" + SqlBuilder.wrapBool(customer.getIsClubMember()))
                .set("MemberPoint=" + SqlBuilder.wrapInt(customer.getPoint()))
                .set("MembershipExpireDate=" + SqlBuilder.wrapDate(customer.getMembershipExpiry()))
                .set("Username=" + SqlBuilder.wrapStr(customer.getUsername()))
                .where("CustomerID=" + SqlBuilder.wrapInt(customer.getCustomerId()))
                .toString();

        try {
            Customer cust = find(customer.getCustomerId());
            if (cust.getUsername().equals(customer.getUsername())) {
                udao.update(customer);
            } else {
                udao.delete(cust.getUsername());
                udao.add(customer);
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
    public boolean add(Customer customer) {
        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        Date date = customer.getMembershipExpiry();
        String sql = qb
                .insert(tb_name)
                .values(
                        SqlBuilder.wrapInt(customer.getCustomerId()),
                        SqlBuilder.wrapStr(customer.getPhone()),
                        SqlBuilder.wrapStr(customer.getFistName()),
                        SqlBuilder.wrapStr(customer.getMiddleName()),
                        SqlBuilder.wrapStr(customer.getLastName()),
                        SqlBuilder.wrapStr(customer.geteMail()),
                        SqlBuilder.wrapStr(customer.getAddress()),
                        SqlBuilder.wrapStr(customer.getDriverLicenseNumber()),
                        SqlBuilder.wrapBool(customer.getIsClubMember()), //SqlBuilder.wrapInt(customer.getIsClubMember() ? 1 : 0),
                        SqlBuilder.wrapInt(customer.getPoint()),
                        "NULL", //SqlBuilder.wrapDate(date),
                        SqlBuilder.wrapStr(customer.getUsername())
                )
                .toString();
        System.out.println(sql);
        try {
            if (customer.getUsername() != null) {
                boolean suc = udao.add(customer);
                if (suc) {
                    add_user = true;
                    Statement stmt = DbConn.getStmt();
                    stmt.executeUpdate(sql);
                }
            }
        } catch (SQLException ex) {
            if (add_user) {
                udao.delete(customer.getUsername());
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer customerID) {

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("CustomerID=" + SqlBuilder.wrapInt(customerID))
                .toString();
        try {
            Customer cust = find(customerID);
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
