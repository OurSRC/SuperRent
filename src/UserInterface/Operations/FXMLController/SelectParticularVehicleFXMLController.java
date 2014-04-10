/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import ControlObjects.VehicleCtrl;
import Operations.Reserve;
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
    private TableColumn<?, ?> VehicleClassColumn;
    @FXML
    private TableColumn<?, ?> PlateColumn;
    @FXML
    private TableColumn<?, ?> VehicleModelColumn;
    @FXML
    private TableColumn<?, ?> OdometerColumn;
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
        
        ArrayList<Vehicle> newArray = newVehicleCtrl.searchVehicle(newVehicle);
        ObservableList<Vehicle> slist = FXCollections.observableArrayList(newArray);
        VehicleTable.setItems(slist);
        System.out.println("Hello buddy");
        
        VehicleModelColumn.setCellValueFactory(new PropertyValueFactory("model"));

    }    

    @FXML
    private void VehicleTypeSelectedTF(ActionEvent event) {
    }

    @FXML
    private void NextButtonAction(ActionEvent event) {
    }

    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException {
        RentNavigator.clearVista();
        RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);
    }
    
}
