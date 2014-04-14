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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ModifyRatesFXMLController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField VehicleClassTF;

    public TextField VehicleTypeTF;
    public TextField DailyRateTF;
    public TextField WeeklyRateTF;
    public TextField HourlyRateTF;

    
    /* Variables to store Values */
    public String vehicleClass;
    public String hourlyRate;
    public String weeklyRate;
    public String dailyRate;
    public String vehicleType;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HourlyRateTF.setText(VehicleClassNavigator.vehicleClass.getHourlyPrice());
        WeeklyRateTF.setText(VehicleClassNavigator.vehicleClass.getWeeklyPrice());
        DailyRateTF.setText(VehicleClassNavigator.vehicleClass.getDailyPrice());
        VehicleClassTF.setText(VehicleClassNavigator.vehicleClass.getClassName());
            VehicleCtrl newVehicleCtrl = new VehicleCtrl();
            
            vehicleType = newVehicleCtrl.getVehicleTypeByClassName(VehicleClassNavigator.vehicleClass.getClassName()).toString();
        
    }

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
        
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
            if(newVehicleCtrl.updateVehicleClass(newVehicleClass))
            {
                System.out.println("Sucess");
            }else
            {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) {
    }

    @FXML
    private void WeeklyRateTF(ActionEvent event) {
    }

    @FXML
    private void DailyRateTF(ActionEvent event) {
    }

    @FXML
    private void HourlyRateTF(ActionEvent event) {
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
