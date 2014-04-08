/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.DbConn;
import dbconn.SqlBuilder;
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
 * @author Jingchuan Chen
 */
public class CustomerDao implements GenericDao<Customer, Integer> {

    private static final String tb_name = "customer";

    /**
     *
     * @param customerID The ID of customer
     * @return Customer object that has the id, or null if customer not exist
     * @throws DaoException
     */
    @Override
    public Customer find(Integer customerID) throws DaoException {

        Customer customer = null;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("CustomerID=" + Integer.toString(customerID))
                .toString();
        customer = findOne(sql);
        return customer;
    }

    /**
     * This function takes sql query and return a single object.
     *
     * @param sql The sql to execute.
     * @return Customer object, or null if not found.
     * @throws DaoException
     */
    public Customer findOne(String sql) throws DaoException {

        Customer customer = null;
        try {
            Statement stmt = DbConn.getStmt();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                customer = parseCustomer(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }
        return customer;
    }

    private Customer parseCustomer(ResultSet rs) throws SQLException, DaoException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt(1));
        customer.setPhone(rs.getString(2));
        customer.setFistName(rs.getString(3));
        customer.setMiddleName(rs.getString(4));
        customer.setLastName(rs.getString(5));
        customer.seteMail(rs.getString(6));
        customer.setAddress(rs.getString(7));
        customer.setDriverLicenseNumber(rs.getString(8));
        customer.setIsClubMember(rs.getBoolean(9));
        customer.setPoint(rs.getInt(10));
        customer.setMembershipExpiry(rs.getDate(11));       //To Chris, need check this line: java.sql.Date getDate() java.sql.Date extend java.util.Date
        customer.setUsername(rs.getString(12));
        /*
         java.sql.Date jd = new java.sql.Date(0);
         Date ud = new Date( 0 );
         csmt.setMembershipExpiry(jd);
         csmt.setMembershipExpiry(ud);
         */

        if (customer.getUsername() != null) {
            UserDao udao = new UserDao();
            User user;
            user = udao.find(customer.getUsername());
            if (user != null) {
                customer.setPassword(user.getPassword());
                customer.setType(user.getType());
            } else { //database error
                //return null;
                customer.setUsername(null);
            }
        }

        return customer;

    }

    /**
     * Search customer object by username
     *
     * @param username The username of customer to find
     * @return Customer object, or null;
     * @throws dao.DaoException
     */
    public Customer findByUsername(String username) throws DaoException {
        Customer customer = null;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(username))
                .toString();
        customer = findOne(sql);
        return customer;
    }

    /**
     * Search customer object by phone number
     *
     * @param phone The phone number to search
     * @return Customer object, or null;
     * @throws DaoException
     */
    public Customer findByPhone(String phone) throws DaoException {
        Customer customer = null;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("PhoneNo=" + SqlBuilder.wrapStr(phone))
                .toString();
        customer = findOne(sql);
        return customer;
    }

    /**
     *
     * @param customer Customer object to update
     * @return
     * @throws DaoException
     */
    @Override
    public boolean update(Customer customer) throws DaoException {
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        boolean added = false;
        boolean updated = false;
        Customer customer_db = null;
        String sql = qb.update(tb_name)
                .set(
                        "PhoneNo=" + SqlBuilder.wrapStr(customer.getPhone()),
                        "FirstName=" + SqlBuilder.wrapStr(customer.getFistName()),
                        "MiddleName=" + SqlBuilder.wrapStr(customer.getMiddleName()),
                        "LastName=" + SqlBuilder.wrapStr(customer.getLastName()),
                        "Email=" + SqlBuilder.wrapStr(customer.geteMail()),
                        "Address=" + SqlBuilder.wrapStr(customer.getAddress()),
                        "DriverLicenseNo=" + SqlBuilder.wrapStr(customer.getDriverLicenseNumber()),
                        "IsClubMember=" + SqlBuilder.wrapBool(customer.getIsClubMember()),
                        "MemberPoint=" + SqlBuilder.wrapInt(customer.getPoint()),
                        "MembershipExpireDate=" + SqlBuilder.wrapDate(customer.getMembershipExpiry()),
                        "Username=" + SqlBuilder.wrapStr(customer.getUsername())
                )
                .where("CustomerID=" + SqlBuilder.wrapInt(customer.getCustomerId()))
                .toString();

        System.out.println(sql);

        try {
            customer_db = find(customer.getCustomerId());
            if (customer_db == null) {
                throw new DaoException(tb_name, "update() existing record not found");
            }
            if (customer_db.getUsername().equals(customer.getUsername())) { // username is not changed
                udao.update(customer);
                updated = true;
            } else {        // username changed
                if (udao.find(customer.getUsername()) != null) {  // username exist
                    throw new DaoException(tb_name, "update() username exist");
                }

                udao.delete(customer_db.getUsername()); // delete old user name
                if (customer.getUsername() != null) {
                    udao.add(customer);                     // add new customer name
                    added = true;
                }
            }
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            if (added) {
                udao.delete(customer.getUsername());
            }
            if (updated) {
                udao.update(customer_db);
            }
            throw new DaoException(tb_name, "update()");
        }

        return true;
    }

    @Override
    public boolean add(Customer customer) throws DaoException {
        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        Date date = customer.getMembershipExpiry();
        String sql = qb
                .insert(tb_name)
                .columns("PhoneNO", "FirstName", "MiddleName", "LastName", "Email", "Address", "DriverLicenseNo",
                        "IsClubMember", "MemberPoint", "MembershipExpireDate", "Username")
                .values(
                        //SqlBuilder.wrapInt(customer.getCustomerId()),
                        SqlBuilder.wrapStr(customer.getPhone()),
                        SqlBuilder.wrapStr(customer.getFistName()),
                        SqlBuilder.wrapStr(customer.getMiddleName()),
                        SqlBuilder.wrapStr(customer.getLastName()),
                        SqlBuilder.wrapStr(customer.geteMail()),
                        SqlBuilder.wrapStr(customer.getAddress()),
                        SqlBuilder.wrapStr(customer.getDriverLicenseNumber()),
                        SqlBuilder.wrapBool(customer.getIsClubMember()),
                        SqlBuilder.wrapInt(customer.getPoint()),
                        "NULL", //SqlBuilder.wrapDate(date),
                        SqlBuilder.wrapStr(customer.getUsername())
                )
                .toString();
        System.out.println(sql);

        try {
            if (customer.getUsername() != null) {
                udao.add(customer);
                add_user = true;    // insert user success
            }
            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            if (add_user) {     // insert user success but insert customer failed
                udao.delete(customer.getUsername());
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
        }
        return true;
    }

    @Override
    public boolean delete(Integer customerID) throws DaoException {

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("CustomerID=" + SqlBuilder.wrapInt(customerID))
                .toString();
        try {
            Customer cust = find(customerID);
            if (cust == null) {     // record not in table
                return true;
            }

            Statement stmt = DbConn.getStmt();
            stmt.executeUpdate(sql);
            if (cust.getUsername() != null) {   // delete user if username exist
                UserDao udao = new UserDao();
                udao.delete(cust.getUsername());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
        }

        return true;
    }
    
    public boolean delete(Customer customer) throws DaoException {
        this.delete(customer.getCustomerId());
        return true;
    }

}
