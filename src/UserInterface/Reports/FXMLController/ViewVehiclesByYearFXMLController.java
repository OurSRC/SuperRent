/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import entity.Vehicle;
import entity.VehicleClass;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn VehicleTypeColumn;
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
    private ComboBox TypeOfVehicle;
    @FXML
    private Label VehicleTypeLabel;
    
    public String vehicleType;
    public String noofyears;
    public Vehicle vehicle;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ViewVehiclesListAction(ActionEvent event) throws ParseException{
        
        
        if (ValidateInput()) {
        vehicleType = TypeOfVehicle.getValue().toString();
        System.out.println("Vehicle Type : " + vehicleType);
        noofyears=YearsTF.getText(). toString();
        System.out.println("No Of Years : " + noofyears);
     
      /*          
       VehicleCtrl vehicleControl = new VehicleCtrl();
       
       ArrayList<Vehicle> vehicleslist = vehicleControl.searchVehicle(vehicle);
   
        ArrayList<Vehicle> VehicleArray = new ArrayList();
         //System.out.println(vehicleslist.size() + " Size of the Arraylist");
       //for (int i = 0; i <vehicleslist.size(); i++) {
            //        VehicleArray.add(vehicleControl.findVehicleClass(vehicleslist.get(i)));
               }
       // ObservableList<Vehicle> list = FXCollections.observableArrayList(VehicleArray);
        
        VehiclesListTable.getItems().clear(); 
        VehiclesListTable.setItems(list);
        
               VehicleTypeColumn.setCellValueFactory(new PropertyValueFactory("className"));
               VehicleNoColumn.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
               VehicleModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
               YearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
               OdometerColumn.setCellValueFactory(new PropertyValueFactory("odometer"));
               StatusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        */
         }  else {
            System.out.println("Please enter Fields");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter Fields");
            dialog.showDialog();
        }

    }
    
         public boolean ValidateInput() {
        if ( TypeOfVehicle.valueProperty().isNotNull().getValue()
             && !YearsTF.getText().equals("")) 
        {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }
    
}
