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
import UserInterface.Operations.FXMLController.ReservationNavigator;
import entity.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.accessibility.AccessibleState.MODAL;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class CustomerMainPageFXMLController implements Initializable {

    @FXML
    private TextField PhoneTF;
    @FXML
    private TextField FirstNameTF;
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
    @FXML
    private Button ModifyCustomerButton;
    @FXML
    private Button RegisterClubMemberButton;
    @FXML
    private Button RenewClubMemberButton;
    @FXML
    private Button NewCustomerButton;
    @FXML
    private Button SearchCustomerButton;
    @FXML
    private Button NextButton;

    /* Variables to store values */
    public Customer currentCustomer;
    public boolean modifyFlag;
    public String customerPhone;
    public String customerFirstName;
    public String customerLastName;
    public String customerMiddleName;
    public String customerLicense;
    public String emailID;
    public String Address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifyFlag = false;
        if (!CustomerNavigator.CurrentUserName.equals("")) {
            NewCustomerButton.setVisible(false);
            SearchCustomerButton.setVisible(false);
            CustomerCtrl newCustomerCtrl = new CustomerCtrl();
            currentCustomer = newCustomerCtrl.getCustomerByUsername(CustomerNavigator.CurrentUserName);
            PhoneTF.setText(currentCustomer.getPhone());
            PhoneTF.setEditable(false);
            LicenseNumberTF.setEditable(false);
            PopulateCustomer();
        }
        if(!ReservationNavigator.reservation)
        {
            NextButton.setVisible(false);
        }
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
    private void RegisterClubMemberButtonAction(ActionEvent event) {
    }

    @FXML
    private void RenewClubMemberButtonAction(ActionEvent event) {
    }

    @FXML
    private void NewCustomerButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CreateCustomerFXML.fxml"));
        Pane newCustomerPane = (Pane) myLoader.load();
        CreateCustomerFXMLController newController = myLoader.getController();
        newController.loadCustomer();
        Scene scene = new Scene(newCustomerPane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    @FXML
    private void SearchCustomerButtonAction(ActionEvent event) {
        FirstNameTF.setText("");
        LastNameTF.setText("");
        MiddleNameTF.setText("");
        EmailTF.setText("");
        LicenseNumberTF.setText("");
        AddressTF.setText("");
        ClubMemberPointsTF.setText("");
        MemberShipExpiryTF.setText("");
        customerPhone = PhoneTF.getText();
        CustomerCtrl newCustomerCtrl = new CustomerCtrl();
        currentCustomer = newCustomerCtrl.getCustomerByPhone(customerPhone);
        if (currentCustomer != null) {
            PopulateCustomer();
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Customer Does not Exist");
            dialog.showDialog();
        }
    }

    @FXML
    private void ModifyCheckAction(KeyEvent event) {
        modifyFlag = true;
        System.out.println("Enable the Modify Button");
        ModifyCustomerButton.setDisable(false);

    }

    public void PopulateCustomer() {
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

    public void ValidateMandatory() {

    }
    
    @FXML
    private void NextButtonAction(ActionEvent event) throws NoSuchMethodException, IOException
    {
        if (!EmailTF.getText().isEmpty()) {
            ReservationNavigator.newReserve.setCustomerId(currentCustomer.getCustomerId());
            ReservationNavigator.clearVista();
            ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
        } else {
            ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
            System.out.println("Please select a customer");
        }
    }
}
