/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class AddVehicleFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TextField ModelTF;
    @FXML
    private TextField PlateNumberTF;
    @FXML
    private TextField ManufacturerTF;
    @FXML
    private Font x2;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private ComboBox VehicleTypeCB;
    @FXML
    private DatePicker ManufacturingDatePicker;
    @FXML
    private DatePicker PurchaseDatePicker;
    @FXML
    private ComboBox StatusCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModelTFAction(ActionEvent event) {
    }

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
    }

    @FXML
    private void BeckToSearchButtonAction(ActionEvent event) throws IOException {
        VehicleNavigator.loadVista(VehicleNavigator.VEHICLEMAINPAGE);
    }

    @FXML
    private void PlateNumberTFAction(ActionEvent event) {
    }

    @FXML
    private void ManufacturerTFAction(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list =  FXCollections.observableArrayList(vehicleControl.getTrunkType());
        VehicleTypeCB.getItems().clear();
        VehicleTypeCB.setItems(list);

    
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list =  FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleTypeCB.getItems().clear();
        VehicleTypeCB.setItems(list);
    }

    @FXML
    private void VehicleTypeCBAction(ActionEvent event) {
    }

    @FXML
    private void StatusCBAction(ActionEvent event) {
    }
    
}
