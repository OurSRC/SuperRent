/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PeopleManagement.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> CustomerTable;
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

    /**
     * Initializes the controller class.
     */
    /* Variables to store values */
    public String customerName;
    public String firstName;
    public String lastName;
    public String licenseNo;
    public String email;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void NeztButtonAction(ActionEvent event) {
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
    private void RegisterMemberAction(ActionEvent event) {
    }

    @FXML
    private void RenewMemberAction(ActionEvent event) {
    }

    @FXML
    private void SearchButtonAction(ActionEvent event) {
        customerName = CustomerNameTF.getText();
        firstName = FirstNameTF.getText();
        lastName = LastNameTF.getText();
        licenseNo = LicenseNumberTF.getText();
        email = EmailTF.getText();
        if (true) {

        }
    }

 
}
