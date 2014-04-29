/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PeopleManagement.FXML;

import Account.CustomerCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ValidateFields;
import UserInterface.Operations.FXMLController.OperationsFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import Account.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class CreateCustomerFXMLController implements Initializable {

    @FXML
    private TextField CustomerPhoneTF;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private TextField MiddleNameTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField AddressTF;
    @FXML
    private TextField LicenseTF;

    /* Variables to store values */
    public String customerPhone;
    public String firstName;
    public String lastName;
    public String middleName;
    public String email;
    public String address;
    public String licenseNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void CreateCustomerButtonAction(ActionEvent event) {
        System.out.println(customerPhone + " I am the phone number");
        if (validateMandatory()) {
            Customer newCustomer = new Customer();
            newCustomer.setPhone(customerPhone);
            newCustomer.setFirstName(firstName);
            newCustomer.setAddress(address);
            newCustomer.setLastName(lastName);
            newCustomer.setMiddleName(middleName);
            newCustomer.setEmail(email);
            newCustomer.setDriverLicenseNumber(licenseNumber);
            newCustomer.setIsClubMember(false);
            newCustomer.setUsername(null);
            CustomerCtrl newCustomerCtrl = new CustomerCtrl();
            Customer createCustomer = newCustomerCtrl.createCustomer(newCustomer);
            if (createCustomer != null) {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Success");
                dialog.setMessage("New Customer has been created");
                dialog.showDialog();
                ((Node) (event.getSource())).getScene().getWindow().hide();

            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Customer Creation Failed");
                dialog.showDialog();
            }
        }
    }

    @FXML
    private void AbortButtonAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();   
    }

    public boolean validateMandatory() {
        customerPhone = CustomerPhoneTF.getText();
        firstName = FirstNameTF.getText();
        lastName = LastNameTF.getText();
        middleName = MiddleNameTF.getText();
        address = AddressTF.getText();
        email = EmailTF.getText();
        licenseNumber = LicenseTF.getText();
        if (!customerPhone.equals("")
                && !firstName.equals("")
                && !lastName.equals("")
                && !middleName.equals("")
                && !address.equals("")
                && !email.equals("")
                && !licenseNumber.equals("")) {
            if(ValidateFields.CheckIntegerNumbersOnly(customerPhone))
            {
                
                return true;
            }else 
            {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Improrper Phone Number");
                dialog.showDialog();
                return false;
            }
        } else {
            return false;
        }
    }

}
