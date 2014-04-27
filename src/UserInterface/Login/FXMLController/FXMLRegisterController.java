package UserInterface.Login.FXMLController;



import ControlObjects.CustomerCtrl;
import SystemOperations.DialogFX;
import SystemOperations.ValidateFields;
import UserInterface.PeopleManagement.FXMLController.PPLManagementNavigator;
import entity.Customer;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vyas
 */
public class FXMLRegisterController {
   
    @FXML
    private Label NameLabel;
    @FXML
    private TextField AddressTF;
    @FXML
    private Label AddressLabel;
    @FXML
    private TextField EmailTF;
    @FXML
    private Label EmailLabel;
    @FXML
    private TextField UsernameTF;
    @FXML
    private TextField PasswordPF;
    @FXML
    private TextField ConfirmPasswordPF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private Label MobileLabel;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField MiddleNameTF;
    @FXML
    private TextField PhoneNumberTF;
    @FXML
    private TextField LicenseNoTF;
    
    @FXML
    private Button submitButton;
    @FXML
    private Button ResetButton;
    
    
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String licenseNo;
    private String address;
    
    
    
    @FXML
    public void SubmitButtonAction(ActionEvent event) throws Exception {
        
        if (!ValidateMandatoryFields()) {           
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Pleas enter the Mandatory Fields");
            dialog.showDialog();
        } 
        
        else if(!ValidatePassword()) {
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Please make sure the password fields match");
            dialog.showDialog();
        }
        
        else if(!ValidatePhoneNumber()){
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Please enter a valid phone number");
            dialog.showDialog();
        }
            
        else {
            firstName = FirstNameTF.getText().trim();
            lastName = LastNameTF.getText().trim();
            middleName = MiddleNameTF.getText().trim();
            email = EmailTF.getText().trim();
            phone = PhoneNumberTF.getText().trim();
            userName = UsernameTF.getText().trim();
            password = PasswordPF.getText();
            licenseNo = LicenseNoTF.getText();
            address = AddressTF.getText();
            
            Customer newCustomer = new Customer (userName, password, phone, address, firstName, middleName,
                    lastName, email, licenseNo, false, 0, null);
            
            CustomerCtrl newCustomerCtrl = new CustomerCtrl();
            
            Customer returnedCustomer = newCustomerCtrl.createCustomer(newCustomer);
            
            if (returnedCustomer ==  null){
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Customer creation attempt unsuccessful  ");
                dialog.showDialog();
            } else {
                    DialogFX dialog = new DialogFX(DialogFX.Type.INFO);
                    dialog.setTitleText("Success");
                    dialog.setMessage( firstName + "," + " welcome to SuperRent!");
                    dialog.showDialog();
                    PPLManagementNavigator.loadVista(PPLManagementNavigator.UserSearch);
            }
            
            
            
            
    }
    }
    private boolean ValidatePassword()
    {
     if (PasswordPF.getText().equals(ConfirmPasswordPF.getText()))
     {
         return true;
     }
     return false;
    }
    
    private boolean ValidatePhoneNumber()
    {
        
        return ValidateFields.ValidatePhoneNumber(PhoneNumberTF.getText().trim());
    }
    
    private boolean ValidateMandatoryFields()
    {
        
        if (ValidateFields.CheckLettersOnly(FirstNameTF.getText()) && ValidateFields.CheckLettersOnly(LastNameTF.getText()) &&
                ValidateFields.CheckLettersOnly(EmailTF.getText()) && !EmailTF.getText().equals("") &&
                !AddressTF.getText().equals("") && !UsernameTF.getText().equals("") && 
                !PasswordPF.getText().equals(""))
        {
           
            return true;
        } else {

            return false;
        }
                
        
    }

    
    @FXML
    private void ResetCustomerFieldsAction(ActionEvent event) {
        FirstNameTF.clear();
        LastNameTF.clear();
        MiddleNameTF.clear();
        EmailTF.clear();
        PhoneNumberTF.clear();
        UsernameTF.clear();
        PasswordPF.clear();
        LicenseNoTF.clear();
        AddressTF.clear();
        ConfirmPasswordPF.clear();
    }

    
}