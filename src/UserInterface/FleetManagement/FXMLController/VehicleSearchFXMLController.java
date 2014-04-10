/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import entity.Vehicle;
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

    
    /* Variables in the Class */
    public String SearchVehicleClass;
    public String SearchPlateNumber;
    public String SearchVehicleAge;
    public String SearchStatus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list =  FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }    

    @FXML
    private void SearchButtonAction(ActionEvent event) {
        if(VehicleClassCB.valueProperty().isNotNull().getValue())
        {
            SearchVehicleClass = VehicleClassCB.getValue().toString();
            System.out.println("Vehicle Class : " + SearchVehicleClass);
        }else
        {
            SearchVehicleClass = null;
        }
        
        if(!VehicleAgeTF.getText().isEmpty())
        {
            SearchVehicleAge = VehicleAgeTF.getText();
            System.out.println("Vehicle Age : " + SearchVehicleAge);
        }else
        {
            SearchVehicleAge = null;
        }
        
        if(!PlateNumberTF.getText().isEmpty())
        {
            SearchPlateNumber = PlateNumberTF.getText();
            System.out.println("Vehicle Plate Number : " + SearchPlateNumber);
        }else
        {
            SearchPlateNumber = null;
        }
        
        if(StatusCB.valueProperty().isNotNull().getValue())
        {
            SearchStatus = StatusCB.getValue().toString();
            System.out.println("Vehicle Status : " + SearchVehicleClass);
        }else
        {
            SearchStatus = null;
        }
        System.out.println("This is called from VehicleSearchFXMLController from SearchButtonAction");
        populateSearchTable();
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
    private void AddVehicleButtonAction(ActionEvent event) throws IOException{
            VehicleNavigator.loadVista(VehicleNavigator.ADDVEHICLE); 
    }
    
    @FXML
    private void ModifyVehicleButtonAction(ActionEvent event) throws IOException{
        if(!VehicleSearchTable.getSelectionModel().isEmpty())
        {
            VehicleNavigator.loadVista(VehicleNavigator.MODIFYVEHICLE); 
        }
        else
        {
            VehicleNavigator.loadVista(VehicleNavigator.MODIFYVEHICLE); 
            System.out.println("No Items Selected");
        }
    }  
    
    public void populateSearchTable(){
             VehicleSearchTable.getItems().clear();
             

        
        ArrayList<Vehicle> newArray = new ArrayList<>(); /* Get the Arraylist from the Control Object */
        Date sampleDate = new Date();
        
        /* Added for dummy object */
        Vehicle dummyVehicle = new Vehicle("1234",sampleDate,"BENZ",12333,1,Vehicle.STATUS.FORRENT,Vehicle.RENTSTATUS.AVAILABLE,null,"ECONOMY",0);
        newArray.add(dummyVehicle);
        ObservableList<Vehicle> slist =  FXCollections.observableArrayList(newArray);
        VehicleSearchTable.setItems(slist);
        System.out.println("I am here and it is working");
        PlateNumberColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
        ModelColumn.setCellValueFactory( new PropertyValueFactory("mode"));
        VehicleClassColoumn.setCellValueFactory( new PropertyValueFactory("className"));
        ManufacturingYearColumn.setCellValueFactory( new PropertyValueFactory("manufactureDate"));
        StatusColumn.setCellValueFactory( new PropertyValueFactory("status"));

        
        
    }
}
