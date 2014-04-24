/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import ControlObjects.StaffCtrl;
import SystemOperations.DialogFX;
import SystemOperations.ErrorMsg;
import SystemOperations.ValidateFields;
import entity.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class UserProfileRemoveFXMLController implements Initializable {

    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private Font x2;
    @FXML
    private PasswordField SetNewPasswordPF;
    @FXML
    private PasswordField ReenterNewPasswordPF;
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
    @FXML
    private Label userNameLabel;

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
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
        StaffCtrl newStaffCtrl = new StaffCtrl();
        FirstNameTF.setText(PPLManagementNavigator.modifyStaff.getFistName());
        MiddleNameTF.setText(PPLManagementNavigator.modifyStaff.getMiddleName());
        LastNameTF.setText(PPLManagementNavigator.modifyStaff.getLastName());
        PhoneNumberTF.setText(PPLManagementNavigator.modifyStaff.getPhone());
        EmailTF.setText(PPLManagementNavigator.modifyStaff.getEmail());
        SetNewPasswordPF.setText(PPLManagementNavigator.modifyStaff.getPassword());
        ReenterNewPasswordPF.setText(PPLManagementNavigator.modifyStaff.getPassword());
        userNameLabel.setText(PPLManagementNavigator.modifyStaff.getUsername());
        
        switch(PPLManagementNavigator.modifyStaff.getBranchId()){
            case 1:
                BranchCB.getSelectionModel().select(0);
        }
        switch(PPLManagementNavigator.modifyStaff.getStatus().toString()) {
            case "ACTIVE" :
                StatusCB.getSelectionModel().select(0);
                break;
            case "DEACTIVATED" :
                StatusCB.getSelectionModel().select(1);
        }
        switch (PPLManagementNavigator.modifyStaff.getStaffType().toString()) {
            case "CLERK" :
                RoleCB.getSelectionModel().select(0);
                break;
            case "MANAGER" :
                RoleCB.getSelectionModel().select(1);
                break;
            case "ADMIN" :
                RoleCB.getSelectionModel().select(2);
        }
    }    

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
        if (!ValidateMandatoryFields()){
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter all the mandatory fields");
            dialog.showDialog();
        }
        
        else if (!ValidatePassword()){
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please make sure the password fields match");
            dialog.showDialog();
        } 
        
        else if(!ValidatePhoneNumber()){
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter a valid phone number");
            dialog.showDialog();
        } 
        else {
            firstName = FirstNameTF.getText();
            lastName = LastNameTF.getText();
            middleName = MiddleNameTF.getText();
            email = EmailTF.getText();
            phone = PhoneNumberTF.getText();
            password = SetNewPasswordPF.getText();
            
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
            
            //Updating modifyStaff with new values
            PPLManagementNavigator.modifyStaff.setFistName(firstName);
            PPLManagementNavigator.modifyStaff.setLastName(lastName);
            PPLManagementNavigator.modifyStaff.setMiddleName(middleName);
            PPLManagementNavigator.modifyStaff.setEmail(email);
            PPLManagementNavigator.modifyStaff.setBranchId(branchID);
            PPLManagementNavigator.modifyStaff.setPhone(phone);
            PPLManagementNavigator.modifyStaff.setPassword(password);
            PPLManagementNavigator.modifyStaff.setStaffType(type);
            PPLManagementNavigator.modifyStaff.setStatus(status);
            StaffCtrl newStaffCtrl = new StaffCtrl();
            if(newStaffCtrl.updateStaff(PPLManagementNavigator.modifyStaff)) {
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Success");
                dialog.setMessage("Staff successfully updated.");
                dialog.showDialog();
            } else {
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Operation unsuccessful.");
                dialog.showDialog();
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
     if (SetNewPasswordPF.getText().equals(ReenterNewPasswordPF.getText()))
     {
         return true;
     }
     return false;
    }
    
    private boolean ValidatePhoneNumber()
    {
        return ValidateFields.CheckForNumbersOnly(PhoneNumberTF.getText().trim());
    }
    
    private boolean ValidateMandatoryFields()
    {
        
        if ( FirstNameTF.getText().equals("") ||
                LastNameTF.getText().equals("")  || EmailTF.getText().equals("") ||
                BranchCB.valueProperty().isNull().getValue() || RoleCB.valueProperty().isNull().getValue() ||
                StatusCB.valueProperty().isNull().getValue() || SetNewPasswordPF.getText().equals(""))
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
