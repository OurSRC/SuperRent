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

import entityParser.*;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provides basic access method for customer table.</p>
 */
public class CustomerDao {

    private static final String tb_name = "customer";

    private static final AttributeParser ap[] = {
        new IntParser("CustomerId", "CustomerId"),
        new StringParser("PhoneNo", "Phone"),
        new StringParser("FirstName", "FirstName"),
        new StringParser("MiddleName", "MiddleName"),
        new StringParser("LastName", "LastName"),
        new StringParser("Email", "Email"),
        new StringParser("Address", "Address"),
        new StringParser("DriverLicenseNo", "DriverLicenseNumber"),
        new BooleanParser("IsClubMember", "IsClubMember"),
        new IntParser("MemberPoint", "Point"),
        new DateParser("MembershipExpireDate", "MembershipExpiry"),
        new StringParser("Username", "Username")
    };

    /**
     *
     * @param customerID The ID of customer
     * @return Customer object that has the id, or null if customer not exist
     * @throws DaoException
     */
    public Customer find(Integer customerID) throws DaoException {

        Customer customer;
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
        UserDao udao = new UserDao();
        User tmpu;

        Customer customer = null;
        try {
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
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

    private ArrayList<Customer> find(String cond) throws DaoException {
        UserDao udao = new UserDao();
        User tmpu;

        ArrayList<Customer> result = new ArrayList<>();

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
                //Customer entity = new Customer();
                Customer entity = parseCustomer(rs);    //modified by Elitward
                //EntityParser.parseEntity(rs, entity, ap);

                result.add(entity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "find()");
        }

        return result;
    }

    private Customer parseCustomer(ResultSet rs) throws SQLException, DaoException {
        Customer customer = new Customer();

        EntityParser.parseEntity(rs, customer, ap);

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
        Customer customer;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("Username=" + SqlBuilder.wrapStr(username))
                .toString();
        customer = findOne(sql);
        return customer;
    }

    public Customer findByCustomerId(int CustomerId) throws DaoException {
        Customer customer;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("CustomerId=" + SqlBuilder.wrapInt(CustomerId))
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
        Customer customer;
        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .select("*")
                .from(tb_name)
                .where("PhoneNo=" + SqlBuilder.wrapStr(phone))
                .toString();
        customer = findOne(sql);
        return customer;
    }

    public ArrayList<Customer> findByInstance(Customer value) throws DaoException {
        SqlBuilder qb = new SqlBuilder();

        for (AttributeParser attr : ap) {
            String str = attr.wrapAttr(value);
            //if (!(str.equalsIgnoreCase("null") || (str.equals("0") && attr.getClass().equals(IntParser.class)))) {
            if (!(str.equalsIgnoreCase("null") || (str.equals("0")))) {
                qb.cond(attr.getColName() + "=" + str);
            }
        }

        String sql = qb.toString();

        System.out.println("***FindByInstance:" + sql);
        return find(sql);
    }

    /**
     *
     * @param customer Customer object to update
     * @return
     * @throws DaoException
     */
    public boolean update(Customer customer) throws DaoException {
        Connection conn = null;

        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();
        boolean added = false;
        boolean updated = false;
        Customer customer_db;
        User userWithSameUname = null;

        qb.update(tb_name);
        qb.set(EntityParser.wrapEntityForSet(customer, ap));
        qb.where("CustomerID=" + SqlBuilder.wrapInt(customer.getCustomerId()));
        String sql = qb.toString();

        try {
            customer_db = find(customer.getCustomerId());
            if (customer_db == null) {
                throw new DaoException(tb_name, "update() existing record not found");
            }

            if (customer.getUsername() != null) {
                userWithSameUname = udao.find(customer.getUsername());
            }

            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            if (customer_db.getUsername() != null
                    && customer_db.getUsername().equals(customer.getUsername())) { // username is not changed
                udao.update(customer);
                //updated = true;
            } else {        // username changed
                if (userWithSameUname != null) {  // username exist
                    throw new DaoException(tb_name, "update() username exist");
                }

                if (customer.getUsername() != null) {
                    udao.add(customer);                     // add new customer name
                    added = true;
                }
            }
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);

            if (added) {
                udao.delete(customer_db.getUsername()); // delete old user name
            }

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            /*
             if (added) {
             udao.delete(customer.getUsername());
             udao.add(customer_db);
             }
             if (updated) {
             udao.update(customer_db);
             }
             */
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

    public boolean add(Customer customer) throws DaoException {
        Connection conn = null;

        boolean add_user = false;
        UserDao udao = new UserDao();
        SqlBuilder qb = new SqlBuilder();

        // add each atributes except CustomerId
        AttributeParser ap_remove_id[] = Arrays.copyOfRange(ap, 1, ap.length);
        qb.insert(tb_name);
        qb.columns(EntityParser.getColunmList(ap_remove_id));
        qb.values(EntityParser.wrapEntity(customer, ap_remove_id));

        String sql = qb.toString();
        try {
            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            if (customer.getUsername() != null) {
                udao.add(customer);
                //add_user = true;    // insert user success
            }
            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            /*
             if (add_user) {     // insert user success but insert customer failed
             udao.delete(customer.getUsername());
             }
             */
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "add()");
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

    public boolean delete(Integer customerID) throws DaoException {
        Connection conn = null;

        SqlBuilder qb = new SqlBuilder();
        String sql = qb
                .deleteFrom(tb_name)
                .where("CustomerID=" + SqlBuilder.wrapInt(customerID))
                .toString();
        try {
            conn = DbConn.getConn();
            conn.setAutoCommit(false);

            Customer cust = find(customerID);
            if (cust == null) {     // record not in table
                return true;
            }

            Statement stmt = DbConn.getStmt();
            System.out.println("SQL:" + sql);
            stmt.executeUpdate(sql);
            if (cust.getUsername() != null) {   // delete user if username exist
                UserDao udao = new UserDao();
                udao.delete(cust.getUsername());
            }

            conn.commit();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(tb_name, "delete()");
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

    public boolean delete(Customer customer) throws DaoException {
        this.delete(customer.getCustomerId());
        return true;
    }

}
