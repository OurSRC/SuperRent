/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import dao.DaoException;
import dao.VehicleDao;
import entity.Vehicle;
import entity.VehicleClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ViewVehiclesByYearFXMLController implements Initializable {
   
    @FXML
    private TableView VehiclesListTable;
    @FXML
    private TableColumn VehicleNoColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn VehicleModelColumn;
    @FXML
    private TableColumn YearColumn;
    @FXML
    private TableColumn OdometerColumn;
    @FXML
    private TableColumn StatusColumn;
    @FXML
    private Button SearchButton;
    @FXML
    private Label YearsLabel;
    @FXML
    private TextField YearsTF;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private Label VehicleTypeLabel;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private ToggleGroup VehicleTypeTG;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    private String vehicleType;
    private VehicleClass.TYPE vehicleTypeEnum;
    private String vehicleClass;
    public String noofyears;
    private int Maxmanufactureyear ;
    private int currentyear ;
    private int ageinyears ;


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
        vehicleTypeEnum = VehicleClass.TYPE.Car;
    }    

    @FXML
    private void ViewVehiclesListAction(ActionEvent event)throws DaoException {
               
        if (ValidateInput()) {
            
            
            
        if(VehicleClassCB.valueProperty().isNull().getValue())
        {
            vehicleClass = null;
               
        }
        
        else
        {
            vehicleClass = VehicleClassCB.getSelectionModel().getSelectedItem().toString();
        }
         populateSearchTable();
        System.out.println("Vehicle class : " + vehicleClass);
        noofyears=YearsTF.getText(). toString();
        System.out.println("No Of Years : " + noofyears);
        }  else {
            System.out.println("Please Enter the Required Fields");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please Enter Suitable Values in Mandatory Fields");
            dialog.showDialog();
        }
    }
    
     public void populateSearchTable() throws DaoException {
         currentyear = Calendar.getInstance().get(Calendar.YEAR);
         Maxmanufactureyear = currentyear-ageinyears;
        
        VehiclesListTable.getItems().clear();
       
        VehicleDao newVehicleCtrl = new VehicleDao();
        ArrayList<Vehicle> vehicleArray = newVehicleCtrl.findVehicleOlderThan(Maxmanufactureyear,vehicleClass,vehicleTypeEnum); /* Get the Arraylist from the Control Object */

        ObservableList<Vehicle> slist = FXCollections.observableArrayList(vehicleArray);
       VehiclesListTable.setItems(slist);
        System.out.println("I am here and it is working");
        
               VehicleNoColumn.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
               VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));
               VehicleModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
               YearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
               OdometerColumn.setCellValueFactory(new PropertyValueFactory("odometer"));
               StatusColumn.setCellValueFactory(new PropertyValueFactory("status"));
    }
     
     public boolean ValidateInput() {
             
             boolean validated=true; 
            if ( YearsTF.getText().equals("")) 
            
            {
            validated=false;
            } 
        
            try{
            ageinyears =Integer.parseInt(YearsTF.getText());
            if (ageinyears > 50)
                validated = false;
            }
            catch(NumberFormatException e){
                validated=false;
            }
            return validated;
        }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void VehicleClassCBAction(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
        vehicleTypeEnum = VehicleClass.TYPE.Car;
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        vehicleType = "TRUCK";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getTruckType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
        vehicleTypeEnum = VehicleClass.TYPE.Truck;
    }
    
}
