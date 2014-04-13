/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ValidateFields;
import entity.VehicleClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class AddVehicleClassFXMLController implements Initializable {

    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField HourlyRateTF;
    @FXML
    private TextField DailyRateTF;
    @FXML
    private TextField WeeklyRateTF;
    @FXML
    private ToggleGroup VehicleTypeTG;

    public String vehicleType;
    public String vehicleClass;
    public String hourlyRate;
    public String dailyRate;
    public String weeklyRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicleType = "Car";
    }

    @FXML
    private void VehicleTypeCarAction(ActionEvent event) {
        vehicleType = "Car";
    }

    @FXML
    private void VehicleTypeTruckAction(ActionEvent event) {
        vehicleType = "Truck";

    }

    @FXML
    private void AddVehicleClassButtonAction(ActionEvent event) {
        vehicleClass = VehicleClassTF.getText();
        hourlyRate = HourlyRateTF.getText();
        dailyRate = DailyRateTF.getText();
        weeklyRate = WeeklyRateTF.getText();

        if (ValidateMandatoryRecords()) {
            /* Call the Create Vehicle Class function in the VehicleClass Control */
            VehicleClass newVehicleClass = new VehicleClass();
            newVehicleClass.setVehicleType(vehicleType);
            newVehicleClass.setClassName(vehicleClass);
            newVehicleClass.setDailyPrice(dailyRate);
            newVehicleClass.setWeeklyPrice(weeklyRate);
            newVehicleClass.setHourlyPrice(hourlyRate);
            
            VehicleCtrl newVehicleCtrl = new VehicleCtrl();
            if(newVehicleCtrl.createVehicleClass(newVehicleClass))
            {
            DialogFX dialog = new DialogFX(Type.INFO);
            dialog.setTitleText("Vehicle Class Created");
            dialog.setMessage("Vehicle Class " + vehicleClass + " is successfully created");
            dialog.showDialog();
            }else
            {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText(" Error ");
            dialog.setMessage("Vehicle Class Creation Failed");
            dialog.showDialog();
            }
            
        } else {
            System.out.println("Please enter all the mandatory records");
        }

    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) throws IOException {
        VehicleClassNavigator.loadVista(VehicleClassNavigator.VEHICLECLASSMAINPAGE);
    }

    public boolean ValidateMandatoryRecords() {
        if (!vehicleClass.equals("")
                && !hourlyRate.equals("")
                && !dailyRate.equals("")
                && !weeklyRate.equals("")
                && !vehicleType.equals("")) {
            if(ValidateFields.CheckForNumbersOnly(hourlyRate) && ValidateFields.CheckForNumbersOnly(weeklyRate) && ValidateFields.CheckForNumbersOnly(dailyRate))
            {
            System.out.println(vehicleType + " " + vehicleClass + " " + hourlyRate + " " + dailyRate + " " + weeklyRate);
            return true;
            }else
            {
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText(" Error ");
            dialog.setMessage("Improper Rates Entered");
            dialog.showDialog();
                return false;
            }
        } else {
            return false;
        }
    }

}
