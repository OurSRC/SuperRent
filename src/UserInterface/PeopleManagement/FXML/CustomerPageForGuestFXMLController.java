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
import UserInterface.Operations.FXMLController.ReservationNavigator;
import Account.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class CustomerPageForGuestFXMLController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    public String customerPhone;
    public String firstName;
    public String lastName;
    public String middleName;
    public String email;
    public String address;
    public String licenseNumber;
    public Customer createCustomer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CustomerPhoneTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(16));
        CustomerPhoneTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = CustomerPhoneTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        CustomerPhoneTF.setText(CustomerPhoneTF.getText().substring(0, CustomerPhoneTF.getText().length() - 1));
                    }
                }
            }

           

        }); 
        
        FirstNameTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = FirstNameTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!ValidateFields.CheckLettersOnly(String.valueOf(ch))) {

                        //if it's not number then just setText to previous one
                        FirstNameTF.setText(FirstNameTF.getText().substring(0, FirstNameTF.getText().length() - 1));
                    }
                }
            }

        });
        
         LastNameTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = LastNameTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!ValidateFields.CheckLettersOnly(String.valueOf(ch))) {

                        //if it's not number then just setText to previous one
                        LastNameTF.setText(LastNameTF.getText().substring(0, LastNameTF.getText().length() - 1));
                    }
                }
            }

        });
         
         MiddleNameTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = MiddleNameTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!ValidateFields.CheckLettersOnly(String.valueOf(ch))) {

                        //if it's not number then just setText to previous one
                        MiddleNameTF.setText(MiddleNameTF.getText().substring(0, MiddleNameTF.getText().length() - 1));
                    }
                }
            }

        });
        
    }

    @FXML
    private void NextButtonAction(ActionEvent event) throws IOException {
        createCustomer(); 
    }

    public void createCustomer() throws IOException {
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
            createCustomer = newCustomerCtrl.checkCreateCustomer(newCustomer);
            
                Customer currentCustomer = newCustomerCtrl.getCustomerByPhone(createCustomer.getPhone());
                ReservationNavigator.newReserve.setCustomerId(createCustomer.getCustomerId());
                ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
            
        } else {
            System.out.println("please enter all mandatory Fields");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Missing Details");
            dialog.setMessage("Please Enter all Mandatory Fields to proceed");
            dialog.showDialog();
        }
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
            if (ValidateFields.CheckIntegerNumbersOnly(customerPhone)) {

                return true;
            } else {
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Improrper Phone Number");
                dialog.showDialog();
                return false;
            }
        } else {
            return false;
        }
    }
    
    public static EventHandler<KeyEvent> maxLength(final Integer i) {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {

                TextField tx = (TextField) arg0.getSource();
                if (tx.getText().length() >= i) {
                    arg0.consume();
                }
            }
        };
    }

}
