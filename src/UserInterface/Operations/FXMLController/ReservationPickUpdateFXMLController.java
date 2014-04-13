/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import static SystemOperations.DateClass.getDateTimeObject;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import org.joda.time.*;
import java.time.*;


/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReservationPickUpdateFXMLController implements Initializable {

        public TextField PickUpDateTF;
        public DatePicker PickupDate;
        public DatePicker ReturnDate;

    
    public ComboBox AvailableVehicleTypeCB;
    @FXML
    private ComboBox VehicleClassComboBox;
    @FXML
    private TableView<?> PriceTable;
    @FXML
    private TableColumn<?, ?> WeeklyRate;
    @FXML
    private TableColumn<?, ?> DailyRate;
    @FXML
    private TableColumn<?, ?> HourlyRate;
    @FXML
    private ComboBox PickUpTimeCombo;
    @FXML
    private ComboBox ReturnTimeCombo;
    @FXML
    private Button NextButton;
    
    
    /* Variables to Store the Values in the Screen */
    LocalDate pickUpTime ;
    LocalDate returnTime;
    String VehicleType;
    

    @FXML
    public void SelectedVehicleCBAction(ActionEvent event)
    {
       VehicleType = AvailableVehicleTypeCB.getSelectionModel().getSelectedItem().toString();
       NextButton.setDisable(false);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AvailableVehicleTypeCB.setDisable(true);
        NextButton.setDisable(true);
        
    }    

    @FXML
    private void NextButtonAction(ActionEvent event) throws IOException {
      //  ReservationNavigator.newReserve.type = VehicleType;
        System.out.println(VehicleType);
        ReservationNavigator.clearVista();
        ReservationNavigator.loadVista(ReservationNavigator.ADDITIONALEQUIPMENTS);   
    }

    
    @FXML
    private void VehicleClassCBAction(ActionEvent event) {
        try
        {
            String toString = VehicleClassComboBox.getSelectionModel().getSelectedItem().toString();
            
            if(!toString.equals(null) && PickupDate.getValue().toEpochDay()!= 0 && ReturnDate.getValue().toEpochDay()!=0)
            {
                        //this.pickUpTime = PickupDate.getValue().toEpochDay();
                        //this.returnTime = ReturnDate.getValue().toEpochDay();
                        //DateClass.getDateTimeObject(PickupDate.getValue().toString());
                        System.out.println(PickupDate.getValue().toString());
                        LocalDate newDate = ReturnDate.getValue();
                        AvailableVehicleTypeCB.setDisable(false);
                        VehicleCtrl vehicleControl = new VehicleCtrl();
                        //ArrayList<String> AvailableVehicleTypes = vehicleControl.getVehicleAvailability(toString, 123,456);
                       // ObservableList<String> list =  FXCollections.observableArrayList(AvailableVehicleTypes);
                        AvailableVehicleTypeCB.getItems().clear();
                       // AvailableVehicleTypeCB.setItems(list);
                        System.out.println(pickUpTime);
                        System.out.println(returnTime);
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }   

    @FXML
    private void PickUpDateAction(ActionEvent event) throws ParseException {
        /*ReturnDate.setValue(null);
        VehicleClassComboBox.setValue(null);
        AvailableVehicleTypeCB.setValue(null);
        AvailableVehicleTypeCB.setDisable(true);
        NextButton.setDisable(true); 
        Date input = new Date();
       // pickUpTime = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(getDateTimeObject(PickupDate.getValue(),null).toString() + " hello i am here");
        Date trial = getDateTimeObject(PickupDate.getValue(),null);
        
        String DATE_FORMAT = "yyyy/MM/dd HH:MM:SS";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        System.out.println("Formated Date " + sdf.format(trial) + "Correct Date");
        String d1 = new SimpleDateFormat(DATE_FORMAT).format(trial);
        */
    }

    @FXML
    private void ReturnDateAction(ActionEvent event) {
        System.out.println(PickupDate.getValue().toString());
    }
}
