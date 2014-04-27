/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PeopleManagement.FXML;

import ControlObjects.CustomerCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import UserInterface.Login.FXMLController.CustomerNavigator;
import entity.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class CustomerPageForCustomerController implements Initializable {

    @FXML
    private TextField PhoneTF;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private Button RegisterClubMemberButton;
    @FXML
    private Button RenewClubMemberButton;
    @FXML
    private Button NewCustomerButton;
    @FXML
    private Button ModifyCustomerButton;
    @FXML
    private Button NextButton;
    @FXML
    private TextField MiddleNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField AddressTF;
    @FXML
    private TextField ClubMemberPointsTF;
    @FXML
    private TextField MemberShipExpiryTF;
    @FXML
    private TextField LicenseNumberTF;

    public Customer currentCustomer;
    private boolean modifyFlag;
    private String customerFirstName;
    private String customerLastName;
    private String customerMiddleName;
    private String customerLicense;
    private String emailID;
    private String Address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifyFlag = false;
        if (!CustomerNavigator.CurrentUserName.equals("")) {
            CustomerCtrl newCustomerCtrl = new CustomerCtrl();
            currentCustomer = newCustomerCtrl.getCustomerByUsername(CustomerNavigator.CurrentUserName);
            PhoneTF.setText(currentCustomer.getPhone());
            PhoneTF.setEditable(false);
            LicenseNumberTF.setEditable(false);
            PopulateCustomer();
        }
    }

    @FXML
    private void ModifyCheckAction(KeyEvent event) {
        modifyFlag = true;
        System.out.println("Enable the Modify Button");
        ModifyCustomerButton.setDisable(false);
    }

    @FXML
    private void RegisterClubMemberButtonAction(ActionEvent event) {
    }

    @FXML
    private void RenewClubMemberButtonAction(ActionEvent event) {
    }

    @FXML
    private void ModifyCustomerButtonAction(ActionEvent event) {
        customerFirstName = FirstNameTF.getText();
        customerLastName = LastNameTF.getText();
        customerMiddleName = MiddleNameTF.getText();
        customerLicense = LicenseNumberTF.getText();
        emailID = EmailTF.getText();
        Address = AddressTF.getText();

        currentCustomer.setAddress(Address);
        currentCustomer.setFirstName(customerFirstName);
        currentCustomer.setLastName(customerLastName);
        currentCustomer.setMiddleName(customerMiddleName);
        currentCustomer.setDriverLicenseNumber(customerLicense);
        currentCustomer.setEmail(emailID);

        CustomerCtrl newCustomerCtrl = new CustomerCtrl();
        if (newCustomerCtrl.updateCustomer(currentCustomer)) {
            System.out.println("Invalid Dates ");
            DialogFX dialog = new DialogFX(Type.INFO);
            dialog.setTitleText("Success");
            dialog.setMessage("Customer Information Successfully updated");
            dialog.showDialog();

        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Customer Update Failed");
            dialog.showDialog();
        }
    }

    @FXML
    private void NextButtonAction(ActionEvent event) {
    }

    private void PopulateCustomer() {

        FirstNameTF.setText(currentCustomer.getFirstName());
        LastNameTF.setText(currentCustomer.getLastName());
        MiddleNameTF.setText(currentCustomer.getMiddleName());
        EmailTF.setText(currentCustomer.getEmail());
        LicenseNumberTF.setText(currentCustomer.getDriverLicenseNumber());
        AddressTF.setText(currentCustomer.getAddress());

        if (currentCustomer.getIsClubMember()) {
            String memberPoint = Integer.toString(currentCustomer.getPoint());
            ClubMemberPointsTF.setText(memberPoint);
            MemberShipExpiryTF.setText(currentCustomer.getMembershipExpiry().toString());
            RenewClubMemberButton.setDisable(false);
            RegisterClubMemberButton.setDisable(true);

        } else {
            RegisterClubMemberButton.setDisable(false);
        }
        ModifyCustomerButton.setDisable(true);
    }

}