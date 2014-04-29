/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import Account.StaffCtrl;
import SystemOperations.DialogFX;
import Dao.DaoException;
import Account.UserDao;
import Account.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class UserSearchFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private ComboBox BranchCB;
    @FXML
    private ComboBox RoleCB;
    @FXML
    private TextField PhoneNumberTF;
    @FXML
    private TextField UsernameTF;
    
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userName;
    private Staff.TYPE type;
    private int branchID;
    @FXML
    private TableView StaffSearchTable;
    @FXML
    private TableColumn FirstNameCol;
    @FXML
    private TableColumn LastNameCol;
    @FXML
    private TableColumn UserIDCol;
    @FXML
    private TableColumn BranchCol;
    @FXML
    private TableColumn RoleCol;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchButtonAction(ActionEvent event) {
        
        if (!ValidateMandatoryFields()){
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Pleas enter at least one criterion");
            dialog.showDialog();
        }
        else{
            if (!FirstNameTF.getText().isEmpty()){
                firstName = FirstNameTF.getText();
                System.out.println("first name: " + firstName);
            }
            else {
                firstName = null;
            }
            if (!LastNameTF.getText().isEmpty()) {
                lastName = LastNameTF.getText();
                System.out.println("last name: " + lastName);
            }
            else {
                lastName = null;
            }
            if (!PhoneNumberTF.getText().isEmpty()) {
                phoneNumber = PhoneNumberTF.getText();
                System.out.println("phone number: " + phoneNumber);                                               
            }
            else {
                phoneNumber = null;
            }
            if (!UsernameTF.getText().isEmpty()) {
                userName = UsernameTF.getText();
                System.out.println("username: " + userName);
            }
            else {
                userName = null;
            }
            if (!BranchCB.valueProperty().isNull().getValue()) {
                branchID = 1;
            }
            else {
                branchID = 1;
            }
            if (!RoleCB.valueProperty().isNull().getValue()) {
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
                System.out.println("Role: " + type.toString());
            }
            else {
                type = null;
            }
            populateSearchTable();
        }
    }
    
    private void populateSearchTable(){
        Staff newStaff = new Staff(branchID, firstName, null , lastName, 
                null, phoneNumber, null , type, userName, null);
        StaffSearchTable.getItems().clear();
        StaffCtrl newStaffCtrl = new StaffCtrl();
        ArrayList<Staff> staffArrayList = newStaffCtrl.searchStaff(newStaff);
        System.out.println(staffArrayList.toString());
        ObservableList<Staff> staffObservableList = FXCollections.observableArrayList(staffArrayList);
        StaffSearchTable.setItems(staffObservableList);
        FirstNameCol.setCellValueFactory(new PropertyValueFactory("fistName"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        UserIDCol.setCellValueFactory(new PropertyValueFactory("username"));
        BranchCol.setCellValueFactory(new PropertyValueFactory("branchId"));
        RoleCol.setCellValueFactory(new PropertyValueFactory("staffType"));
        
        
    }
    @FXML
    private void BranchCBAction(ActionEvent event) {
    }

    @FXML
    private void RoleCBAction(ActionEvent event) {
    }
    
    private boolean ValidateMandatoryFields(){
        if (FirstNameTF.getText().equals("") && LastNameTF.getText().equals("") && 
                PhoneNumberTF.getText().equals("") && UsernameTF.getText().equals("") &&
                BranchCB.valueProperty().isNull().getValue() && RoleCB.valueProperty().isNull().getValue())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @FXML
    private void ModifyButtonAction(ActionEvent event)throws IOException, DaoException {
        UserDao findUser = new UserDao();
        if(!StaffSearchTable.getSelectionModel().isEmpty()){
            PPLManagementNavigator.modifyStaff = (Staff) StaffSearchTable.getSelectionModel().getSelectedItem();
            /*Staff selectedStaff = (Staff) StaffSearchTable.getSelectionModel().getSelectedItem();
            PPLManagementNavigator.modifyStaff = findUser.find(selectedStaff.getUsername()) ;*/
            PPLManagementNavigator.loadVista(PPLManagementNavigator.UserProfile);
        }
        
    }
    
}
