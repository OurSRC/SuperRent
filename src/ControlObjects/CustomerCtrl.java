package ControlObjects;

import SystemOperations.ErrorMsg;
import dao.CustomerDao;
import dao.DaoException;
import entity.Customer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerCtrl {

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

    public Customer getCustomerByPhone(String phone) {
        CustomerDao customerDAO = new CustomerDao();
        Customer customer = null;
        try {
            customer = customerDAO.findByPhone(phone);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return  customer;
    }

    public Customer getCustomerByUsername(String username) {
        CustomerDao customerDAO = new CustomerDao();
        Customer customer = null;
        try {
            customer = customerDAO.findByUsername(username);
        } catch (DaoException ex) {
            Logger.getLogger(CustomerCtrl.class.getName()).log(Level.SEVERE, null, ex);
            ErrorMsg.setLastError(ErrorMsg.ERROR_GENERAL);
        }
        return  customer;
    }

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

    public boolean payClubMemberFee(Customer customer, int Years) {
        ErrorMsg.setLastError(ErrorMsg.ERROR_NOT_SUPPORT_YET);
        return false;
    }
}
