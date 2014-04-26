/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import ControlObjects.StaffCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ErrorMsg;
import SystemOperations.ValidateFields;
import entity.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class AddUserFXMLController implements Initializable {

    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private Font x2;
    @FXML
    private PasswordField SetPasswordPF;
    @FXML
    private PasswordField ReenterPasswordPF;
    @FXML
    private ComboBox BranchCB;
    @FXML
    private ComboBox RoleCB;
    @FXML
    private TextField PhoneNumberTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private ComboBox StatusCB;
    @FXML
    private TextField MiddleNameTF;

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private Staff.TYPE type;
    private Staff.STATUS status;
    private int branchID;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmButtonAction(ActionEvent event) throws IOException {
        if (!ValidateMandatoryFields()) {           
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Pleas enter the Mandatory Fields");
            dialog.showDialog();
        } 
        
        else if(!ValidatePassword()) {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Please make sure the password fields match");
            dialog.showDialog();
        }
        
        else if(!ValidatePhoneNumber()){
            DialogFX dialog = new DialogFX(Type.ERROR);
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
            userName = UserNameTF.getText().trim();
            password = SetPasswordPF.getText();
            
            switch(StatusCB.getSelectionModel().getSelectedItem().toString())
            {
                case "Active": 
                    status = Staff.STATUS.ACTIVE;
                    break;
                case "Deactivated":
                    status = Staff.STATUS.DEACTIVATED;
                    break;
            }
            
            switch(RoleCB.getSelectionModel().getSelectedItem().toString())
            {
                case "Clerk":
                    type = Staff.TYPE.CLERK;
                    break;
                case "Manager":
                    type = Staff.TYPE.MANAGER;
                    break;
                case "Admin":
                    type = Staff.TYPE.ADMIN;
                                      
            }
            
            switch(BranchCB.getSelectionModel().getSelectedItem().toString())
            {
                default:
                    branchID = 1;
            }
                
            //test
            System.out.println("All the values set!!!");
            
            Staff newStaff = new Staff(branchID, firstName, middleName, lastName, email,
            phone, status, type, userName, password);
        
            //test
            System.out.println("newStaff created");
            
            StaffCtrl newStaffCtrl = new StaffCtrl();
            Staff returnedStaff = newStaffCtrl.createStaff(newStaff);
            
            if (returnedStaff ==  null){
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Staff creation attempt unsuccessful  ");
                dialog.showDialog();
            } else {
                    DialogFX dialog = new DialogFX(Type.INFO);
                    dialog.setTitleText("Success");
                    dialog.setMessage( type.toString() + " " + userName + " is successfully created");
                    dialog.showDialog();
                    PPLManagementNavigator.loadVista(PPLManagementNavigator.UserSearch);
            }
        
        }
        
        
    }


    @FXML
    private void BranchCBAction(ActionEvent event) {
    }

    @FXML
    private void RoleCBAction(ActionEvent event) {
    }

    @FXML
    private void StatusCBAction(ActionEvent event) {

    }
    
    private boolean ValidatePassword()
    {
     if (SetPasswordPF.getText().equals(ReenterPasswordPF.getText()))
     {
         return true;
     }
     return false;
    }
    
    private boolean ValidatePhoneNumber()
    {
        
        return ValidateFields.CheckIntegerNumbersOnly(PhoneNumberTF.getText().trim());
    }
    
    private boolean ValidateMandatoryFields()
    {
        
        if (UserNameTF.getText().equals("") || FirstNameTF.getText().equals("") ||
                LastNameTF.getText().equals("")  || EmailTF.getText().equals("") ||
                BranchCB.valueProperty().isNull().getValue() || RoleCB.valueProperty().isNull().getValue() ||
                StatusCB.valueProperty().isNull().getValue() || SetPasswordPF.getText().equals(""))
        {
           
            return false;
        } else {
            System.out.println("returned true");
            return true;
        }
                
        
    }

    @FXML
    private void CancelButtonAction(ActionEvent event) throws IOException {
        PPLManagementNavigator.clearVista();
        PPLManagementNavigator.loadVista(PPLManagementNavigator.UserSearch);
    }
}
