package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.CustomerDao;
import dao.DaoException;
import entity.Customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<p>
 * CustomerCtrl is one of the components in Logical Control, get input from Database and output data to support User Interface, which operates Customer entity object
 * </p>
 */
public class CustomerCtrl {
    static final int MEMBERSHIP_INIT_POINT = 500;

    /**
     * Create a customer record in database
     * @param customer The input entity object
     * @return The complete entity object, the id is reset.
     */
    public Customer createCustomer(Customer customer) {
        try {
            CustomerDao customerDAO = new CustomerDao();
            boolean suc = customerDAO.add(customer);
            Customer withIdInfo = customerDAO.findByPhone(customer.getPhone());
            return withIdInfo;

            /*if (suc) {
             //Locate the customer again to get customerID, which is auto increased by database
             Customer withIdInfo = customerDAO.findByUsername(customer.getUsername());
             return withIdInfo;
             } else {
             ErrorMsg.setLastError(ErrorMsg.ERROR_DATABASE_LOGIC_ERROR);
             return null;
             }*/
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return null;
    }

    /**
     *
     * @param customer
     * @return
     */
    public boolean updateCustomer(Customer customer) {
        CustomerDao customerDAO = new CustomerDao();
        boolean ans = false;
        try {
            ans = customerDAO.update(customer);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return ans;
    }

    /**
     * Update a record in database
     * @param customerId The input entity object
     * @return true for success, false for otherwise
     */
    public boolean deleteCustomer(int customerId) {
        CustomerDao customerDAO = new CustomerDao();
        boolean ans = false;
        try {
            ans = customerDAO.delete(customerId);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return ans;
    }

    /**
     * Find the customer record as entity object by specifying a phone number
     * @param phone The specified phone number
     * @return The found record or null for not found
     */
    public Customer getCustomerByPhone(String phone) {
        CustomerDao customerDAO = new CustomerDao();
        Customer customer = null;
        try {
            customer = customerDAO.findByPhone(phone);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return customer;
    }

    /**
     *Find the customer record as entity object by specifying a username
     * @param username The specified username
     * @return
     */
    public Customer getCustomerByUsername(String username) {
        CustomerDao customerDAO = new CustomerDao();
        Customer customer = null;
        try {
            customer = customerDAO.findByUsername(username);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return customer;
    }

    /**
     *This method find a {@link Customer} with given {@code customerId}
     * @param customerId
     * @return {@link Customer}
     */
    public Customer getCustomerById(int customerId) {
        CustomerDao customerDAO = new CustomerDao();
        Customer customer = null;
        try {
            customer = customerDAO.findByCustomerId(customerId);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return customer;
    }

    /**
     *This method find a {@link Customer} with given {@code customer}
     * @param customer
     * @return an array list of {@link Customer}
     */
    public ArrayList<Customer> searchCustomer(Customer customer) {
        CustomerDao customerDAO = new CustomerDao();
        ArrayList<Customer> list = null;
        try {
            list = customerDAO.findByInstance(customer);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return list;
    }

    /**
     *This method check whether the customer is an active club member
     * @param customer
     * @return true if the customer is an active member, false for otherwise
     */
    public boolean checkMembershipActive(Customer customer) {
        if (customer.getIsClubMember()) {
            if (customer.getMembershipExpiry().compareTo(new Date()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method extend the membership of the customer for specified years
     * @param customer The specified customer
     * @param years The years to extend membership
     * @param start The time to start from
     * @return true for success, false for otherwise
     */
    public boolean extendMembership(Customer customer, int years, Date start) {
        if(customer!=null){
            if( checkMembershipActive(customer) ){
                Date d = customer.getMembershipExpiry();
                d.setYear( d.getYear()+years );
                customer.setMembershipExpiry(d);
            }else{
                Date d = new Date();
                d.setYear( d.getYear()+years );
                customer.setMembershipExpiry(d);
            }
            if( !customer.getIsClubMember() ){
                customer.setIsClubMember(true);
                customer.setPoint(MEMBERSHIP_INIT_POINT);
            }
            CustomerCtrl cc = new CustomerCtrl();
            boolean suc = cc.updateCustomer(customer);
            return suc;
        }else{
            return false;
        }
    }

    /**
     * This method has not supported yet
     * @param customer
     * @param Years
     * @return
     */
    public boolean payClubMemberFee(Customer customer, int Years) {
        ErrorMsg.setLastError(ErrorMsg.ERROR_NOT_SUPPORT_YET);
        return false;
    }
    
    /**
     * This method check whether the customer is exist or not. If the customer record has already exist, it return the complete object otherwise create new record.
     * @param customer
     * @return
     */
    public Customer checkCreateCustomer(Customer customer) {
        ArrayList<Customer>  list = searchCustomer(customer);
        if( list!=null && list.size()>0 ){
            Customer first = list.get(0);
            return first;
        }else{
            return createCustomer(customer);
        }
    }

}
