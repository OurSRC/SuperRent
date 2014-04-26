/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.VehicleCtrl;
import dao.DaoException;
import dao.VehicleDao;
import entity.Vehicle;
import entity.VehicleClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewVehiclesforSaleFXMLController implements Initializable {

    @FXML
    private TableView VehiclesforSaleTable;
    @FXML
    private TableColumn VehicleNoColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn ModelColumn;
    @FXML
    private TableColumn YearColumn;
    @FXML
    private TableColumn OdometerColumn;
    @FXML
    private TableColumn SalePriceColumn;
    @FXML
    private Label VehicleCategoryLabel;
    @FXML
    private Button SearchButton;

    @FXML
    private Button PrintPDFButton;
    //@FXML
    //private Label VehicleYearLabel;
   //@FXML
    //private TextField VehicleYearTF;
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

   // private String manufactureyear;
    @FXML
    private Font x1;
    @FXML
    private ToggleGroup VehicleTypeTG;

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
        if (!VehicleClassCB.valueProperty().isNull().getValue()){
            vehicleClass = VehicleClassCB.getSelectionModel().getSelectedItem().toString();
        } else {
            vehicleClass = null;
        }
        
        //manufactureyear=VehicleYearTF.getText().toString();
         populateSearchTable();
    }

     public void populateSearchTable()  {
          
        
        Vehicle newVehicle = new Vehicle(null, null, null, 0, 1, Vehicle.STATUS.FORSALE, null, Vehicle.SELLSTATUS.FORSALE, vehicleClass, 0);
        
        VehiclesforSaleTable.getItems().clear();
       
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        ArrayList<Vehicle> vehicleArray = newVehicleCtrl.searchVehicle(newVehicle); /* Get the Arraylist from the Control Object */
        // deleting the vehicles with the correct type according to radio button
        for(int i=0; i < vehicleArray.size(); i++) {
            VehicleClass vehicle = newVehicleCtrl.findVehicleClass(vehicleArray.get(i).getClassName());
            System.out.println(vehicle.getVehicleType().toString());
            if(!vehicle.getVehicleType().toString().toLowerCase().equals(vehicleType.toLowerCase())){
                vehicleArray.remove(i);
            }
            
        }
       ObservableList<Vehicle> slist = FXCollections.observableArrayList(vehicleArray);
       VehiclesforSaleTable.setItems(slist);
        System.out.println("I am here and it is working");
        
               VehicleNoColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
               VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));
               ModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
               YearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
               OdometerColumn.setCellValueFactory(new PropertyValueFactory("odometer"));
               SalePriceColumn.setCellValueFactory(new PropertyValueFactory("sellingPrice"));
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
