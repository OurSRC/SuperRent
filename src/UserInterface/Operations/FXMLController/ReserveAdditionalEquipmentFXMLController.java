/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.CustomerCtrl;
import ControlObjects.EquipmentCtrl;
import ControlObjects.InsuranceCtrl;
import ControlObjects.VehicleCtrl;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import UserInterface.Login.FXMLController.CustomerNavigator;
import entity.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReserveAdditionalEquipmentFXMLController implements Initializable {

    @FXML
    private TextField VehicleClassSelectedTF;
    public ListView AdditionalEquipmentListView;
    public ListView InsuranceList;

    public CheckBox DamageWaiverCheckBox;
    public CheckBox PersonalAccidentCheckBox;
    public CheckBox PersonalEffectsCoverageCheckBox;
    public CheckBox RoadsideAssistanceCheckBox;
    public RadioButton RequiredCB;
    public RadioButton NotRequiredCB;

    @FXML
    private ToggleGroup AdditionalEquipmentTG;
    @FXML
    private RadioButton InsuranceRequiredCB;
    @FXML
    private ToggleGroup InsuranceTG;
    @FXML
    private RadioButton InsuranceNotRequiredCB;

    private boolean insurance_ok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  VehicleClassSelectedTF.setText(ReservationNavigator.SampleSharedVariable);
        VehicleClassSelectedTF.setText(ReservationNavigator.newReserve.getVehicleClass());
        AdditionalEquipmentListView.setDisable(true);
        InsuranceList.setDisable(true);

    }

    @FXML
    public void NextButtonAction(ActionEvent event) throws IOException, NoSuchMethodException {
        insurance_ok = false;
        if (InsuranceRequiredCB.isSelected() && !InsuranceList.getSelectionModel().getSelectedItems().isEmpty()) {
            Object[] a = InsuranceList.getSelectionModel().getSelectedItems().toArray();
            ArrayList<String> SelectedInsurance = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                SelectedInsurance.add(a[i].toString());
                System.out.println(SelectedInsurance.get(i) + "   Value from ArrayList");
            }
            ReservationNavigator.newReserve.setInsurance(SelectedInsurance);
            insurance_ok = true;
            System.out.println("Insurance is set");
        } else if (InsuranceNotRequiredCB.isSelected()) {
            insurance_ok = true;

        } else {
            insurance_ok = false;
            System.out.println("Insurance is not set");
        }

        if (RequiredCB.isSelected() && !AdditionalEquipmentListView.getSelectionModel().getSelectedItems().isEmpty() && insurance_ok) {
            Object[] a = AdditionalEquipmentListView.getSelectionModel().getSelectedItems().toArray();
            ArrayList<String> SelectedEquipments = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                SelectedEquipments.add(a[i].toString());
                System.out.println(SelectedEquipments.get(i) + "   Value from ArrayList");
            }
            ReservationNavigator.newReserve.setEquipmentType(SelectedEquipments);
            ReservationNavigator.clearVista();
            if (ClerkMainPageNavigator.staff) {
                ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);
            } else if (CustomerNavigator.customerFlag) {
                CustomerCtrl newCustomerCtrl = new CustomerCtrl();
                Customer currentCustomer = newCustomerCtrl.getCustomerByUsername(CustomerNavigator.CurrentUserName);
                ReservationNavigator.newReserve.setCustomerId(currentCustomer.getCustomerId());
                ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
            } else {
                System.out.println("Call the CreateCustomer for Guest user");
            }
        } else if (NotRequiredCB.isSelected()) {
            ReservationNavigator.clearVista();
            if (ClerkMainPageNavigator.staff) {
                ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);
            } else if (CustomerNavigator.customerFlag) {
                CustomerCtrl newCustomerCtrl = new CustomerCtrl();
                Customer currentCustomer = newCustomerCtrl.getCustomerByUsername(CustomerNavigator.CurrentUserName);
                ReservationNavigator.newReserve.setCustomerId(currentCustomer.getCustomerId());
                ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
            } else {
                System.out.println("Call the CreateCustomer for Guest user");
                ReservationNavigator.loadVista(ReservationNavigator.ReservationGuestPage);
            }
        } else {
            System.out.println("Please select an Additional Item");

        }

    }

    @FXML
    public void BackButtonAction(ActionEvent event) throws IOException, NoSuchMethodException {
        ReservationNavigator.clearVista();
        ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);
    }

    public void populateListView() {
        EquipmentCtrl equipCtrl = new EquipmentCtrl();
        ArrayList<String> AdditionalEquipmentArray = equipCtrl.getEquipmentTypeByVehicleClass(VehicleClassSelectedTF.getText());
        ObservableList<String> items = FXCollections.observableArrayList(AdditionalEquipmentArray);
        AdditionalEquipmentListView.setItems(items);
    }

    @FXML
    public void RequiredCBAction(ActionEvent event) {
        AdditionalEquipmentListView.setDisable(false);
        AdditionalEquipmentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        populateListView();
    }

    @FXML
    public void NotRequiredCBAction(ActionEvent event) {
        AdditionalEquipmentListView.getItems().clear();
        AdditionalEquipmentListView.setDisable(true);

    }

    @FXML
    private void InsuranceRequiredCBAction(ActionEvent event) {
        InsuranceList.setDisable(false);
        InsuranceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<String> newInsuranceList = new ArrayList();
        InsuranceCtrl newInsuranceCtrl = new InsuranceCtrl();
        newInsuranceList = newInsuranceCtrl.getInsuranceType();
        InsuranceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(newInsuranceList.size() + " Size of insurance list");
        ObservableList<String> items = FXCollections.observableArrayList(newInsuranceList);
        InsuranceList.setItems(items);
    }

    @FXML
    private void InsuranceNotRequiredCBAction(ActionEvent event) {

        InsuranceList.getItems().clear();
        InsuranceList.setDisable(true);
    }

}
