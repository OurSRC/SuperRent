/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import entity.VehicleClass;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class VehicleClassSearchFXMLController implements Initializable {

    @FXML
    private ToggleGroup VehicleTypeTG;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private TableView VehicleClassTableView;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn HourlyRateColumn;
    @FXML
    private TableColumn DailyRateColumn;
    @FXML
    private TableColumn WeeklyRateColumn;
    @FXML
    private RadioButton VehicleTypeRBCar;
    @FXML
    private RadioButton VehicleTypeRBTruck;

    /* Variables to store the Values */
    public VehicleClass.TYPE vehicleType;
    public String vehicleClass;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicleClass = "";
        vehicleType = vehicleType.Car;
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    public void ModifyVehicleClassButtonAction(ActionEvent event) throws IOException {
        if (!VehicleClassTableView.getSelectionModel().isEmpty()) {
            VehicleClassNavigator.vehicleClass = (VehicleClass) VehicleClassTableView.getSelectionModel().getSelectedItem();
            VehicleClassNavigator.vehicleClass.setVehicleType(vehicleType);
            System.out.println(VehicleClassNavigator.vehicleClass.getVehicleType().toString());
            VehicleClassNavigator.loadVista(VehicleClassNavigator.MODIFYVEHICLECLASSPAGE);
        } else {
            System.out.println("No Items Selected");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Invalid operation");
            dialog.setMessage("No Vehicle Class Selected.");
            dialog.showDialog();
        }
    }

    @FXML
    public void AddVehicleClassButtonAction(ActionEvent event) throws IOException {
        VehicleClassNavigator.loadVista(VehicleClassNavigator.ADDVEHICLECLASSPAGE);
    }

    @FXML
    private void VehicleClassCBAction(ActionEvent event) {
    }

    @FXML
    private void SearchButtonAction(ActionEvent event) {
        if(VehicleTypeRBTruck.isSelected())
        {
            vehicleType = VehicleClass.TYPE.Truck;
        }else
        {
            vehicleType = VehicleClass.TYPE.Car;
        }
        VehicleClass newVehicleClass = new VehicleClass();
        if (VehicleClassCB.valueProperty().isNotNull().getValue()) {
            vehicleClass = VehicleClassCB.getSelectionModel().getSelectedItem().toString();
        } else {
            vehicleClass = "";
        }

        VehicleClassTableView.getItems().clear();
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        ArrayList<String> VehicleClassString = newVehicleCtrl.getSubVehicleType(vehicleType);
        ArrayList<VehicleClass> VehicleClassArray = new ArrayList();
        if (vehicleClass.equals("")) {

            System.out.println(VehicleClassString.size() + " Size of the ARraylist");
            for (int i = 0; i < VehicleClassString.size(); i++) {
                VehicleClassArray.add(newVehicleCtrl.findVehicleClass(VehicleClassString.get(i)));
            }
        } else {
            VehicleClassArray.add(newVehicleCtrl.findVehicleClass(vehicleClass));
        }

        /* Get the Arraylist from the Control Object */
        ObservableList<VehicleClass> slist = FXCollections.observableArrayList(VehicleClassArray);
        VehicleClassTableView.setItems(slist);
        System.out.println("I am here and it is working");
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));
        HourlyRateColumn.setCellValueFactory(new PropertyValueFactory("hourlyPrice"));
        DailyRateColumn.setCellValueFactory(new PropertyValueFactory("dailyPrice"));
        WeeklyRateColumn.setCellValueFactory(new PropertyValueFactory("weeklyPrice"));
    }

    @FXML
    private void VehicleTypeRBCarAction(ActionEvent event) {
        VehicleClassCB.getItems().clear();

        vehicleType = vehicleType.Car;
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void VehicleTypeRBTruckAction(ActionEvent event) {
        VehicleClassCB.getItems().clear();

        vehicleType = vehicleType.Truck;
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getTruckType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

}
