/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PeopleManagement.FXML;

import Account.CustomerCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import UserInterface.Operations.FXMLController.MembershipPaymentPageFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import Account.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class CustomerPageForClerkReservationController implements Initializable {

    @FXML
    private TextField CustomerNameTF;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private TextField LicenseNumberTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TableView CustomerTable;
    @FXML
    private TableColumn<?, ?> CustomerPhoneColumn;
    @FXML
    private TableColumn<?, ?> CustomerFullName;
    @FXML
    private TableColumn<?, ?> EmailColumn;
    @FXML
    private TableColumn<?, ?> LicenseColumn;
    @FXML
    private TableColumn<?, ?> MiddleNameColumn;
    @FXML
    private Button NeztButtonAction;
    @FXML
    private Button NewCustomerAction;
    @FXML
    private Button RegisterMemberAction;
    @FXML
    private Button RenewMemberAction;
    @FXML
    private TableColumn MembershipExpiryDateColumn;
    @FXML
    private TableColumn MemberPointsColumn;

    /**
     * Initializes the controller class.
     */
    /* Variables to store values */
    public String customerPhone;
    public String firstName;
    public String lastName;
    public String licenseNo;
    public String email;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NeztButtonAction(ActionEvent event) throws NoSuchMethodException, IOException {

        if (!CustomerTable.getSelectionModel().isEmpty()) {
            Customer selectedCustomer = (Customer) CustomerTable.getSelectionModel().getSelectedItem();
            ReservationNavigator.newReserve.setCustomerId(selectedCustomer.getCustomerId());
            ReservationNavigator.clearVista();
            ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please select a Customer to Proceed");
            dialog.showDialog();
        }
    }

    @FXML
    private void NewCustomerAction(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CreateCustomerFXML.fxml"));
        Pane newCustomerPane = (Pane) myLoader.load();

        Scene scene = new Scene(newCustomerPane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void RegisterMemberAction(ActionEvent event) throws IOException {
        if (!CustomerTable.getSelectionModel().isEmpty()) {

            Customer selectedCustomer = (Customer) CustomerTable.getSelectionModel().getSelectedItem();
            if (!selectedCustomer.getIsClubMember()) {
                Stage stage = new Stage();
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Operations/FXML/MembershipPaymentPageFXML.fxml"));
                Pane registerPane = (Pane) myLoader.load();
                MembershipPaymentPageFXMLController newController = myLoader.getController();
                newController.storeCustomer(selectedCustomer.getPhone());
                Scene scene = new Scene(registerPane);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Customer is already a Club Member");
                dialog.showDialog();
            }
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please select a Customer to Proceed");
            dialog.showDialog();
        }
    }

    @FXML
    private void RenewMemberAction(ActionEvent event
    ) throws IOException {
        if (!CustomerTable.getSelectionModel().isEmpty()) {
            Customer selectedCustomer = (Customer) CustomerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer.getIsClubMember()) {

                Stage stage = new Stage();
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Operations/FXML/MembershipPaymentPageFXML.fxml"));
                Pane renewPane = (Pane) myLoader.load();
                MembershipPaymentPageFXMLController newController = myLoader.getController();
                newController.storeCustomer(selectedCustomer.getPhone());
                Scene scene = new Scene(renewPane);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Please register Club Member");
                dialog.showDialog();
            }
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please select a Customer to Proceed");
            dialog.showDialog();
        }

    }

    @FXML
    private void SearchButtonAction(ActionEvent event
    ) {
        customerPhone = CustomerNameTF.getText();
        System.out.println("customerName : " + customerPhone);
        firstName = FirstNameTF.getText();
        lastName = LastNameTF.getText();
        licenseNo = LicenseNumberTF.getText();
        email = EmailTF.getText();
        if (ValidateMandatory()) {
            Customer newCustomer = new Customer();
            if (!customerPhone.equals("")) {
                newCustomer.setPhone(customerPhone);
            }
            if (!firstName.equals("")) {
                newCustomer.setFirstName(firstName);
            }
            if (!lastName.equals("")) {
                newCustomer.setLastName(lastName);
            }
            if (!email.equals("")) {
                newCustomer.setEmail(email);
            }
            if (!licenseNo.equals("")) {
                newCustomer.setDriverLicenseNumber(licenseNo);
            }

            CustomerCtrl newCustomerCtrl = new CustomerCtrl();
            ArrayList<Customer> customerArray = newCustomerCtrl.searchCustomer(newCustomer);
            ObservableList<Customer> slist = FXCollections.observableArrayList(customerArray);
            CustomerTable.setItems(slist);
            System.out.println(customerArray.size());
            CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("phone"));
            CustomerFullName.setCellValueFactory(new PropertyValueFactory("lastName"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory("email"));
            LicenseColumn.setCellValueFactory(new PropertyValueFactory("driverLicenseNumber"));
            MiddleNameColumn.setCellValueFactory(new PropertyValueFactory("middleName"));
            MembershipExpiryDateColumn.setCellValueFactory(new PropertyValueFactory("isClubMember"));
            MemberPointsColumn.setCellValueFactory(new PropertyValueFactory("point"));

        } else {
            System.out.println("Please enter any one value");
        }
    }

    private boolean ValidateMandatory() {
        if (!customerPhone.equals("") || !firstName.equals("") || !lastName.equals("") || !licenseNo.equals("") || !email.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}
