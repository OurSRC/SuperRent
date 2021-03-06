/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import Vehicle.VehicleCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ErrorMsg;
import Vehicle.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class VehicleSearchFXMLController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField VehicleAgeTF;
    @FXML
    private TextField PlateNumberTF;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private ToggleGroup VehicleTypeTG;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private TableView VehicleSearchTable;
    @FXML
    private TableColumn PlateNumberColumn;
    @FXML
    private TableColumn VehicleClassColoumn;
    @FXML
    private TableColumn ModelColumn;
    @FXML
    private TableColumn ManufacturingYearColumn;
    @FXML
    private TableColumn StatusColumn;
    @FXML
    private ComboBox VehicleAgeCB;
    @FXML
    private ComboBox StatusCB;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private TableColumn SellingPriceColumn;

    /* Variables in the Class */
    public String SearchVehicleClass;
    public String SearchPlateNumber;
    public String SearchVehicleAge;
    public String SearchStatus;
    public String VehicleAgeYears;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void SearchButtonAction(ActionEvent event) {

        if (ValidateMandatoryFields()) {
            SearchVehicleClass = VehicleClassCB.getSelectionModel().getSelectedItem().toString();

 

            if (!PlateNumberTF.getText().isEmpty()) {
                SearchPlateNumber = PlateNumberTF.getText();
                System.out.println("Vehicle Plate Number : " + SearchPlateNumber);
            } else {
                SearchPlateNumber = null;
            }
            populateSearchTable();
        } else {
            System.out.println("Please enter all the Mandatory Fields");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Missing Details");
            dialog.setMessage("Please Enter All Mandatory Fields");
            dialog.showDialog();
        }
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getTruckType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void VehicleAgeCBAction(ActionEvent event) {

    }

    @FXML
    private void VehicleClassCBAction(ActionEvent event) {

    }

    @FXML
    private void ViewVehicleInformationButton(ActionEvent event) throws IOException {
        VehicleNavigator.loadVista(VehicleNavigator.ADDVEHICLE);
    }

    @FXML
    private void AddVehicleButtonAction(ActionEvent event) throws IOException {
        VehicleNavigator.loadVista(VehicleNavigator.ADDVEHICLE);
    }

    @FXML
    private void ModifyVehicleButtonAction(ActionEvent event) throws IOException {
        if (!VehicleSearchTable.getSelectionModel().isEmpty()) {
            VehicleNavigator.Modifyvehicle = null;
            VehicleNavigator.Modifyvehicle = (Vehicle) VehicleSearchTable.getSelectionModel().getSelectedItem();
            VehicleNavigator.loadVista(VehicleNavigator.MODIFYVEHICLE);
        } else {
            System.out.println("No Items Selected");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Missing Details");
            dialog.setMessage("No Vehicle Selected");
            dialog.showDialog();
        }
    }

    @FXML
    private void SellVehicleButtonAction(ActionEvent event) throws IOException {
        if (!VehicleSearchTable.getSelectionModel().isEmpty()) {
            Vehicle SellVehicle = (Vehicle) VehicleSearchTable.getSelectionModel().getSelectedItem();
            if (SellVehicle.getStatus().toString().equals("FORSALE")) {
                SellVehicle.setSellStatus("SOLD");
                VehicleCtrl newVehicleCtrl = new VehicleCtrl();
                if (newVehicleCtrl.updateVehicle(SellVehicle)) {
                    System.out.println("vehicle Successfully Sold");
                    DialogFX dialog = new DialogFX(Type.INFO);
                    dialog.setTitleText("Success");
                    dialog.setMessage("vehicle Successfully Sold");
                    dialog.showDialog();
                    populateSearchTable();
                } else {
                    System.out.println("Vehicle selling Failed. Please Contact System Administrator");
                }
            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Not a valid Car for Selling");
                dialog.showDialog();
                System.out.println("Not a valid Car for Selling");
            }
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("No Vehicle Selected");
            dialog.showDialog();
            System.out.println("No Vehicle Selected");
        }
    }

    public void populateSearchTable() {
        Vehicle newVehicle = new Vehicle(SearchPlateNumber, null, null, 0, 1, null, null, null, SearchVehicleClass, 0);
        VehicleSearchTable.getItems().clear();
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        ArrayList<Vehicle> vehicleArray = newVehicleCtrl.searchVehicle(newVehicle); /* Get the Arraylist from the Control Object */

        ObservableList<Vehicle> slist = FXCollections.observableArrayList();
        for(int i=0;i<vehicleArray.size();i++)
        {
            if(vehicleArray.get(i).getSellStatus() != Vehicle.SELLSTATUS.SOLD)
            {
                slist.add(vehicleArray.get(i));
            } else {
            }
        }
        VehicleSearchTable.setItems(slist);
        PlateNumberColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
        VehicleClassColoumn.setCellValueFactory(new PropertyValueFactory("className"));
        ManufacturingYearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory("status"));
    }

    public boolean ValidateMandatoryFields() {
        if (VehicleClassCB.valueProperty().isNotNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }
}
