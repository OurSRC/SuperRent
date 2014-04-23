/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import ControlObjects.VehicleCtrl;
import Operations.Reserve;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import entity.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class SelectParticularVehicleFXMLController implements Initializable {
    @FXML
    private TableView VehicleTable;
    @FXML
    private TextField VehicleTypeSelected;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn PlateColumn;
    @FXML
    private TableColumn VehicleModelColumn;
    @FXML
    private TableColumn OdometerColumn;
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        Vehicle newVehicle = new Vehicle();
        newVehicle.setClassName("COMPACT");
        newVehicle.setRentStatus("IDLE");
        newVehicle.setStatus("FORRENT");
        VehicleTypeSelected.setText(newVehicle.getClassName());
        
        ArrayList<Vehicle> newArray = newVehicleCtrl.searchVehicle(newVehicle);
        ObservableList<Vehicle> slist = FXCollections.observableArrayList(newArray);
        VehicleTable.setItems(slist);
        System.out.println("Hello buddy");
        
        VehicleModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
        PlateColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
        OdometerColumn.setCellValueFactory(new PropertyValueFactory("odometer"));
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));

    }    

    @FXML
    private void VehicleTypeSelectedTF(ActionEvent event) {
    }

    @FXML
    private void NextButtonAction(ActionEvent event) throws IOException {
        
        if (!VehicleTable.getSelectionModel().isEmpty()) {
            Vehicle rentVehicle = (Vehicle) VehicleTable.getSelectionModel().getSelectedItem();
            RentNavigator.RentVehicle = rentVehicle;
            RentNavigator.loadVista(RentNavigator.RentDetailsPage);
            
        } else {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please select a vehicle to Proceed");
            dialog.showDialog();
            System.out.println("No Vehicle Selected");
        }
    }

    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException {
        RentNavigator.clearVista();
        RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);
    }
    
}
