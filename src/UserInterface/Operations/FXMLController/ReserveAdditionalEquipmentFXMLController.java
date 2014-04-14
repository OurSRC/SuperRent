/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.EquipmentCtrl;
import ControlObjects.InsuranceCtrl;
import ControlObjects.VehicleCtrl;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  VehicleClassSelectedTF.setText(ReservationNavigator.SampleSharedVariable);
        VehicleClassSelectedTF.setText(ReservationNavigator.newReserve.getVehicleClass());
        AdditionalEquipmentListView.setDisable(true);
        ArrayList<String> newInsuranceList = new ArrayList();
        InsuranceCtrl newInsuranceCtrl = new InsuranceCtrl();
        newInsuranceList = newInsuranceCtrl.getInsuranceType();
        InsuranceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(newInsuranceList.size() + " Size of insurance list");
        ObservableList<String> items = FXCollections.observableArrayList(newInsuranceList);
        InsuranceList.setItems(items);

    }

    @FXML
    public void NextButtonAction(ActionEvent event) throws IOException, NoSuchMethodException {
        if (!InsuranceList.getSelectionModel().getSelectedItems().isEmpty()) {
            Object[] a = InsuranceList.getSelectionModel().getSelectedItems().toArray();
            ArrayList<String> SelectedInsurance = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                SelectedInsurance.add(a[i].toString());
                System.out.println(SelectedInsurance.get(i) + "   Value from ArrayList");
            }
            ReservationNavigator.newReserve.setInsurance(SelectedInsurance);
            System.out.println("Insurance is set");
        } else {
            System.out.println("Insurance is not set");

        }

        if (RequiredCB.isSelected() && !AdditionalEquipmentListView.getSelectionModel().getSelectedItems().isEmpty()) {
            Object[] a = AdditionalEquipmentListView.getSelectionModel().getSelectedItems().toArray();
            ArrayList<String> SelectedEquipments = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                SelectedEquipments.add(a[i].toString());
                System.out.println(SelectedEquipments.get(i) + "   Value from ArrayList");
            }
            ReservationNavigator.newReserve.setEquipmentType(SelectedEquipments);
            ReservationNavigator.clearVista();
            ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);
        } else if(NotRequiredCB.isSelected()){
            ReservationNavigator.clearVista();
            ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);
        }else
        {
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

}
