/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PeopleManagement.FXML;

import Account.Customer;
import Account.CustomerCtrl;
import Account.CustomerDao;
import Dao.DaoException;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import UserInterface.Login.FXMLController.CustomerNavigator;
import UserInterface.Operations.FXMLController.MembershipPaymentPageFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        
        PhoneTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = PhoneTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        PhoneTF.setText(PhoneTF.getText().substring(0, PhoneTF.getText().length() - 1));
                    }
                }
            }


           

        }); 
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
    private void RegisterClubMemberButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Operations/FXML/MembershipPaymentPageFXML.fxml"));
        Pane registerPane = (Pane) myLoader.load();
        MembershipPaymentPageFXMLController newController = myLoader.getController();
        newController.storeCustomer(PhoneTF.getText());
        Scene scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        PopulateCustomer();
    }

    @FXML
    private void RenewClubMemberButtonAction(ActionEvent event) throws IOException {
        if(currentCustomer.getIsClubMember())
        {
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Operations/FXML/MembershipPaymentPageFXML.fxml"));
            Pane renewPane = (Pane) myLoader.load();
            MembershipPaymentPageFXMLController newController = myLoader.getController();
            newController.storeCustomer(currentCustomer.getPhone());
            Scene scene = new Scene(renewPane);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            PopulateCustomer();

        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Customer is not a Club Member");
            dialog.showDialog();
        }
    }

    @FXML
    private void NewCustomerButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CreateCustomerFXML.fxml"));
        Pane newCustomerPane = (Pane) myLoader.load();
        Scene scene = new Scene(newCustomerPane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
    }

    @FXML
    private void SearchCustomerButtonAction(ActionEvent event) throws DaoException {
        FirstNameTF.setText("");
        LastNameTF.setText("");
        MiddleNameTF.setText("");
        EmailTF.setText("");
        AddressTF.setText("");
        ClubMemberPointsTF.setText("");
        MemberShipExpiryTF.setText("");
        customerPhone = PhoneTF.getText();
        customerLicense = LicenseNumberTF.getText();
        CustomerDao newCustomerCtrl = new CustomerDao();
        currentCustomer = newCustomerCtrl.findByPhoneAndLicense(customerPhone,customerLicense);
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
        PhoneTF.setText(currentCustomer.getPhone());
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
