/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.VehicleCtrl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewVehiclesforSaleFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TableView VehiclesforSaleTable;
    @FXML
    private TableColumn VehicleNo;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn Model;
    @FXML
    private TableColumn Year;
    @FXML
    private TableColumn Odometer;
    @FXML
    private TableColumn SalePrice;
    @FXML
    private Label VehicleCategoryLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private Label VehicleModelLabel;
    @FXML
    private TextField VehicleModelTF;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label VehicleYearLabel;
    @FXML
    private TextField VehicleYearTF;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private Label VehicleTypeLabel;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private RadioButton VehicleTypeCarRB;
    private String vehicleType;
    private String vehicleClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }    

    @FXML
    private void ViewVehiclesforSaleAction(ActionEvent event) {
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void VehicleClassCBAction(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        vehicleType = "TRUCK";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getTruckType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }
    
}
