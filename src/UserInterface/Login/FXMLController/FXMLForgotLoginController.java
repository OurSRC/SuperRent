package UserInterface.Login.FXMLController;

import ControlObjects.CustomerCtrl;
import SystemOperations.MailSender;
import entity.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
 
/**
* Controller class for the second vista.
*/
public class FXMLForgotLoginController implements Initializable{

    
    public TextField UserNameTF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void SendEmailButton(ActionEvent event)
    {
        CustomerCtrl newCustomerCtrl = new CustomerCtrl();
        
        if(ValidateMandatory())
        {
            String UserName = UserNameTF.getText();
            Customer forgotCustomer = newCustomerCtrl.getCustomerByUsername(UserName);
            
            String newPassword = forgotCustomer.getPhone() + "@123";
            forgotCustomer.setPasswordText(newPassword);
            
            if(newCustomerCtrl.updateCustomer(forgotCustomer))
            {
                System.out.println("Password set and send to Mail");
            }else
            {
                System.out.println("Password Reset has failed.Please check with Branch");
                MailSender.send(forgotCustomer.getEmail(), "Password Reset for : " + forgotCustomer.getUsername(), "Your Password has been reset to " + newPassword + " .Please contact Branch if you still face issues");
            }
            
            
            
            
        }
    }

    private boolean ValidateMandatory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
/**
* Event handler fired when the user requests a previous vista.
*
* @param event the event that triggered the handler.
*/
}