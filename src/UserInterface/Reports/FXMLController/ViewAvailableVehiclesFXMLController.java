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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewAvailableVehiclesFXMLController implements Initializable {

    @FXML
    private Label VehicleCategoryLabel;
    @FXML
    private TableView VehiclesAvailableTable;
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
    private TableColumn RateDay;
    @FXML
    private TableColumn RateWeek;
    @FXML
    private Button SearchButton;
    @FXML
    private Label PickUpDateLabel;
    @FXML
    private Label RetrunDateLabel;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private Label VehicleTypeLabel;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private DatePicker PickUpDateDP;
    @FXML
    private DatePicker ReturnDateDP;
    private String vehicleType;
    private String vehicleClass;
    public String PickUpDateValue;
    public String ReturnUpDateValue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       SearchButton.setDisable(true);
       vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }    

    @FXML
    private void ViewAvailableVehiclesAction(ActionEvent event) {
        PickUpDateValue=(PickUpDateDP.getValue().toString());
        System.out.println(PickUpDateValue);
        ReturnUpDateValue=(ReturnDateDP.getValue().toString());
        System.out.println(ReturnUpDateValue);
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
        
        
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
    private void PickUpDateAction(ActionEvent event) {
        SearchButton.setDisable(false);
    }

    @FXML
    private void ReturnDateAction(ActionEvent event) {
    }
    
}
