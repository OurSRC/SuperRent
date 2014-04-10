/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ModifyRatesFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private ComboBox VehicleClassCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) throws IOException {
        VehicleClassNavigator.loadVista(VehicleClassNavigator.VEHICLECLASSMAINPAGE);
    }

    @FXML
    private void WeeklyRateTF(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list =  FXCollections.observableArrayList(vehicleControl.getTrunkType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list =  FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void DailyRateTF(ActionEvent event) {
    }

    @FXML
    private void HourlyRateTF(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeCBAction(ActionEvent event) {
    }
    
}
