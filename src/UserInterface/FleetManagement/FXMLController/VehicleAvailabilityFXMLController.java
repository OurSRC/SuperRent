/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import entity.VehicleClass;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class VehicleAvailabilityFXMLController implements Initializable {
    @FXML
    private ComboBox<?> ClassOfVehicle;
    @FXML
    private TableColumn<?, ?> HourlyRate;
    @FXML
    private TableColumn<?, ?> DailyRate;
    @FXML
    private TableColumn<?, ?> WeeklyRate;
    @FXML
    private DatePicker DateFromDatePicker;
    @FXML
    private ComboBox DateFromTime;
    @FXML
    private ComboBox<?> DateToTime;
    @FXML
    private DatePicker DateToDatePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void DisplayAvailableVehicleAction(ActionEvent event) throws ParseException
    {
        

        if(ValidateInput())
        {
                        LocalDate DateFrom = DateFromDatePicker.getValue();
                        String FromTime = DateFromTime.getValue().toString();
                        Date PickUpDate = DateClass.getDateTimeObject(DateFrom,FromTime);
                        
                        LocalDate ToDate = DateToDatePicker.getValue();
                        String ToTime = DateToTime.getValue().toString();
                        Date ReturnDate = DateClass.getDateTimeObject(ToDate,ToTime);
                        
                        
                        String VehicleType = ClassOfVehicle.getValue().toString();
                        System.out.println("Passing Parameters from VehicleAvailabilityFXMLController to Vehicle Control");
                        System.out.println("Vehicle Type : " + VehicleType);
                        System.out.println("PickUpDate : " + PickUpDate.toString());
                        System.out.println("ReturnDate : " + ReturnDate.toString());
                        System.out.println("Return Type : ArrayList<VehicleClass>");


                        if(ReturnDate.after(PickUpDate) && PickUpDate.compareTo(ReturnDate)!=0)
                        {
                            VehicleClass.TYPE type;
                            if( VehicleType.compareTo("Car")==0 )
                                type = VehicleClass.TYPE.Car;
                            else
                                type = VehicleClass.TYPE.Truck;
                            
                        VehicleCtrl vehicleControl = new VehicleCtrl();
                        ArrayList<String> AvailableVehicleTypes = vehicleControl.getVehicleAvailability( type, PickUpDate, ReturnDate, BranchCtrl.getDefaultBranch() );
                        ObservableList<String> list =  FXCollections.observableArrayList(AvailableVehicleTypes);
                        //AvailableVehicleTypeCB.getItems().clear();
                        //AvailableVehicleTypeCB.setItems(list);
                        //System.out.println(pickUpTime);
                        //System.out.println(returnTime);   
                        }else
                        {
                            System.out.println("Invalid Dates ");
                            DialogFX dialog = new DialogFX(Type.ERROR);
                            dialog.setTitleText("Error");
                            dialog.setMessage("Invalid Dates Entered");
                            dialog.showDialog();
                        }
        }else
        {
            System.out.println("Please enter the Mandatory Fields");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter all the Mandatory Values");
            dialog.showDialog();
        }
    }
    
    
    
    public void ReserveVehicleActionButton(ActionEvent event) throws IOException
    {
        ReservationNavigator.loadVista(ReservationNavigator.ADDITIONALEQUIPMENTS);
    }
    
    public void DateFromTimeCBAction(ActionEvent event)
    {
        
    }
    
    public void DateToTimeCBAction(ActionEvent event)
    {
        
    }
    
    public void DateFromDatePickerAction(ActionEvent event)
    {
        
    }
    
    public void DateToDatePickerAction(ActionEvent event)
    {
        
    }
    
    public boolean ValidateInput()
    {
        if(DateFromDatePicker.valueProperty().isNotNull().getValue()  
                && DateToDatePicker.valueProperty().isNotNull().getValue()  
                && ClassOfVehicle.valueProperty().isNotNull().getValue()
                && DateFromTime.valueProperty().isNotNull().getValue()
                && DateToTime.valueProperty().isNotNull().getValue())
        {
            return true;
        }else
        {
            return false;
        }
    }
    
}
